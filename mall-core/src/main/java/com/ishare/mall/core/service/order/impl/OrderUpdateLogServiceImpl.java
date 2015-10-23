package com.ishare.mall.core.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.OrderActionLog;
import com.ishare.mall.core.repository.order.OrderActionLogRepository;
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
    private OrderActionLogRepository repository;

    @Override
    public OrderActionLog save(OrderActionLog orderActionLog) {
        return repository.save(orderActionLog);
    }

}
