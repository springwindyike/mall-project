package com.ishare.mall.api.service.channel.impl;

import com.ishare.mall.api.service.BaseService;
import com.ishare.mall.api.service.channel.ChannelService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by YinLin on 2015/9/25.
 * Description :
 * Version 1.0
 */
@Service
public class ChannelServiceImpl extends BaseService implements ChannelService {

    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ChannelTokenResultDTO findByAppId(String id) throws ApiLogicException {
        ResponseEntity<Response<ChannelTokenResultDTO>> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_ID),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Response<ChannelTokenResultDTO>>() {
                    }, id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("渠道未找到", HttpStatus.NOT_FOUND);
        }

        Response<ChannelTokenResultDTO> response = responseEntity.getBody();

        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("渠道未找到", HttpStatus.NOT_FOUND);
        }

        return response.getData();
    }

    @Override
    public ChannelTokenResultDTO findByAppSecret(String secret) throws ApiLogicException {
        ResponseEntity<Response<ChannelTokenResultDTO>> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_SECRET),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Response<ChannelTokenResultDTO>>() {
                    }, secret);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("渠道未找到", HttpStatus.NOT_FOUND);
        }

        Response<ChannelTokenResultDTO> response = responseEntity.getBody();

        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("渠道未找到", HttpStatus.NOT_FOUND);
        }

        return response.getData();
    }
}
