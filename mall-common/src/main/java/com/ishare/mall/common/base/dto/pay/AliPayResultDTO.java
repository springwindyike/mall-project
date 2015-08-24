package com.ishare.mall.common.base.dto.pay;

/**
 * Created by YinLin on 2015/8/24.
 * Description :
 * Version 1.0
 */
public class AliPayResultDTO {

    private boolean success;

    private String payFormHtml;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPayFormHtml() {
        return payFormHtml;
    }

    public void setPayFormHtml(String payFormHtml) {
        this.payFormHtml = payFormHtml;
    }
}
