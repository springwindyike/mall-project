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
    }
    
    interface Product{
    	  //添加商品
        String ADD_PRODUCT="addProduct";
    }

    interface Main {
        //首页
        String INDEX = "index";
        //注册member
        String ADD_MEMBER="addMember";
        //找回密码
        String FIND_PASSWORD="findPassword";

    }
}
