package com.ishare.mall.core.service.order;

import com.ishare.mall.core.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface OrderService {
	
	Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest);
	
	Order findOne(String id);

	List<Order> findTotalSales();
	
	List<Order> findByCreateBy(String createBy);
	
	Order createNewOrder(Order order);

	/**
	 * 支付完成设置状态为等待发货状态
	 * @return
	 */
	Order payComplete(String orderId);
}
