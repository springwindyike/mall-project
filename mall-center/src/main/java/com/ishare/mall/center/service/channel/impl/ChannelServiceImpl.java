package com.ishare.mall.center.service.channel.impl;

import com.ishare.mall.center.service.base.BaseService;
import com.ishare.mall.center.service.channel.ChannelService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2015/11/16.
 */

@Service
public class ChannelServiceImpl extends BaseService implements ChannelService {

    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ChannelDTO findByChannelId(Integer id){
        ResponseEntity<Response<ChannelDTO>> responseEntity;
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(id);
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_FIND_BY_ID),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ChannelDTO>>() {
                    });
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new ApiLogicException("供应商为找到", HttpStatus.NOT_FOUND);
        }
        Response<ChannelDTO> response = responseEntity.getBody();
        if(!response.isSuccess() || response.getData() == null){
            throw new ApiLogicException("供应商为找到", HttpStatus.NOT_FOUND);
        }
        return response.getData();
    }
}
