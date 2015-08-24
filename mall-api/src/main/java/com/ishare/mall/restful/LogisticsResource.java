package com.ishare.mall.restful;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ishare.mall.common.base.general.CommonGeneral;

/**
 * Created by YinLin on 2015/8/5.
 * Description:物流接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/logistics")
public class LogisticsResource {
	
	 @RequestMapping(value = "/orderId/{orderId}/expressId/{expressId}", method = RequestMethod.GET)
	 @ResponseBody
	 public Object get(@NotEmpty @PathVariable("orderId") String orderId,@NotEmpty @PathVariable("expressId") String expressId) {
		 String url = "http://www.aikuaidi.cn/rest/?key=266f8c229f87424c9637bf1d3221c00f"+"&order="+orderId+"&id="+expressId;
		 String result = CommonGeneral.loadJSON(url);
		return result;
		 
	 }
}
