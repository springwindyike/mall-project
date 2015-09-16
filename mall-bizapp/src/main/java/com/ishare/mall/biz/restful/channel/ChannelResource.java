package com.ishare.mall.biz.restful.channel;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/1.
 * Description : 渠道相关APP
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Channel.REQUEST_MAPPING)
public class ChannelResource {

    @Autowired
    private ChannelService channelService;
    /**
     * 通过appId获取订单
     * @param id
     * @return
     */
    @RequestMapping(value       = APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_ID + APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_ID_PARAM,
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json", "application/xml"})
    public ChannelTokenResultDTO getByAppId(@NotEmpty @PathVariable("id")String id) {
        Channel channel = channelService.findByAppId(id);
        if (channel != null) {
            return (ChannelTokenResultDTO)MapperUtils.map(channel, ChannelTokenResultDTO.class);
        }
        return null;
    }

    /**
     * 通过appSecret获取订单
     * @param secret
     * @return
     */
    @RequestMapping(value       = APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_SECRET + APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_SECRET_PARAM,
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json", "application/xml"})
    public ChannelTokenResultDTO getByAppSecret(@NotEmpty @PathVariable("secret") String secret) {
        Channel channel = channelService.findByAppSecret(secret);
        if (channel != null) {
            return (ChannelTokenResultDTO)MapperUtils.map(channel, ChannelTokenResultDTO.class);
        }
        return null;
    }
}
