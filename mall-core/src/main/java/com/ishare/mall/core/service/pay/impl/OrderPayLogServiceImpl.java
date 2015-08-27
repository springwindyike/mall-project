package com.ishare.mall.core.service.pay.impl;

import com.ishare.mall.core.model.pay.OrderPayLog;
import com.ishare.mall.core.repository.pay.OrderPayLogRepository;
import com.ishare.mall.core.service.pay.OrderPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class OrderPayLogServiceImpl implements OrderPayLogService {
    @Autowired
    private OrderPayLogRepository repository;
    @Override
    public OrderPayLog findByOrderId(String orderId) {
        List<OrderPayLog> logs = repository.findByOrderId(orderId);
        return logs != null && logs.size() > 0 ? logs.get(0) : null;
    }
}
