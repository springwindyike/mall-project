package com.ishare.mall.api.service.pay.impl;

import com.ishare.mall.api.service.BaseService;
import com.ishare.mall.api.service.pay.AliPayService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Created by YinLin on 2015/9/24.
 * Description :
 * Version 1.0
 */
@Service
public class AliPayServiceImpl extends BaseService implements AliPayService {

    private static final Logger log = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String create(String accessToken, OrderDetailDTO order) {
        AliPayDTO aliPayDTO = this.createAliPayDTO(accessToken, order);
        ResponseEntity<Response> responseHtml;
        try {
            responseHtml = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.AliPay.REQUEST_MAPPING, APPURIConstant.AliPay.REQUEST_MAPPING_CREATE_PAY_HTML), aliPayDTO, Response.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("创建支付宝支付失败！", HttpStatus.BAD_REQUEST);
        }

        Response payHtml = responseHtml.getBody();

        if (!payHtml.isSuccess() || payHtml.getData() == null) {
            throw new ApiLogicException("创建支付宝支付失败！", HttpStatus.BAD_REQUEST);
        }
        return (String)payHtml.getData();
    }

    /**
     * 创建支付DTO
     * @param token 认证token
     * @param order order表单
     * @return
     */
    private AliPayDTO createAliPayDTO(String token, OrderDetailDTO order) {
        AliPayDTO aliPayDTO = new AliPayDTO();
        aliPayDTO.setAmount(new BigDecimal(order.getPayableFee()));
        aliPayDTO.setOrderID(order.getOrderId());
        aliPayDTO.setGoodsName(order.getOrderId());
        aliPayDTO.setReturnUrl("127.0.0.1");
        return  aliPayDTO;
    }

    public static Logger getLog() {
        return log;
    }
}
