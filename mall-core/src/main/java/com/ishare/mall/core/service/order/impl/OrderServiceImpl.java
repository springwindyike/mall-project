package com.ishare.mall.core.service.order.impl;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.GeneratedOrderId;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductStyle;
import com.ishare.mall.core.repository.order.GeneratedOrderIdRepository;
import com.ishare.mall.core.repository.order.OrderRepository;
import com.ishare.mall.core.repository.product.ProductRepository;
import com.ishare.mall.core.repository.product.ProductStyleRepository;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.status.OrderState;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	private MemberService memberService;

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
	public Order create(ExchangeDTO exchangeDTO) {
		Order order = null;
		Product product = productRepository.findOne(exchangeDTO.getProductId());
		return null;
	}

	@Override
	public Page<Order> findByChannelId(Integer channelId,
			PageRequest pageRequest) {
		Page<Order> page = orderRepository.findByChannelId(channelId, pageRequest);
		return page;
	}
	//订单生成流程
	private void initProcessor(ExchangeDTO exchangeDTO) {
		Order order = new Order();
		Product product = productRepository.findOne(exchangeDTO.getProductId());
		order.setOrderId(this.nextOrderId());
		order.setCreateTime(new Date());
		OrderItem orderItem = this.initItemProcessor(order, exchangeDTO);
	}

	/**
	 * 初始化订单项
	 * @param order
	 * @param exchangeDTO
	 * @return
	 */
	private OrderItem initItemProcessor(Order order, ExchangeDTO exchangeDTO) {
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
		orderItem.setImageUrl(style.getImageUrl());
		return orderItem;
	}


	//获取下一个订单号
	private String nextOrderId() {
		Date current=new Date();
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
		return String.format("%06d",generatedOrderId.getOrderId()+1);

	}


}
