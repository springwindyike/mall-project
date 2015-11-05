package com.ishare.mall.test.repository;

import com.ishare.mall.common.base.enumeration.PaymentWay;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YinLin on 2015/9/18.
 * Description :
 * Version 1.0
 */
public class OrderServiceTest extends RepositoryTestTemplate {
    @Autowired
    private OrderService orderService;

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Override
    public void testCreate() {

    }

    @Override
    public void testRetrieve() {
        Map param = new HashMap();
        param.put("EQ_paymentWay", PaymentWay.NET);
        Page<Order> orders = orderService.search(param, null);
        log.debug(orders.getTotalElements() + "");
    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testDelete() {

    }
}
