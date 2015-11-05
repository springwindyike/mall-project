package com.ishare.mall.manage.form;

import com.ishare.mall.common.base.enumeration.OrderState;
import com.ishare.mall.common.base.enumeration.PaymentWay;

/**
 * Created by wanghao on 2015/11/4.
 */
public class SearchForm {


    private String orderId;
    private String channelName;
    private OrderState status;
    private String datemin;
    private String datemax;
    private String createBy;
    private PaymentWay payWay;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDatemin() {
        return datemin;
    }

    public void setDatemin(String datemin) {
        this.datemin = datemin;
    }

    public String getDatemax() {
        return datemax;
    }

    public void setDatemax(String datemax) {
        this.datemax = datemax;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    public PaymentWay getPayWay() {
        return payWay;
    }

    public void setPayWay(PaymentWay payWay) {
        this.payWay = payWay;
    }
}
