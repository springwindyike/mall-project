package com.ishare.mall.core.service.order;

import com.ishare.mall.core.model.order.GeneratedOrderId;


public interface GeneratedOrderIdService {
	
	
	GeneratedOrderId findOne(String id);
	
	GeneratedOrderId createNewGeneratedOrderId(GeneratedOrderId generatedOrderId);

	int updateOrderId(String id, String orderId);
}
