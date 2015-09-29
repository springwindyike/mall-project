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
		String REQUEST_MAPPING_EDIT = "/edit";
		String REQUEST_MAPPING_DELIVER = "/deliver/{id}";
		String REQUEST_MAPPING_DELIVER_SUBMIT = "/deliver";
		String REQUEST_MAPPING_LOGISTICS = "/logistics";
		String REQUEST_MAPPING_CANCEL = "/cancel/{id}";
		String REQUEST_MAPPING_CANCEL_SUBMIT = "/cancel";
		String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition/{searchCondition}";
	}
 
interface Category {
	String REQUEST_MAPPING = "/category";
	String REQUEST_MAPPING_CATEGORY_LIST = "/list";
	String REQUEST_MAPPING_CATEGORY_ADD = "/add";
}
}
