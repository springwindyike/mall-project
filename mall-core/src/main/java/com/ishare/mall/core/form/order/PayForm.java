package com.ishare.mall.core.form.order;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */

public class PayForm {
    @NotEmpty(message = "token不能为空")
    private String token;
    @NotEmpty(message = "订单id不能为空")
    private String orderId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
