package com.ishare.mall.old.service.impl;

import com.ishare.mall.old.model.DeliveryAddress;
import com.ishare.mall.old.repository.DeliveryAddressRepository;
import com.ishare.mall.old.service.DeliverAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeliverAddrServiceImpl implements DeliverAddrService {

	@Autowired
	private DeliveryAddressRepository deliveryAddressRepository;

	/**
	 * 新增收货地址
	 */
	public DeliveryAddress addDeliverAddr(DeliveryAddress deliveryAddress) {
		DeliveryAddress save = deliveryAddressRepository.save(deliveryAddress);
		return save;
	}

	/**
	 * 删除收货地址
	 */
	public void deleteDeliverAddr(long id) {
		deliveryAddressRepository.delete(id);
	}

	/**
	 * 修改收货地址
	 */
	public void updateDeliverAddr(DeliveryAddress deliveryAddress) {
		deliveryAddressRepository.save(deliveryAddress);
	}

	/**
	 * 查询收货地址
	 */
	public List<DeliveryAddress> listDeliverAddr() {
		List<DeliveryAddress> findAll = deliveryAddressRepository.findAll();
		return findAll;
	}
}
