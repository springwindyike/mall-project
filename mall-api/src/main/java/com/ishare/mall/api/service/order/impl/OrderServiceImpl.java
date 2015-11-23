package com.ishare.mall.api.service.order.impl;

import com.ishare.mall.api.service.BaseService;
import com.ishare.mall.api.service.order.OrderService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderRequestDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.page.PageRequestDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
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

import java.util.List;

/**
 * Created by YinLin on 2015/9/24.
 * Description : 客户端
 * Version 1.0
 */

@Service
public class OrderServiceImpl extends BaseService implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderDetailDTO findOne(String id) throws ApiLogicException {

        ResponseEntity<Response<OrderDetailDTO>> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_ID),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Response<OrderDetailDTO>>() {
                    }, id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("订单未找到", HttpStatus.NOT_FOUND);
        }

        Response<OrderDetailDTO> response = responseEntity.getBody();

        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("订单未找到", HttpStatus.NOT_FOUND);
        }

        return response.getData();
    }

    @Override
    public List<OrderDetailDTO> create(ExchangeDTO exchangeDTO) throws ApiLogicException {
        ResponseEntity<Response<List<OrderDetailDTO>>> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_CREATE),
                    HttpMethod.POST, new HttpEntity<ExchangeDTO>(exchangeDTO), new ParameterizedTypeReference<Response<List<OrderDetailDTO>>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("订单创建失败", HttpStatus.BAD_REQUEST);
        }

        Response<List<OrderDetailDTO>> response = responseEntity.getBody();

        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("订单创建失败", HttpStatus.BAD_REQUEST);
        }

        return response.getData();
    }

    @Override
    public OrderDetailDTO payComplete(AliPayNotifyDTO notify) throws ApiLogicException {
        ResponseEntity<Response<OrderDetailDTO>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_PAY_BACK),
                    HttpMethod.POST, new HttpEntity<AliPayNotifyDTO>(notify), new ParameterizedTypeReference<Response<OrderDetailDTO>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("未找到订单", HttpStatus.BAD_REQUEST);
        }

        Response<OrderDetailDTO> response = responseEntity.getBody();

        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("未找到订单", HttpStatus.BAD_REQUEST);
        }

        return response.getData();
    }

    @Override
    public Response<PageDTO<OrderDetailDTO>> listByAccount(String account, String clientId, PageRequestDTO pageRequest) {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setAccount(account);
        requestDTO.setClientId(clientId);
        requestDTO.setPageRequest(pageRequest);
        ResponseEntity<Response<PageDTO<OrderDetailDTO>>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_ACCOUNT_AND_APP_ID),
                    HttpMethod.POST, new HttpEntity<OrderRequestDTO>(requestDTO), new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("未找到订单", HttpStatus.BAD_REQUEST);
        }

        Response<PageDTO<OrderDetailDTO>> response = responseEntity.getBody();

        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("未找到订单", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
