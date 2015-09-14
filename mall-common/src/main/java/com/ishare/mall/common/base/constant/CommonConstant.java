package com.ishare.mall.common.base.constant;

/**
 * Created by YinLin on 2015/8/18.
 * Description :
 * Version 1.0
 */
public interface CommonConstant {
    interface Common {
        String SLASH = "/";
        String CURRENT_MEMBER = "member";
        String PAGE_REQUEST = "pageRequest";
    }
   
    interface ValidForm {
    	/** 验证通过 信息 */
        String VALIDFORM_SUCCESS_INFO = "验证通过！";
        /** 验证通过 符号 */
        String VALIDFORM_SUCCESS_STATUS = "y";
        /** 验证失败 信息 */
        String VALIDFORM_FAIL_INFO = "验证失败！";
        /** 验证失败 符号 */
        String VALIDFORM_FAIL_STATUS = "n";
    }
    
    //字符集
    interface CharSet {
        String UTF8 = "utf-8";
    }
}
