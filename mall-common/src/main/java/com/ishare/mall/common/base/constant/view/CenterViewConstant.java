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

    interface Brand {
    	 String LIST_BRAND ="brand/list";
    	 String ADD_BRAND ="brand/add";
    	 String BRAND_UPDATE_SUCCESS = "S";
    	 String BRAND_ADD_SUCCESS = "S";
    	 String BRAND_UPDATE = "brand/update";
    }
    
    interface Banner {
   	 String LIST_BANNER ="banner/list";
   	 String ADD_BANNER ="banner/add";
   	 String BANNER_UPDATE_SUCCESS = "S";
   	 String BANNER_ADD_SUCCESS = "S";
   	 String BANNER_UPDATE = "banner/update";
   }

	interface Order {
		String LIST_ORDER = "order/list";
		String SELLER_LIST_ORDER = "order/seller_list";
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
	
interface Artice {
	String ARTICE_LIST = "artice/list";
}
	interface Channel{
		String CHANNEL_VIEW = "channel/view";
		String SUCCESS = "S";
	}
}
