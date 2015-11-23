package com.ishare.mall.core.service.order.impl;

import com.ishare.mall.common.base.dto.order.*;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.enumeration.*;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.*;
import com.ishare.mall.core.model.pay.OrderPayLog;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.repository.deliver.DeliverRepository;
import com.ishare.mall.core.repository.information.OrderItemRepository;
import com.ishare.mall.core.repository.order.GeneratedOrderIdRepository;
import com.ishare.mall.core.repository.order.OrderRefundRepository;
import com.ishare.mall.core.repository.order.OrderRepository;
import com.ishare.mall.core.repository.order.OrderActionLogRepository;
import com.ishare.mall.core.repository.product.ProductRepository;
import com.ishare.mall.core.repository.product.ProductStyleRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.service.pay.OrderPayLogService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

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
    @Autowired
    private OrderActionLogRepository orderActionLogRepository;
    @Autowired
    private OrderPayLogService orderPayLogService;
    @Autowired
    private OrderRefundRepository orderRefundRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Order findOne(String id) {
        Order order = orderRepository.findOne(id);
        if (order == null) {
            throw new OrderServiceException("订单不存在");
        }
        return order;
    }

    @Override
    public Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        try {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Specification<Order> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Order.class);
            Page<Order> page = orderRepository.findAll(spec, pageRequest);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("订单搜索失败");
        }
    }

    public List<Order> findTotalSales() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("查询订单失败");
        }
    }

    @Override
    public List<Order> findByCreateBy(String createBy) {
        List<Order> order = orderRepository.findByCreateBy(createBy);
        if (order == null || order.size() == 0) {
            throw new OrderServiceException("没有找到符合要求的订单");
        }
        return order;
    }

    @Override
    public Order createNewOrder(Order order) {
        try {
            Date current = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(current);
            GeneratedOrderId generatedOrderId = generatedOrderIdRepository.findOne(date);
            if (null == generatedOrderId) {
                GeneratedOrderId go = new GeneratedOrderId();
                go.setId(date);
                go.setOrderId(1);
                generatedOrderIdRepository.save(go);
                String orderIdStr = String.format("%06d", go.getOrderId());
                order.setOrderId(date + orderIdStr);
                return orderRepository.save(order);
            }
            generatedOrderId.setOrderId(generatedOrderId.getOrderId() + 1);
            generatedOrderIdRepository.save(generatedOrderId);
            String orderIdStr = String.format("%06d", generatedOrderId.getOrderId() + 1);
            order.setOrderId(date + orderIdStr);
            return orderRepository.save(order);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("订单保存失败");
        }
    }

    @Override
    public Order payComplete(AliPayNotifyDTO notify) {
        log.debug("支付完成：" + notify.toString());
        synchronized (this) {
            OrderPayLog payLog = orderPayLogService.findByOrderId(notify.getOut_trade_no());
            if (payLog != null && payLog.getPayType().equals(PayType.NEW)) {
                payLog.setAmount(new BigDecimal(notify.getTotal_fee()));
                payLog.setTansId(notify.getTrade_no());
                payLog.setUpdateTime(new Date());
                payLog.setPayType(PayType.PAY);
                log.warn("pay from pc and pay by blank amount payment={}", "支付宝");
                //更新支付log状态
                orderPayLogService.updateForProcess(payLog);
            } else if (payLog == null) {
                payLog = new OrderPayLog();
                payLog.setOrderId(notify.getSubject());
                payLog.setAmount(new BigDecimal(notify.getTotal_fee()));
                payLog.setTansId(notify.getTrade_no());
                payLog.setUpdateTime(new Date());
                payLog.setFinishTime(new Date());
                payLog.setCreateTime(new Date());
                payLog.setCostType(CostType.MEMBER);
                payLog.setPayType(PayType.PAY);

            }

            try {
                Order order = orderRepository.findOne(notify.getOut_trade_no());
                if (order != null) {
                    order.setState(OrderState.WAIT_DELIVER);
                    order.setPaymentState(true);
                    payLog.setChannelId(order.getChannel().getId());
                    orderPayLogService.save(payLog);
                    order = orderRepository.save(order);
                }

                return order;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new OrderServiceException("支付出错");
            }

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<OrderDetailDTO> create(ExchangeDTO exchangeDTO) {
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
        try {
            Page<Order> page = orderRepository.findByChannelId(channelId, pageRequest);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("查询订单失败");
        }
    }

    @Override
    public Page<Order> findBySellerId(Integer id, PageRequest pageRequest) throws OrderServiceException {
        try {
            Page<Order> page = orderRepository.findBySellerId(id, pageRequest);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("查询订单失败");
        }
    }

    //订单生成流程
    private List<OrderDetailDTO> initProcessor(ExchangeDTO exchangeDTO) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<OrderDetailDTO>();
        Channel channelSeller = channelService.findByAppId(exchangeDTO.getClientId());
        Member buyer = memberService.findByAccount(exchangeDTO.getAccount());
        Map<Integer, List<OrderItemDetailDTO>> map = this.splitChannel(exchangeDTO);
        Set<Integer> set = map.keySet();
        for (Integer channelId : set) {
            List<OrderItemDetailDTO> list = map.get(channelId);
            Order order = new Order();
            OrderDetailDTO detailDTO = new OrderDetailDTO();
            Channel channel = channelService.findOne(channelId);
            String orderId = this.nextOrderId();
            log.debug("ID : " + orderId);
            order.setOrderId(orderId);
            order.setCreateTime(new Date());
            order.setChannel(channel);
            order.setSellerId(channelSeller.getId());
            List<OrderItem> orderItems = this.initItemProcessor(order, list);
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
            //支付方式
            order.setPaymentWay(exchangeDTO.getPaymentWay());
            //等待支付状态 TODO 如果这里的支付方式可选货到付款之后需要变动
            order.setState(OrderState.WAIT_PAYMENT);
            //是否支付
            order.setPaymentState(false);
            //保存 返回
            //修改商品库存
            order.setCreateBy(buyer);
            //收货人
            OrderDeliverInfo orderDeliverInfo = this.initDeliverProcessor(order, exchangeDTO);
            try {
                order.setOrderDeliverInfo(orderDeliverInfo);
                Order newOrder = orderRepository.save(order);
                itemRepository.save(orderItems);

                OrderActionLog orderActionLog = new OrderActionLog();
                orderActionLog.setOrderActionLogType(OrderActionLogType.DELIVER);
                orderActionLog.setNote("创建订单");
                orderActionLog.setOrder(newOrder);
                orderActionLog.setActionById(buyer.getId().toString());
                orderActionLog.setActionByname(buyer.getName());
                orderActionLog.setActionBytype(buyer.getMemberType().getName());
                orderActionLog.setActionByfrom("center");
                orderActionLog.setActionTime(order.getUpdateTime());
                orderActionLogRepository.save(orderActionLog);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new OrderServiceException("订单保存失败");
            }
            detailDTO = (OrderDetailDTO) MapperUtils.map(order, OrderDetailDTO.class);
            //设置收货人信息
            detailDTO.setDeliver((OrderDeliverDTO) MapperUtils.map(orderDeliverInfo, OrderDeliverDTO.class));
            //设置订单项
            detailDTO.setItems((Set<OrderItemDetailDTO>) MapperUtils.mapAsSet(orderItems, OrderItemDetailDTO.class));
            orderDetailDTOList.add(detailDTO);
        }

        return orderDetailDTOList;
    }

    /**
     * 初始化订单收货地址
     *
     * @param order
     * @param exchangeDTO
     * @return
     */
    private OrderDeliverInfo initDeliverProcessor(Order order, ExchangeDTO exchangeDTO) {
        OrderDeliverInfo orderDeliverInfo = new OrderDeliverInfo();
        //orderDeliverInfo.setOrder(order);
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
        //设置快递方式
        orderDeliverInfo.setDeliverWay(exchangeDTO.getDeliverWay());
        return orderDeliverInfo;
    }

    /**
     * 初始化订单项
     *
     * @param order
     * @param orderItemDetailDTOList
     * @return
     */
    private List<OrderItem> initItemProcessor(Order order, List<OrderItemDetailDTO> orderItemDetailDTOList) {

        List<OrderItem> orderItems = new ArrayList<>();
        // TODO 判断商品是否是来自第三方
        // TODO 判断商品库存
        // TODO 暂时单个商品
        //List<OrderItemDetailDTO> orderItemDetailDTOList = exchangeDTO.getOrderItemDetailDTOList();
        for (OrderItemDetailDTO orderItemDetailDTO : orderItemDetailDTOList) {
//			Product product = productRepository.findOne(orderItemDetailDTO.getProductId());
//			// 去掉style
//			//ProductStyle style = styleRepository.findOne(exchangeDTO.getStyleId());
//			if(product != null){
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(orderItemDetailDTO, orderItem);
            orderItem.setOrder(order);
            //设置样式相关
            //orderItem.setStyleId(orderItemDetailDTO.getStyleId().longValue());
            //if (style != null) {
            //orderItem.setStyleName(style.getName());
            //orderItem.setImageUrl(style.getImageUrl());
            //}
            //orderItem.setImageUrl(product.getDefaultImageUrl());
            //设置商品相关
            //orderItem.setAmount(orderItemDetailDTO.getAmount());
//				orderItem.setProductId(product.getId());
//				orderItem.setProductName(product.getName());
            orderItem.setState(OrderItemState.COMPLETE_VERIFY);
            orderItem.setChannelId(orderItemDetailDTO.getChannelId());
            //销售价格
//				orderItem.setProductPrice(product.getSellPrice());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    //获取下一个订单号
    private String nextOrderId() {
        synchronized (this) {
            Date current = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(current);
            GeneratedOrderId generatedOrderId = generatedOrderIdRepository.findOne(date);
            if (null == generatedOrderId) {
                GeneratedOrderId go = new GeneratedOrderId();
                go.setId(date);
                go.setOrderId(1);
                generatedOrderIdRepository.save(go);
                return date + String.format("%06d", go.getOrderId());
            }
            generatedOrderId.setOrderId(generatedOrderId.getOrderId() + 1);
            generatedOrderIdRepository.save(generatedOrderId);
            System.out.println("date : " + date);
            System.out.println("orderID : " + date + String.format("%06d", generatedOrderId.getOrderId()));
            return date + String.format("%06d", generatedOrderId.getOrderId());
        }
    }

    @Override
    public Order updateOrder(Order order, OrderActionLog orderActionLog) throws OrderServiceException {

        try {
            orderActionLogRepository.save(orderActionLog);
            return orderRepository.save(order);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("订单修改失败");
        }
    }

    @Override
    public Page<Order> findBycondition(String orderId, Integer channelId, PageRequest pageRequest) throws OrderServiceException {
        try {
            return orderRepository.findBycondition(orderId, channelId, pageRequest);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("搜索订单失败");
        }
    }

    @Override
    public Page<Order> findAllBycondition(Map<String, Object> searchParams, PageRequest pageRequest) throws OrderServiceException {
        try {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Specification<Order> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Order.class);

            return orderRepository.findAll(spec, pageRequest);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("搜索订单失败");
        }
    }

    @Override
    public Page<Order> findAll(PageRequest pageRequest)
            throws OrderServiceException {
        try {
            Page<Order> page = orderRepository.findAll(pageRequest);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("查询订单失败");
        }
    }

    @Override
    public Page<Order> listByAccount(OrderRequestDTO requestDTO) {
        try {
            return orderRepository.findByCreateByAndChannelId(requestDTO.getAccount(), requestDTO.getClientId(),
                    new PageRequest(requestDTO.getPageRequest().getCurrentPage(),
                            requestDTO.getPageRequest().getPageSize(), Sort.Direction.DESC, requestDTO.getPageRequest().getOrder()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("用户订单收索失败");
        }
    }

    @Override
    public Page<OrderRefund> findRefundPage(Map<String, Object> searchParams, PageRequest pageRequest) throws OrderServiceException {
        try {
            Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
            Specification<OrderRefund> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), OrderRefund.class);
            return orderRefundRepository.findAll(spec, pageRequest);
        } catch (OrderServiceException e) {
            log.error("error", e.getStackTrace());
            throw new OrderServiceException(e);
        }
    }

    @Override
    public OrderRefund getRefundDetail(String refundId) {
        return orderRefundRepository.findOne(refundId);
    }

    @Override
    public OrderRefund saveRefund(OrderRefund orderRefund) {
        return orderRefundRepository.save(orderRefund);
    }

    @Override
    public Order editOrder(Order order, OrderDeliverInfo orderDeliverInfo, OrderItem orderItem, OrderActionLog orderActionLog) throws OrderServiceException {

        try {
            deliverRepository.save(orderDeliverInfo);
            itemRepository.save(orderItem);
            orderActionLogRepository.save(orderActionLog);
            return orderRepository.save(order);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new OrderServiceException("订单编辑失败");
        }
    }

    //根据channel获取对应商品
    public Map splitChannel(ExchangeDTO exchangeDTO) {
        Map<Integer, List<OrderItemDetailDTO>> map = new HashMap<Integer, List<OrderItemDetailDTO>>();
        List<OrderItemDetailDTO> orderItemDetailDTOs = exchangeDTO.getOrderItemDetailDTOList();
        for (OrderItemDetailDTO orderItemDetailDTO : orderItemDetailDTOs) {
            Product product = productRepository.findOne(orderItemDetailDTO.getProductId());
            if (product != null) {
                orderItemDetailDTO.setImageUrl(product.getDefaultImageUrl());
                orderItemDetailDTO.setProductId(product.getId());
                orderItemDetailDTO.setProductName(product.getName());
                orderItemDetailDTO.setChannelId(product.getChannel().getId());
                orderItemDetailDTO.setProductPrice(product.getSellPrice());
                if (map.get(product.getChannel().getId()) != null) {
                    map.get(product.getChannel().getId()).add(orderItemDetailDTO);
                } else {
                    List<OrderItemDetailDTO> list = new ArrayList<OrderItemDetailDTO>();
                    list.add(orderItemDetailDTO);
                    map.put(product.getChannel().getId(), list);
                }
            }
        }
        return map;
    }
}
