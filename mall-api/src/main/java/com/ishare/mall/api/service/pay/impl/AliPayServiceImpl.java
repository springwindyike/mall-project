package com.ishare.mall.api.service.pay.impl;

import com.ishare.mall.api.service.BaseService;
import com.ishare.mall.api.service.pay.AliPayService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.dto.pay.AliRefundNotifyDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.common.base.pay.AlipayCore;
import com.ishare.mall.common.base.pay.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

import static com.ishare.mall.common.base.constant.CommonConstant.CharSet.UTF8;

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
        log.debug(order.toString());
        log.debug(this.buildBizAppURI(APPURIConstant.AliPay.REQUEST_MAPPING, APPURIConstant.AliPay.REQUEST_MAPPING_CREATE_PAY_HTML));
        try {
            responseHtml = restTemplate.postForEntity(
                    this.buildBizAppURI(APPURIConstant.AliPay.REQUEST_MAPPING, APPURIConstant.AliPay.REQUEST_MAPPING_CREATE_PAY_HTML),
                    aliPayDTO,
                    Response.class);
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

    @Override
    public void back() {

    }

    @Override
    public boolean verifyNotifyUrl(AliPayNotifyDTO notify, AliRefundNotifyDTO refund) {
        // 首先验证notify ID是否有效
        String verifyNotifyUrl = "https://mapi.alipay.com/gateway.do?service={notify_verify}&partner={partner}&notify_id={notify_id}";
        String notifyId = "";
        if(null != notify){
            notifyId = notify.getNotify_id();
        }else if(null != refund){
            notifyId = refund.getNotify_id();
        }
        RestTemplate restTemplate = new RestTemplate();
        String verifyNotify = restTemplate.getForObject(verifyNotifyUrl, String.class, "notify_verify", partner, notifyId);

        if (verifyNotify == null || !"true".equals(verifyNotify)) {
            log.info("notify from aliPay, notify verify failure, verifyNotify={}, notify={}, partner={}", new Object[]{verifyNotify, notifyId, partner});
            return false;
        }
        return true;
    }

    @Override
    public boolean verifyNotifySign(Map<String, String> params, String sign) {
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = AlipayCore.paraFilter(params);
        // 获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        // 获得签名验证结果
        boolean isSign = false;
        isSign = MD5.verify(preSignStr, sign, key, UTF8);
        return isSign;
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
        aliPayDTO.setReturnUrl(returnUrl + "/" + token);
        return  aliPayDTO;
    }

    public static Logger getLog() {
        return log;
    }
}
