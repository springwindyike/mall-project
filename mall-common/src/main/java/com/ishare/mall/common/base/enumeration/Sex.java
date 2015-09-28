package com.ishare.mall.common.base.enumeration;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 性别
 * Version 1.0
 */
public enum Sex {
    NONE {public String getName(){return "男女不限";}},
    MAN  {public String getName(){return "男";}},
    WOMAN{public String getName(){return "女";}};
    public abstract String getName();
}
