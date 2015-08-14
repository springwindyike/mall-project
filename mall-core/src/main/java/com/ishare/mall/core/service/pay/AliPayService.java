package com.ishare.mall.core.service.pay;

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
    String create();
}
