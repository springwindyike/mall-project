package com.ishare.mall.common.base.constant;

/**
 * Created by Administrator on 2015/11/10.
 */
public interface CodeConstant {
    interface Refund{
        Integer CENTER_CONFIRM = 2;
        Integer CENTER_NOT_CONFIRM = 3;
        Integer WAIT_CENTER_CONFIRM = 1;
        String CENTER_CONFIRM_STRING = "同意";
        String CENTER_NOT_CONFIRM_STRING = "不同意";
        String WAIT_CENTER_CONFIRM_STRING = "待处理";
        Integer MANAGE_CONFIRM = 3;
        Integer MANAGE_NOT_CONFIRM = 1;
        Integer WAIT_MANAGE_CONFIRM = 2;
        String MANAGE_CONFIRM_STRING = "完成";
        String MANAGE_NOT_CONFIRM_STRING = "不同意";
        String WAIT_MANAGE_CONFIRM_STRING = "待确认";
        String BLANK = "";
    }
}
