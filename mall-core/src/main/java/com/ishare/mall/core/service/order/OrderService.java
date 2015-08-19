package com.ishare.mall.core.service.order;

import java.util.List;

import com.ishare.mall.core.model.order.Order;

public interface OrderService {
	
	Order findOne(String id);
	
	List<Order> findTotalSales();
}
