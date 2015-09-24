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
		String REQUEST_MAPPING_DELIVER = "/deliver";
		String REQUEST_MAPPING_LOGISTICS = "/logistics";
		String REQUEST_MAPPING_CANCEL = "/cancel";
	}

}
