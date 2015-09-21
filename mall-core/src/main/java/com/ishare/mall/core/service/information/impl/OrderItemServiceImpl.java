package com.ishare.mall.core.service.information.impl;

import com.ishare.mall.common.base.enumeration.OrderItemSort;
import com.ishare.mall.core.form.AddOrderItemForm;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.repository.information.OrderItemRepository;
import com.ishare.mall.core.repository.order.OrderRepository;
import com.ishare.mall.core.service.information.OrderItemService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by YinLin on 2015/8/10.
 * Description: 商品类别业务逻辑表
 * Version 1.0
 */
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private static final Logger log = LoggerFactory.getLogger(OrderItemServiceImpl.class);
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<OrderItem> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OrderItem> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), OrderItem.class);
        Page<OrderItem> page = orderItemRepository.findAll(spec, pageRequest);
       	log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    public Logger getLog() {
        return log;
    }

	@Override
	public OrderItem findOne(Integer id) {
		return orderItemRepository.findOne(id);
	}

	@Override
	public List<OrderItem> createNewOrderItems(List<OrderItem> orderItemsList) {
		return orderItemRepository.save(orderItemsList);
	}

	@Override
	public OrderItem createNewOrderItem(AddOrderItemForm orderItems) {
		Order order = orderRepository.findOne(orderItems.getOrderId());
		if(null == order){
//			OrderService orderService = new OrderServiceImpl();
//			Order newOrder = buildOrder(orderItems);
//			orderService.createNewOrder(order);
			return null;
		}
		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setAmount(orderItems.getAmount());
		newOrderItem.setExchangeOrBack(OrderItemSort.BACK);
		newOrderItem.setId(orderItems.getId());
		newOrderItem.setImageUrl(orderItems.getImageUrl());
		newOrderItem.setOrder(order);
		newOrderItem.setProductId(orderItems.getProductId());
		newOrderItem.setProductName(orderItems.getProductName());
		newOrderItem.setProductPrice(orderItems.getProductPrice());
		newOrderItem.setState(orderItems.getState());
		//newOrderItem.setStyleId(orderItems.getStyleId());
		newOrderItem.setStyleName(orderItems.getStyleName());
		return orderItemRepository.save(newOrderItem);
	}

//	public Order buildOrder(AddOrderItemForm orderItems) {
//		Order newOrder = new Order();
//		orderItems.
//		return newOrder;
//	}
}
