package com.ishare.mall.api.service.pay;

import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.dto.pay.AliRefundNotifyDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;

import java.util.Map;

/**
 * Created by YinLin on 2015/9/24.
 * Description :
 * Version 1.0
 */
public interface AliPayService {
    String create(String accessToken, OrderDetailDTO order) throws ApiLogicException;

    /**
     * 支付宝回调通知
     */
    void back();

    /**
     * 检测订单是否正确
     * @param notify
     * @param refund
     * @return
     */
    boolean verifyNotifyUrl(AliPayNotifyDTO notify, AliRefundNotifyDTO refund);


    boolean verifyNotifySign(Map<String, String> params, String sign);
}
