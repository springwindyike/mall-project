package com.ishare.mall.controller;

import com.google.common.collect.Lists;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.order.OrderService;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YinLin on 2015/7/30.
 * Description: Test Demo 这个Api的Demo类
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private ChannelService channelService;
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "success";
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public Product show() {
        Product product = new Product();
        product.setBasePrice(10.0f);
        product.setCode("1001001000");
        return product;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> list() {
        List<Product> products = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setCode("10001010" + (i + 1));
            product.setName("衣服");
            products.add(product);
        }
        return products;
    }
    @RequestMapping(value = "/channels/a/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Channel getByA(@NotEmpty @PathVariable("id")String appId) {
        return channelService.findByAppId(appId);
    }
    @RequestMapping(value = "/channels/s/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Channel getBys(@NotEmpty @PathVariable("id")String appId) {
        return channelService.findByAppSecret(appId);
    }
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@NotEmpty @PathVariable("id") String orderId) {
    	return orderService.findOne(orderId);
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(TestForm form) {
        if (form != null) {
            log.debug(form.getId());
            log.debug(form.getName());
            log.debug(form.getGender().getName());
        }
        return form;
    }
}
