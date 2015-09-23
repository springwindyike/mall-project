package com.ishare.mall.biz.restful.order;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.service.pay.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by YinLin on 2015/9/23.
 * Description : 支付宝相关接口
 * Version 1.0
 */
@Controller(APPURIConstant.AliPay.REQUEST_MAPPING)
public class AliPayResource {

    @Autowired
    private AliPayService aliPayService;

    private Logger log = LoggerFactory.getLogger(AliPayResource.class);

    @RequestMapping(value       = APPURIConstant.AliPay.REQUEST_MAPPING_CREATE_PAY_HTML,
                    method      = RequestMethod.POST,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json", "application/xml"},
                    consumes    = {"application/json", "application/xml"})
    public Response create(@RequestBody AliPayDTO aliPayDTO) {
        Response response = new Response();
        response.setCode(200);
        //response.setData(aliPayService.create(aliPayDTO));
        return response;
    }

    public AliPayService getAliPayService() {
        return aliPayService;
    }

    public void setAliPayService(AliPayService aliPayService) {
        this.aliPayService = aliPayService;
    }
}
