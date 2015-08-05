package com.ishare.mall.old.service;

import com.ishare.mall.old.model.DeliveryAddress;

import java.util.List;


public interface DeliverAddrService {
	public DeliveryAddress addDeliverAddr(DeliveryAddress deliveryAddress);
	public void deleteDeliverAddr(long id);
	public void updateDeliverAddr(DeliveryAddress deliveryAddress);
	public List<DeliveryAddress> listDeliverAddr();
}
