package com.ishare.mall.core.service.pay;

import com.ishare.mall.core.model.pay.OrderPayLog;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */
public interface OrderPayLogService {
    OrderPayLog findByOrderId(String orderId);
    OrderPayLog save(OrderPayLog orderPayLog);
    OrderPayLog updateForProcess(OrderPayLog orderPayLog);
}
