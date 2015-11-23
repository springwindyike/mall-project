package com.ishare.mall.manage.service.product.impl;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.service.base.BaseService;
import com.ishare.mall.manage.service.product.ProdcutService;
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
public class ProductServiceImpl extends BaseService implements ProdcutService {
    @Autowired
    private RestTemplate restTemplate;

    public long findCount() {
        ResponseEntity<Response<Long>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_COUNT),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
                });
        Response<Long> response = responseEntity.getBody();
        return response.getData();
    }

    /**
     * 本周新增商品数量
     * @return
     */
    public long findThisWeekCount(){
        ResponseEntity<Response<Long>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_ALL_THISWEEK_COUNT),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
                });
        Response<Long> response = responseEntity.getBody();
        return response.getData();
    }
}