package com.ishare.mall.common.base.constant.uri;

/**
 * Created by YinLin on 2015/9/2. Description : center常量 Version 1.0
 */
public interface CenterURIConstant {

	String VERIFYCODE = "verifycode";

	interface Index {
		// 首页
		// String INDEX = "index";
		// 登录
		String LOGIN = "login";
		// 注册member
		String REGISTER = "register";
		// 找回密码
		String FIND_PASSWORD = "findPassword";
		// 验证账户名
		String ACCOUNT_VALID = "accountValid";
		//注册验证Channel
		String CHANNEL_VALID = "channelValid";
	}
    
    interface Product{
   	 String REQUEST_MAPPING   = "/product";
   	 String REQUEST_MAPPING_SAVE= "/add";
   	 String REQUEST_MAPPING_UPDATE= "/update/{id}";
   	 String REQUEST_MAPPING_DEL="/del/{id}";
   	 String REQUEST_MAPPING_FIND_BY_ID="/findById/{id}";
   	 String REQUEST_MAPPING_FIND_BY_CHANNEL_ID="/findByChannelId";
   	 String REQUEST_MAPPING_FORWORD="/forword";
    }

  String INDEX = "index";

	interface Member {
		String REQUEST_MAPPING = "/member";
		interface Password {
			String FIND = "/password/find";
		}
	}

	interface Order {
		String REQUEST_MAPPING = "/order";
		String REQUEST_MAPPING_SHOW = "/list";
		String REQUEST_MAPPING_FIND_BY_CHANNEL_ID = "/findByChannelId";
	}

	interface Main {
		// 首页
		String INDEX = "index";

	}
}
