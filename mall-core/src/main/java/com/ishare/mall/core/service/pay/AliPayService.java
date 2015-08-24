package com.ishare.mall.core.service.pay;

import com.ishare.mall.common.base.dto.pay.AliPayDTO;

/**
 * Created by YinLin on 2015/8/14.
 * Description : 支付宝支付接口
 * Version 1.0
 */
public interface AliPayService {
    /**
     * 构建支付宝提交Form
     * @return
     */
    String create(AliPayDTO aliPayDTO);
}
