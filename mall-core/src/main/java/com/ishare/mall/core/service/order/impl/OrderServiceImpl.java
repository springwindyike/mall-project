package com.ishare.mall.core.service.order.impl;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDeliverDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.GeneratedOrderId;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.order.OrderDeliverInfo;
import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductStyle;
import com.ishare.mall.core.repository.deliver.DeliverRepository;
import com.ishare.mall.core.repository.information.OrderItemRepository;
import com.ishare.mall.core.repository.order.GeneratedOrderIdRepository;
import com.ishare.mall.core.repository.order.OrderRepository;
import com.ishare.mall.core.repository.product.ProductRepository;
import com.ishare.mall.core.repository.product.ProductStyleRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.status.OrderState;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;

import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private GeneratedOrderIdRepository generatedOrderIdRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductStyleRepository styleRepository;
	@Autowired
	private OrderItemRepository itemRepository;
	@Autowired
	private DeliverRepository deliverRepository;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ChannelService channelService;

	@Override
	public Order findOne(String id) {
		return orderRepository.findOne(id);
	}
	@Override
	public Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Order> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Order.class);
		Page<Order> page = orderRepository.findAll(spec, pageRequest);
		return page;
    }

	public List<Order> findTotalSales() {

		return orderRepository.findAll();
	}
	
	@Override
	public List<Order> findByCreateBy(String createBy) {
	    List<Order> order = orderRepository.findByCreateBy(createBy);
	    if (order == null || order.size() == 0) return null;
	    return order;
	}
	
	@Override
	public Order createNewOrder(Order order) {
		Date current=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(current);
		GeneratedOrderId generatedOrderId = generatedOrderIdRepository.findOne(date);
		if(null == generatedOrderId){
			GeneratedOrderId go = new GeneratedOrderId();
			go.setId(date);
			go.setOrderId(1);
			generatedOrderIdRepository.save(go);
			String orderIdStr = String.format("%06d", go.getOrderId());     
			order.setOrderId(date + orderIdStr);
			return orderRepository.save(order);
		}
		generatedOrderId.setOrderId(generatedOrderId.getOrderId()+1);
		generatedOrderIdRepository.save(generatedOrderId);
		String orderIdStr = String.format("%06d",generatedOrderId.getOrderId()+1);
		order.setOrderId(date + orderIdStr);
		return orderRepository.save(order);
		
	}

	@Override
	public Order payComplete(String orderId) {
		Order order = orderRepository.findOne(orderId);
		if (order != null) {
			order.setState(OrderState.WAIT_DELIVER);
			order = orderRepository.save(order);
		}
		return order;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public OrderDetailDTO create(ExchangeDTO exchangeDTO) {
		return initProcessor(exchangeDTO);
	}

	@Override
	public Order confirm(String id) {
		return null;
	}

	@Override
	public Order cancel(String id) {
		return null;
	}

	@Override
	public Page<Order> findByChannelId(Integer channelId,
			PageRequest pageRequest) {
		Page<Order> page = orderRepository.findByChannelId(channelId, pageRequest);
		return page;
	}

	//订单生成流程
	private OrderDetailDTO initProcessor(ExchangeDTO exchangeDTO) {
		Order order = new Order();
		OrderDetailDTO detailDTO = new OrderDetailDTO();
		Channel channel = channelService.findByAppId(exchangeDTO.getClientId());
		Member buyer = memberService.findByAccount(exchangeDTO.getAccount());
		order.setOrderId(this.nextOrderId());
		order.setCreateTime(new Date());
		order.setChannel(channel);
		Set<OrderItem> orderItems = this.initItemProcessor(order, exchangeDTO);
		Float total = 0f;
		//费用计算
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getAmount() * orderItem.getProductPrice();
		}
		order.setProductTotalPrice(total);
		//商品费用
		//TODO 运费
		Float transFee = 0f;
		//总计
		order.setTotalPrice(total + transFee);
		//实际支付
		order.setPayableFee(total + transFee);
		//保存 返回
		//修改商品库存
		order.setCreateBy(buyer);
		//收货人
		OrderDeliverInfo orderDeliverInfo = this.initDeliverProcessor(order, exchangeDTO);
		orderRepository.save(order);
		itemRepository.save(orderItems);
		deliverRepository.save(orderDeliverInfo);
		//设置收货人信息
		detailDTO.setDeliver((OrderDeliverDTO) MapperUtils.map(orderDeliverInfo, OrderDeliverDTO.class));
		//设置订单项
		detailDTO.setItems((Set<OrderItemDetailDTO>) MapperUtils.mapAsList(orderItems, OrderItemDetailDTO.class));
		return detailDTO;
	}

	/**
	 * 初始化订单收货地址
	 * @param order
	 * @param exchangeDTO
	 * @return
	 */
	private OrderDeliverInfo initDeliverProcessor(Order order, ExchangeDTO exchangeDTO) {
		OrderDeliverInfo orderDeliverInfo = new OrderDeliverInfo();
		orderDeliverInfo.setOrder(order);
		orderDeliverInfo.setCountry(exchangeDTO.getCountry());
		orderDeliverInfo.setProvince(exchangeDTO.getProvince());
		orderDeliverInfo.setCity(exchangeDTO.getCity());
		orderDeliverInfo.setDistrict(exchangeDTO.getDistrict());
		orderDeliverInfo.setDetail(exchangeDTO.getDetail());
		orderDeliverInfo.setMobile(exchangeDTO.getMobile());
		orderDeliverInfo.setEmail(exchangeDTO.getEmail());
		orderDeliverInfo.setRecipients(exchangeDTO.getRecipients());
		orderDeliverInfo.setTel(exchangeDTO.getTel());
		orderDeliverInfo.setRequirement(exchangeDTO.getRequirement());
		return orderDeliverInfo;
	}

	/**
	 * 初始化订单项
	 * @param order
	 * @param exchangeDTO
	 * @return
	 */
	private Set<OrderItem> initItemProcessor(Order order, ExchangeDTO exchangeDTO) {

		Set<OrderItem> orderItems = new HashSet<>();
		// TODO 暂时单个商品
		Product product = productRepository.findOne(exchangeDTO.getProductId());
		ProductStyle style = styleRepository.findOne(exchangeDTO.getStyleId());
		Member member = memberService.findByAccount(exchangeDTO.getAccount());

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		//设置样式相关
		orderItem.setStyleId(exchangeDTO.getStyleId());
		orderItem.setStyleName(style.getName());
		//设置商品相关
		orderItem.setAmount(exchangeDTO.getAmount());
		orderItem.setProductId(product.getId());
		orderItem.setProductName(product.getName());
		//设置图片
		orderItem.setImageUrl(style.getImageUrl());
		//销售价格
		orderItem.setProductPrice(product.getSellPrice());

		orderItems.add(orderItem);

		return orderItems;
	}

	//获取下一个订单号
	private String nextOrderId() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(current);
		GeneratedOrderId generatedOrderId = generatedOrderIdRepository.findOne(date);
		if(null == generatedOrderId){
			GeneratedOrderId go = new GeneratedOrderId();
			go.setId(date);
			go.setOrderId(1);
			generatedOrderIdRepository.save(go);
			return String.format("%06d", go.getOrderId());
		}
		generatedOrderId.setOrderId(generatedOrderId.getOrderId() + 1);
		generatedOrderIdRepository.save(generatedOrderId);
		return String.format("%06d",generatedOrderId.getOrderId() + 1);
	}


}
