package com.ishare.mall.core.service.pay.impl;

import com.ishare.mall.common.base.constant.PayConstant;
import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.dto.pay.AliRefundNotifyDTO;
import com.ishare.mall.core.service.pay.AliPayService;
import com.ishare.mall.core.utils.pay.AlipayCore;
import com.ishare.mall.core.utils.pay.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ishare.mall.common.base.constant.CommonConstant.CharSet.UTF8;

/**
 * Created by YinLin on 2015/8/14.
 * Description :
 * Version 1.0
 */
@Service
public class AliPayServiceImpl implements AliPayService {

    private final static Logger log = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Value(PayConstant.AliPay.GATE_WAY_URL)
    private String gatewayUrl;

    @Value(PayConstant.AliPay.PARTNER)
    private String partner;

    @Value(PayConstant.AliPay.KEY)
    private String key;

    @Value(PayConstant.AliPay.NOTIFY_URL)
    private String notifyUrl;

    @Value(PayConstant.AliPay.REFUND_NOTIFY_URL)
    private String refundNotifyUrl;

    @Value(PayConstant.AliPay.SELLER_EMAIL)
    private String sellerEmail;

    @Value(PayConstant.AliPay.RSA_PUBLIC_KEY)
    private String rsaPublicKey;

    @Value(PayConstant.AliPay.RSA_PRIVATE_KEY)
    private String rsaKey;

    @Override
    public String create(AliPayDTO aliPayDTO) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", UTF8);
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", notifyUrl);
        sParaTemp.put("return_url", aliPayDTO.getReturnUrl());
        sParaTemp.put("seller_email", sellerEmail);
        sParaTemp.put("out_trade_no", aliPayDTO.getOrderID());
        sParaTemp.put("subject", aliPayDTO.getGoodsName());
        sParaTemp.put("total_fee", aliPayDTO.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString());
        return this.buildRequest(sParaTemp, "POST", "支付");
    }

    private String buildPayForm() {
        return null;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     *
     * @param sParaTemp     请求参数数组
     * @param strMethod     提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    private String buildRequest(Map<String, String> sParaTemp,
                                String strMethod, String strButtonName) {
        // 待请求参数数组
        Map<String, String> sPara = this.buildRequestParameter(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<form target=\"_self\" id=\"aliPaySubmit\" name=\"alipaysubmit\" action=\""
                + gatewayUrl
                + "_input_charset="
                + UTF8
                + "\" method=\"" + strMethod + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name
                    + "\" value=\"" + value + "\"/>");
        }
        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName
                + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['aliPaySubmit'].submit();</script>");
        log.debug(sbHtml.toString());
        return sbHtml.toString();
    }

    private Map<String, String> buildRequestParameter(Map<String, String> parameter) {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(parameter);
        // 生成签名结果
        String mySign = this.buildRequestMySign(sPara);
        //签名结果与签名方式加入请求提交参数数组
        sPara.put("sign", mySign);

        sPara.put("sign_type", PayConstant.AliPay.SIGN_TYPE);
        return sPara;
    }

    /**
     * 生成
     * @param sPara
     * @return
     */
    private String buildRequestMySign(Map<String, String > sPara) {
        String preStr = AlipayCore.createLinkString(sPara);
        String mySign = "";
        mySign = MD5.sign(preStr, key, UTF8);
        return mySign;
    }

    /**
     * 去阿里验证id是否有效
     * @param notify 支付验证
     * @param refund 退款验证
     * @return
     */
    @Override
    public boolean verifyNotifyUrl(AliPayNotifyDTO notify, AliRefundNotifyDTO refund) {
        // 首先验证notify ID是否有效
        RestTemplate restTemplate = new RestTemplate();
        String verifyNotifyUrl = "https://mapi.alipay.com/gateway.do?service={notify_verify}&partner={partner}&notify_id={notify_id}";
        String notifyId = "";
        if(null != notify){
            notifyId = notify.getNotify_id();
        }else if(null != refund){
            notifyId = refund.getNotify_id();
        }
        String verifyNotify = restTemplate.getForObject(verifyNotifyUrl,String.class, "notify_verify", partner, notifyId);
        if (verifyNotify == null || !"true".equals(verifyNotify)) {
            log.info("notify from aliPay, notify verify failure, verifyNotify={}, notify={}", new Object[]{verifyNotify, notifyId});
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

}
