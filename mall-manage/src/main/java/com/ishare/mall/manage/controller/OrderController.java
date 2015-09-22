package com.ishare.mall.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.manage.controller.base.BaseController;

/**
 * Created by ZhangZhaoxin on 2015/9/22. 
 * Description : 
 * Version 1.0
 */
@Controller
@RequestMapping(ManageURIConstant.Order.REQUEST_MAPPING)
public class OrderController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(OrderController.class);

	public static Logger getLog() {
		return log;
	}

	/**
	 * 访问列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_SHOW, method = RequestMethod.GET)
	public String register() {
		return ManageViewConstant.Order.LIST_ORDER;
	}

	/**
	 * 获取当前渠道下所有的order
	 *
	 * @return Page<OrderDetailDTO>
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO<?> findByChannelId(HttpServletRequest request, Model model) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setChannelId(8);
		int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"))==0?1:Integer.parseInt(request.getParameter("iDisplayLength"));
		int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int currentPage = displayStart/displayLength+1;
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<OrderDetailDTO> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID), orderDetailDTO, OrderDetailDTO.class);
		OrderDetailDTO orderDTOResult = resultDTO.getBody();
		model.addAttribute("pageDTO",orderDTOResult.getPageDTO());
		return orderDTOResult.getPageDTO();
	}
	
}