package com.ishare.mall.common.base.constant.uri;

/**
 * Created by YinLin on 2015/9/222. 
 * Description : center常量
 * Version 1.0
 */
public interface ManageURIConstant {

	interface Order {
		String REQUEST_MAPPING = "/order";
		String REQUEST_MAPPING_SHOW = "/list";
		String REQUEST_MAPPING_FIND_BY_CHANNEL_ID = "/findByChannelId";
		String REQUEST_MAPPING_EDIT = "/edit/{id}";
		String REQUEST_MAPPING_EDIT_SUBMIT = "/edit";
		String REQUEST_MAPPING_DELIVER = "/deliver/{id}";
		String REQUEST_MAPPING_DELIVER_SUBMIT = "/deliver";
		String REQUEST_MAPPING_LOGISTICS = "/logistics/{order}/{id}";
		String REQUEST_MAPPING_CANCEL = "/cancel/{id}";
		String REQUEST_MAPPING_CANCEL_SUBMIT = "/cancel";
		String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition";
		String REQUEST_MAPPING_GET_ORDER_LIST = "/getOrderList";
	}
 
	interface Category {
		String REQUEST_MAPPING = "/category";
		String REQUEST_MAPPING_CATEGORY_LIST = "/list";
		String REQUEST_MAPPING_ALL_TYPE ="/allType";
		String REQUEST_MAPPING_CATEGORY_ADD = "/add";
		String REQUEST_MAPPING_CATEGORY_ADD_TEST = "/addTest";
	}

	interface Express {
		String REQUEST_MAPPING = "/express";
		String REQUEST_MAPPING_FIND ="/find/{order}/{id}";
	}
	
	String VERIFYCODE = "verifycode";
	interface Index{
		String LOGIN = "login";
	}
	
	interface Main {
		// 首页
		String INDEX = "main";
	}

	interface Channel{
		String REQUEST_MAPPING = "/channel";
	}

	interface ManageUser{
		String REQUEST_MAPPING = "/manageUser";
		String REQUEST_MAPPING_GET_MANAGE_USER_List = "/getManageUserList";
		String REQYEST_MAPPING_MANAGE_USER_LIST = "manage/list";
		String REQYEST_MAPPING_FORWARD_TO_MANAGE_USER_LIST = "/forward2ManageUsreList";
		String REQUEST_MAPPING_RESOURCE = "/manageuser";
		String REQUEST_MAPPING_VIEW = "manage/view";
		String REQUEST_MAPPING_CHANGE_STATUS = "/changeStatus";
		String REQUEST_MAPPING_FORWARD_TO_UPDATE_PAGE = "manage/update";
		String REQUEST_MAPPING_FIND_BY_ID = "/findById";
		String REQUEST_MAPPING_RESET_PASSWORD = "/restPassword";
		String REQUEST_MAPPING_SAVE = "/save";
		String REQUEST_MAPPING_UPDATE = "/update";
		String REQUEST_MAPPING_CHECK_NAME = "/checkLoginAccount";
		String REQUEST_MAPPING_CHANGE_PASSWORD = "/changePassword";
		String REQUEST_MAPPING_FORWARD_TO_ADD_PAGE = "manage/add";
		String REQUEST_MAPPING_FIND_BY_SEARCH_CONDITION = "/findByCondition";
		String REQUEST_MAPPING_FORWARD_TO_CHANGE_PASSWORD_PAGE = "manage/password";
	}
	
    interface Product{
      	 String REQUEST_MAPPING   = "/product";
      	 String REQUEST_MAPPING_SAVE= "/add";
      	 String REQUEST_MAPPING_UPDATE= "/update/{id}";
      	 String REQUEST_MAPPING_DEL="/del/{id}";
      	 String REQUEST_MAPPING_FIND_BY_ID="/findById/{id}";
      	 String REQUEST_MAPPING_FIND_ALL="/findAll";
      	 String REQUEST_MAPPING_FORWORD="/forword";
      	 String REQUEST_MAPPING_ALL_TYPE_PRODUCT ="/allType";
      	 String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition/{searchCondition}";
       }
    
	interface ProductType {
		 String REQUEST_MAPPING   = "/productType";
		 String REQUEST_MAPPING_FITST_LEVEL = "/firstLevel";
		 String REQUEST_MAPPING_CHILDREN_LEVEL = "/childLevel/{parentId}";
		 String REQUEST_MAPPING_FIND_BY_ID = "/findById/{id}";
	}
	
	  interface Brand {
		  String REQUEST_MAPPING   = "/brand";
		  String REQUEST_MAPPING_FORWORD="/forword";
		  String REQUEST_MAPPING_ADD_FORWORD="/addForword";
		  String REQUEST_MAPPING_UPLOAD_PIC="/uploadPic";
		  String REQUEST_MAPPING_FIND_ALL_BRAND="/allBrand";
		  String REQUEST_MAPPING_FIND_ALL_BRAND_LIST="/allBrandList";
		  String REQUEST_MAPPING_DELETE_BY_ID ="/del/{id}";
		  String REQUEST_MAPPING_UPDATE_BY_ID ="/update/{id}";
		  String REQUEST_MAPPING_ADD ="/add";
		  String REQUEST_MAPPING_UPDATE ="/update";
	  } 
}
