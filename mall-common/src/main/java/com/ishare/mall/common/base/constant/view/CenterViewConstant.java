package com.ishare.mall.common.base.constant.view;

/**
 * Created by YinLin on 2015/9/2. Description : Version 1.0
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
    	 String ADD_PRODUCT ="product/add";
    	 String LIST_PRODUCT ="product/list";
    	 String UPDATE_PRODUCT= "product/update";
    }


	interface Order {
		String ADD_ORDER = "order/list";
	}

	interface Main {
		String MAIN = "main/index";
	}

	interface Member {
		String MEMBER_LIST = "member/list";
		String MEMBER_ADD = "member/add";
		String MEMBER_ADD_SUCCESS = "S";
		String MEMBER_UPDATE_SUCCESS = "S";
		String MEMBER_VIEW = "member/view";
		String MEMBER_UPDATE = "member/update";
		String MEMBER_CHANGE_PASSWORD = "/member/password/change";
		interface Password {
			// 找回密码
			String FIND_PASSWORD = "member/password/find";
		}
		
	}
}
