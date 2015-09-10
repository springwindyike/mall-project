package com.ishare.mall.common.base.constant.view;

/**
 * Created by YinLin on 2015/9/2.
 * Description :
 * Version 1.0
 */
public interface CenterViewConstant {
    //首页 view
    interface Index {
        String LOGIN = "index/login";
        String INDEX = "index";
    	//注册
    	String REGISTER = "index/register";
    	//找回密码
    	String FIND_PASSWORD = "index/find-password";
    }
    
    interface Product {
    	 String ADD_PRODUCT ="product/addProduct";
    }

    interface Main {
    	String MAIN = "main/index";

    }

    interface Member {
        String MEMBER_LIST = "member/memberList";
        String MEMBER_ADD = "member/memberAdd";
    }
}
