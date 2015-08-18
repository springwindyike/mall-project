package com.ishare.mall.core.service.order;

import com.ishare.mall.core.model.order.Order;

public interface OrderService {
	Order findOne(String id);
}
