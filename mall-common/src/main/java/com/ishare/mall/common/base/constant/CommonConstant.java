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
        String CURRENT_MANAGEUSER = "manageUser";
        String PAGE_REQUEST = "pageRequest";
    }
   
    interface ValidForm {
    	/** 验证通过 信息 */
        String VALIDFORM_SUCCESS_INFO = "恭喜你，可以注册！";
        /** 验证通过 符号 */
        String VALIDFORM_SUCCESS_STATUS = "y";
        /** 验证失败 信息 */
        String VALIDFORM_FAIL_INFO = "对不起，您填写的信息已存在！";
        /** 验证失败 符号 */
        String VALIDFORM_FAIL_STATUS = "n";
    }
    
    //字符集
    interface CharSet {
        String UTF8 = "utf-8";
    }
}
