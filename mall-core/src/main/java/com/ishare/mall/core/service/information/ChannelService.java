package com.ishare.mall.core.service.information;

import java.util.Set;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.order.Order;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface ChannelService {
    Channel findOne(Integer id);
    Channel findByAppId(String appId);
    Channel findByAppSecret(String appSecret);
    Set<Order> findAllOrderByChannelId(Integer id);
}
