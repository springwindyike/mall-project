package com.ishare.mall.api.form.pay;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YinLin on 2015/8/27.
 * Description : 支付form
 * Version 1.0
 */

public class PayForm {
    @NotEmpty(message = "token不能为空")
    private String access_token;
    @NotEmpty(message = "订单id不能为空")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
