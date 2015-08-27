package com.ishare.mall.common.base.dto.pay;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * Created by YinLin on 2015/8/27.
 * Description : 通知第三方支付情况
 * Version 1.0
 */
public class NotifyChannelDTO extends GenericDTO {
    private static final long serialVersionUID = -2601175208254084537L;
    private String notify_time; //通知时间
    private String notify_type; //通知类型
    private String notify_id;  //通知ID
    private String total_fee;  //总共的费用
    private String order_id;    //订单号
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
