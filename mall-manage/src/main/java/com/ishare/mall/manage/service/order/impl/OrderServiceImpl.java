package com.ishare.mall.manage.service.order.impl;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2015/11/23.
 */
@Service
public class OrderServiceImpl extends BaseService implements OrderService{
    @Autowired
    private RestTemplate restTemplate;
    public long findcount() {
        ResponseEntity<Response<Long>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPOMG_COUNT),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
                });
        Response<Long> response = responseEntity.getBody();
        return response.getData();
    }

}
