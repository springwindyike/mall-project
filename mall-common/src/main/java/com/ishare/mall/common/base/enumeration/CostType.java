package com.ishare.mall.common.base.enumeration;

/**
 * Created by YinLin on 2015/8/27.
 * Description :
 * Version 1.0
 */
public enum CostType {
    /**商户**/
    CHANNEL{
        public String getName(){return "商户";}
    },
    /**用户**/
    MEMBER{
        public String getName(){return "用户";}
    };
    public abstract String getName();
}
