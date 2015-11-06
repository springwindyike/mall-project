package com.ishare.mall.common.base.enumeration;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 订单状态
 * Version 1.0
 */
public enum OrderState {
    /** 已取消 */
    CANCEL {public String getName(){return "已取消";}},
    /** 待审核 */
    WAIT_CONFIRM {public String getName(){return "待收货";}},
    /** 等待付款 */
    WAIT_PAYMENT {public String getName(){return "等待付款";}},
    /** 等待发货 */
    WAIT_DELIVER {public String getName(){return "等待发货";}},
    /** 已发货 */
    DELIVERED {public String getName(){return "已发货";}},
    /** 已收货 */
    RECEIVED {public String getName(){return "已收货";}};

    public abstract String getName();
}
