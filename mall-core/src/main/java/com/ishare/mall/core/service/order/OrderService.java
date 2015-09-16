package com.ishare.mall.core.service.order;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.core.model.order.Order;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.model.order.Order;

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

	Order create(ExchangeDTO exchangeDTO);
	
	/**
	 * 根据channel id查询所有的Order
	 * @param channelId
	 * @param pageRequest
	 * @return
	 */
	Page<Order> findByChannelId(Integer channelId, PageRequest pageRequest);
}
