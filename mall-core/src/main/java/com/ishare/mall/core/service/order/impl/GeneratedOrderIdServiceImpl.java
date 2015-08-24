package com.ishare.mall.core.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.GeneratedOrderId;
import com.ishare.mall.core.repository.order.GeneratedOrderIdRepository;
import com.ishare.mall.core.service.order.GeneratedOrderIdService;

@Service
@Transactional
public class GeneratedOrderIdServiceImpl implements GeneratedOrderIdService {
	
	@Autowired
	private GeneratedOrderIdRepository generatedOrderIdRepository;
	@Override
	public GeneratedOrderId findOne(String id) {
		return generatedOrderIdRepository.findOne(id);
	}
	@Override
	public GeneratedOrderId createNewGeneratedOrderId(
			GeneratedOrderId generatedOrderId) {
		return generatedOrderIdRepository.save(generatedOrderId);
	}
	@Override
	public int updateOrderId(String id, String orderId) {
		return generatedOrderIdRepository.setFixedOrderIdFor(id, orderId);
	}

}
