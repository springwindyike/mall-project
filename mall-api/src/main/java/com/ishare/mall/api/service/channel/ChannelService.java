package com.ishare.mall.api.service.channel;

import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;

/**
 * Created by YinLin on 2015/9/25.
 * Description :
 * Version 1.0
 */
public interface ChannelService {
    ChannelTokenResultDTO findByAppId(String id) throws ApiLogicException;
    ChannelTokenResultDTO findByAppSecret(String secret) throws ApiLogicException;
}
