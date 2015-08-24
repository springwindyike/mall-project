package com.ishare.mall.core.service.deliver.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.OrderDeliverInfo;
import com.ishare.mall.core.repository.deliver.DeliverRepository;
import com.ishare.mall.core.service.deliver.DeliverService;

/**
 * Created by liaochenglei on 2015/8/24.
 * Description: 收货地址增删改
 * Version 1.0
 */
@Service
@Transactional
public class DeliverServiceImpl implements DeliverService {

    private static final Logger log = LoggerFactory.getLogger(DeliverServiceImpl.class);
    @Autowired
    private DeliverRepository deliverRepository;

	@Override
	public OrderDeliverInfo save(OrderDeliverInfo orderDeliverInfo) {
		 return deliverRepository.save(orderDeliverInfo);
	}

	public void delete(Integer id) {
		  try {
			deliverRepository.delete(id);
		} catch (Exception e) {
			log.error("删除失败");
		}
	}
	
    public Logger getLog() {
        return log;
    }
}
