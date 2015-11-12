package com.ishare.mall.common.base.constant.uri;

/**
 * Created by YinLin on 2015/9/2. Description : center常量 Version 1.0
 */
public interface CenterURIConstant {

	String VERIFYCODE = "verifycode";

	interface Index {
		// 首页
		String INDEX = "index";
		// 登录
		String LOGIN = "login";
		// 注册member
		String REGISTER = "register";
		//注销
		String LOGOUT = "logout";
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
   	 String ALL_TYPE_PRODUCT ="/allType";
   	 String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition/{searchCondition}";
    }

  String INDEX = "index";

  interface Brand {
	  String REQUEST_MAPPING   = "/brand";
	  String REQUEST_MAPPING_FORWORD="/forword";
	  String REQUEST_MAPPING_FIND_ALL_BRAND="/allBrand";
	  String REQUEST_MAPPING_FIND_ALL_BRAND_LIST="/allBrandList";
  } 
  interface Banner{
	  String REQUEST_MAPPING   = "/banner";
	  String REQUEST_MAPPING_FORWORD="/forword";
	  String REQUEST_MAPPING_ADD_FORWORD="/addForword";
	  String REQUEST_MAPPING_FIND_ALL_BANNER="/allbanner";
	  String REQUEST_MAPPING_FIND_ALL_BANNER_LIST="/allBannerList";
	  String REQUEST_MAPPING_DELETE_BY_ID ="/del/{id}";
	  String REQUEST_MAPPING_UPDATE_BY_ID ="/update/{id}";
	  String REQUEST_MAPPING_ADD ="/add";
	  
	  
  }
  
  	interface Artice{
  	  String REQUEST_MAPPING   = "/artice";
  	  String REQUEST_MAPPING_FIND_ALL_ATRICE="/allArtice";	
  	  String REQUEST_MAPPING_FORWORD_ALL_ATRICE="/forwordArtice";	
  	  String REQUEST_MAPPING_DELETE_BY_ID ="/del/{id}";
  	}
  
	

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
		String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition/{searchCondition}";
		String REQUEST_MAPPING_FIND_BY_ID = "/findById/{id}";
	}

	interface Main {
		// 首页
		String INDEX = "main";
	}
	
	interface ProductType {
		 String REQUEST_MAPPING   = "/productType";
		 String REQUEST_MAPPING_FITST_LEVEL = "/firstLevel";
		 String REQUEST_MAPPING_CHILDREN_LEVEL = "/childLevel/{parentId}";
		 String REQUEST_MAPPING_FIND_BY_ID = "/findById/{id}";
	}
}
