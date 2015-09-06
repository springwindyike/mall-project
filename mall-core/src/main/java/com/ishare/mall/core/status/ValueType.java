package com.ishare.mall.core.status;

/**
 * Created by YinLin on 2015/9/6.
 * Description :
 * Version 1.0
 */
public enum ValueType {
    STRING {public String getName(){return "字符串";}},
    NUMBER  {public String getName(){return "数值";}};
    public abstract String getName();
}
