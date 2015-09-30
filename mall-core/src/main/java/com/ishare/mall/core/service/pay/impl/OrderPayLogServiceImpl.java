package com.ishare.mall.core.service.pay.impl;

import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.enumeration.CostType;
import com.ishare.mall.common.base.enumeration.PayType;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.model.pay.OrderPayLog;
import com.ishare.mall.core.repository.pay.OrderPayLogRepository;
import com.ishare.mall.core.service.pay.OrderPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public OrderPayLog save(OrderPayLog orderPayLog) {
        return repository.save(orderPayLog);
    }

    @Override
    public OrderPayLog updateForProcess(OrderPayLog orderPayLog) {
        orderPayLog.setFinishTime(new Date());
        return updateForPayType(orderPayLog, PayType.PROCESS);
    }

    @Override
    public OrderPayLog create(AliPayDTO aliPayDTO) {
        OrderPayLog orderPayLog = findByOrderId(aliPayDTO.getOrderID());
        if (orderPayLog != null && orderPayLog.getPayType() == PayType.PAY) {
            throw new OrderServiceException("日志创建失败订单已支付");
        }
        if (orderPayLog == null) {
            orderPayLog = new OrderPayLog();
            orderPayLog.setOrderId(aliPayDTO.getOrderID());
            orderPayLog.setCreateTime(new Date());
            orderPayLog.setCostType(CostType.MEMBER);
            orderPayLog.setPayType(PayType.NEW);
            this.save(orderPayLog);
        }
        return orderPayLog;
    }

    /**
     * 更新支付日志记录type
     */
    private OrderPayLog updateForPayType(OrderPayLog orderPayLog, PayType type) {
        if (orderPayLog != null) {
            orderPayLog.setPayType(type);
            return repository.save(orderPayLog);
        }
        return null;
    }
}
