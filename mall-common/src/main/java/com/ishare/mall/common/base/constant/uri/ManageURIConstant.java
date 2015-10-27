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
		String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition/{searchCondition}";
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
	}
}
