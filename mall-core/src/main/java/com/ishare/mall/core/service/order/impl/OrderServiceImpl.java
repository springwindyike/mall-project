package com.ishare.mall.core.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.repository.order.OrderRepository;
import com.ishare.mall.core.service.order.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Override
	public Order findOne(String id) {
		return orderRepository.findOne(id);
	}

}
