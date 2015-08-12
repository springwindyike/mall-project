package com.ishare.mall.core.service.information;

import com.ishare.mall.core.model.information.Channel;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface ChannelService {
    Channel findOne(Integer id);
    Channel findByAppId(String appId);
    Channel finaByAppSecret(String appSecret);
}
