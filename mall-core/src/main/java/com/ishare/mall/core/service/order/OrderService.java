package com.ishare.mall.core.service.order;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

import com.ishare.mall.core.model.order.Order;

public interface OrderService {
	
	Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest);
	
	Order findOne(String id);

	List<Order> findTotalSales();
}
