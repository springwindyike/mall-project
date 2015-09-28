package com.ishare.mall.core.service.order;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.model.order.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface OrderService {
	
	/**
	 * 搜索Order
	 * @param searchParams
	 * @param pageRequest
	 * @return
	 * @throws OrderServiceException
	 */
	Page<Order> search(Map<String, Object> searchParams, PageRequest pageRequest) throws OrderServiceException;
	/**
	 * 根据id获得Order
	 * @param id
	 * @return
	 * @throws OrderServiceException
	 */
	Order findOne(String id) throws OrderServiceException;
	/**
	 * 查询所有订单
	 * @return
	 * @throws OrderServiceException
	 */
	List<Order> findTotalSales() throws OrderServiceException;
	/**
	 * 根据创建者查询订单
	 * @param createBy
	 * @return
	 * @throws OrderServiceException
	 */
	List<Order> findByCreateBy(String createBy) throws OrderServiceException;
	/**
	 * 创建新订单
	 * @param order
	 * @return
	 * @throws OrderServiceException
	 */
	Order createNewOrder(Order order) throws OrderServiceException;

	/**
	 * 支付完成设置状态为等待发货状态
	 * @return
	 */
	Order payComplete(String orderId) throws OrderServiceException;

	/**
	 * 创建订单
	 * @param exchangeDTO
	 * @return
	 */
	OrderDetailDTO create(ExchangeDTO exchangeDTO) throws OrderServiceException;

	/**
	 * 确认订单
	 * @param id
	 * @return
	 */
	Order confirm(String id) throws OrderServiceException;

	/**
	 * 取消订单
	 * @param id
	 * @return
	 */
	Order cancel(String id) throws OrderServiceException;

	/**
	 * 根据channel id查询所有的Order
	 * @param channelId
	 * @param pageRequest
	 * @return
	 */
	Page<Order> findByChannelId(Integer channelId, PageRequest pageRequest) throws OrderServiceException;
	
	/**
	 * 更新订单
	 * @param logistics
	 * @return
	 * @throws OrderServiceException
	 */
	Order updateOrder(Order order, String note) throws OrderServiceException;
}
