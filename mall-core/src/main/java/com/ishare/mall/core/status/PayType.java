package com.ishare.mall.core.status;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */
public enum PayType {
    /**新建**/
    NEW {
        public String getName(){return "新建";}
    },
    /**已支付**/
    PAY {
        public String getName(){return "已支付";}
    },
    /**处理中**/
    PROCESS {
        public String getName(){return "处理中";}
    };
    public abstract String getName();
}
