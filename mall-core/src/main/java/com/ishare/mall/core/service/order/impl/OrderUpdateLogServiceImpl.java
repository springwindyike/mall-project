package com.ishare.mall.core.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.OrderUpdateLog;
import com.ishare.mall.core.repository.order.OrderUpdateLogRepository;
import com.ishare.mall.core.service.order.OrderUpdateLogService;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class OrderUpdateLogServiceImpl implements OrderUpdateLogService {
    @Autowired
    private OrderUpdateLogRepository repository;

    @Override
    public OrderUpdateLog save(OrderUpdateLog orderUpdateLog) {
        return repository.save(orderUpdateLog);
    }

}
