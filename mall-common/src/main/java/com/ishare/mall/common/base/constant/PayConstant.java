package com.ishare.mall.common.base.constant;

/**
 * Created by YinLin on 2015/8/18.
 * Description :
 * Version 1.0
 */
public interface PayConstant {
    //支付宝相关
    interface AliPay {
        String SIGN_TYPE            = "MD5";
        String GATE_WAY_URL         = "#{aliPaySettings['ali.pay.gate.way.url']}";
        String PARTNER              = "#{aliPaySettings['ali.pay.partner']}";
        String KEY                  = "#{aliPaySettings['ali.pay.key']}";
        String NOTIFY_URL           = "#{aliPaySettings['ali.pay.notify.url']}";
        String REFUND_NOTIFY_URL    = "#{aliPaySettings['ali.pay.refund.notify.url']}";
        String SELLER_EMAIL         = "#{aliPaySettings['ali.pay.seller.email']}";
        String RSA_PUBLIC_KEY       = "#{aliPaySettings['ali.pay.rsa.public.key']}";
        String RSA_PRIVATE_KEY      = "#{aliPaySettings['ali.pay.rsa.private.key']}";
    }
}
