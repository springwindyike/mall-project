package com.ishare.mall.center.service.product.impl;

import com.ishare.mall.center.service.base.BaseService;
import com.ishare.mall.center.service.product.ProductService;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
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
 * Created by Administrator on 2015/11/12.
 */
@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductDetailDTO findOne(Integer id) {
        ResponseEntity<Response<ProductDetailDTO>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI("welcome", "/{id}"),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Response<ProductDetailDTO>>() {
                    }, id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("商品未找到", HttpStatus.NOT_FOUND);
        }
        Response<ProductDetailDTO> response = responseEntity.getBody();
        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("商品未找到", HttpStatus.NOT_FOUND);
        }
        return response.getData();
    }
}
