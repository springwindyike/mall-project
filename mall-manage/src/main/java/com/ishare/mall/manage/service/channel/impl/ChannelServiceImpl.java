package com.ishare.mall.manage.service.channel.impl;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.service.base.BaseService;
import com.ishare.mall.manage.service.channel.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
@Service
public class ChannelServiceImpl extends BaseService implements ChannelService{

    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取当前所有channel
     */
   public  List<ChannelDTO> findAll(){
       log.debug("findAll start");
       ResponseEntity<Response<List<ChannelDTO>>> responseEntity;
       responseEntity = restTemplate.exchange(
               this.buildBizAppURI( APPURIConstant.Channel.REQUEST_MAPPING,ManageURIConstant.Channel.REQUEST_MAPPING_FindThisWeek),
               HttpMethod.GET, null, new ParameterizedTypeReference<Response<List<ChannelDTO>>>() {
               });
       Response<List<ChannelDTO>> response = responseEntity.getBody();
       return response.getData();
   }

   public  Long findCount(){
       log.debug("findAll start");
       ResponseEntity<Response<Long>> responseEntity;
       responseEntity = restTemplate.exchange(
               this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_COUNT),
               HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
               });
       Response<Long> response = responseEntity.getBody();
       return response.getData();
    }
    /**
     * 获取本周新增会员数量
     */
   public  Long findThisWeekCount(){
        log.debug("findAll start");
        ResponseEntity<Response<Long>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_THISWEEK_COUNT),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
                });
        Response<Long> response = responseEntity.getBody();
        return response.getData();
    }
}
