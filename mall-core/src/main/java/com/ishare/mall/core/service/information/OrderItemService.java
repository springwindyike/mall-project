package com.ishare.mall.core.service.information;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.form.AddOrderItemForm;
import com.ishare.mall.core.model.order.OrderItem;

/**
 * Created by liaochenglei on 2015/8/18.
 * Description:
 * Version 1.0
 */
public abstract interface OrderItemService {

	public abstract OrderItem findOne(Integer id);
	public abstract Page<OrderItem> search(Map<String, Object> searchParams, PageRequest pageRequest);
	List<OrderItem> createNewOrderItems(List<OrderItem> orderItemsList);
	OrderItem createNewOrderItem(AddOrderItemForm addOrderItem);
	List<OrderItem> findByOrderId(String orderId);
}
