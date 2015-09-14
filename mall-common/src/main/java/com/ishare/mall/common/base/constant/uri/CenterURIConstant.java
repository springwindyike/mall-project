package com.ishare.mall.common.base.constant.uri;

/**
 * Created by YinLin on 2015/9/2.
 * Description : center常量
 * Version 1.0
 */
public interface CenterURIConstant {
	
	String VERIFYCODE = "verifycode";
	
    interface Index {
        //首页
//        String INDEX = "index";
        //登录
        String LOGIN = "login";
        //注册member
        String REGISTER = "register";
        //找回密码
        String FIND_PASSWORD = "findPassword";
        //验证账户名
        String ACCOUNTVALID = "accountValid";
    }
    
    interface Product{
   	 String REQUEST_MAPPING   = "/product";
   	 String REQUEST_MAPPING_SAVE= "/add";
    }
   
    interface Main {
        //首页
        String INDEX = "index";


    }
}
