package com.ishare.mall.core.service.pay;

import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.dto.pay.AliRefundNotifyDTO;
import com.ishare.mall.common.base.exception.service.pay.AliPayServiceException;

import java.util.Map;

/**
 * Created by YinLin on 2015/8/14.
 * Description : 支付宝支付接口
 * Version 1.0
 */
public interface AliPayService {

    /**
     * 构建支付宝提交Form
     * @return String
     */
    String create(AliPayDTO aliPayDTO) throws AliPayServiceException;

    /**
     * 支付宝支付成功或者退款成功返回结果验证
     * @param notify
     * @param refund
     * @return boolean
     */
    boolean verifyNotifyUrl(AliPayNotifyDTO notify, AliRefundNotifyDTO refund);

    /**
     * 根据反馈回来的信息，生成签名结果
     * @param params 通知返回来的参数数组
     * @param sign   比对的签名结果
     * @return 生成的签名结果
     */
    boolean verifyNotifySign(Map<String, String> params, String sign);
}
