package com.ishare.mall.biz.restful.order;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.enumeration.PayType;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.pay.OrderPayLog;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.service.pay.AliPayService;
import com.ishare.mall.core.service.pay.OrderPayLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YinLin on 2015/9/23.
 * Description : 支付宝相关接口
 * Version 1.0
 */
@Controller(APPURIConstant.AliPay.REQUEST_MAPPING)
public class AliPayResource {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private OrderPayLogService orderPayLogService;

    @Autowired
    private OrderService orderService;

    private Logger log = LoggerFactory.getLogger(AliPayResource.class);

    @RequestMapping(value       = APPURIConstant.AliPay.REQUEST_MAPPING_CREATE_PAY_HTML,
                    method      = RequestMethod.POST,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json", "application/xml"},
                    consumes    = {"application/json", "application/xml"})
    @ResponseBody
    public Response create(@RequestBody AliPayDTO aliPayDTO) {
        Response response = new Response();
        response.setCode(200);
        response.setData(aliPayService.create(aliPayDTO));
        return response;
    }

    /**
     * 阿里支付回调
     * @return
     */
    @RequestMapping(value = APPURIConstant.AliPay.REQUEST_MAPPING_NOTIFY, method = RequestMethod.POST)
    public Object payNotify(AliPayNotifyDTO notify, HttpServletRequest request) {
        if(!aliPayService.verifyNotifyUrl(notify, null)) {
            return null;
        }
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //如果校验码为空
        if (StringUtils.isBlank(notify.getSign())) {
            log.info("notify from aliPay, sign is null, notify={}, params={}",
                    new Object[]{notify, params});
            return null;
        }
        if (aliPayService.verifyNotifySign(params, notify.getSign())) {
            if (isPaySuccess(notify)) {
                OrderPayLog payLog = orderPayLogService.findByOrderId(notify.getOut_trade_no());
                if (payLog != null && payLog.getPayType().equals(PayType.NEW)) {
                    payLog.setAmount(new BigDecimal(notify.getTotal_fee()));
                    payLog.setTansId(notify.getTrade_no());
                    payLog.setUpdateTime(new Date());
                    log.warn("pay from pc and pay by blank amount payment={}", "支付宝");
                    //更新支付log状态
                    orderPayLogService.updateForProcess(payLog);
                    //设置支付完成
                    orderService.payComplete(notify.getOut_trade_no());
                }
            }
        }
        return null;
    }

    /**
     * 简单验证支付是否成功
     * @param notify
     * @return
     */
    private boolean isPaySuccess(AliPayNotifyDTO notify) {
        return !StringUtils.isBlank(notify.getTrade_status()) && (notify.getTrade_status().equals("TRADE_FINISHED") || notify.getTrade_status().equals("TRADE_SUCCESS"));
    }


}
