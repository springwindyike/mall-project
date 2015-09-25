package com.ishare.mall.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderResultDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
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
	public String list() {
		return ManageViewConstant.Order.LIST_ORDER;
	}
	
	/**
	 * 访问编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_EDIT, method = RequestMethod.GET)
	public String edit() {
		return ManageViewConstant.Order.EDIT_ORDER;
	}
	
	/**
	 * 访问发货页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_DELIVER, method = RequestMethod.GET)
	public String deliver(@NotEmpty @PathVariable("id") String id, HttpServletRequest request) {
		request.setAttribute("orderId", id);
		return ManageViewConstant.Order.DELIVER_ORDER;
	}
	/**
	 * 访问发货页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_DELIVER_SUBMIT, method = RequestMethod.POST)
	public OrderResultDTO deliverSubmit(
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("expressId") String expressId, 
			@NotEmpty @RequestParam("expressOrder") String expressOrder, 
			@NotEmpty @RequestParam("note") String note) {
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setExpressId(expressId);
		orderDetailDTO.setExpressOrder(expressOrder);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_DELIVER), orderDetailDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
			e.printStackTrace();
			orderResultDTO.setMessage("发货失败！");
			orderResultDTO.setSuccess(false);
			return orderResultDTO;
		}
		orderDetailDTO = (OrderDetailDTO) resultDTO.getBody().getData();
		orderResultDTO.setMessage("发货成功！");
		orderResultDTO.setSuccess(true);
		orderResultDTO.setOrderDetailDTO(orderDetailDTO);
		return orderResultDTO;
	}
	/**
	 * 访问物流页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_LOGISTICS, method = RequestMethod.GET)
	public String logistics() {
		return ManageViewConstant.Order.LOGISTICS_ORDER;
	}
	/**
	 * 访问取消页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_CANCEL, method = RequestMethod.GET)
	public String cancel() {
		return ManageViewConstant.Order.CANCEL_ORDER;
	}
	/**
	 * 访问取消页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_CANCEL, method = RequestMethod.POST)
	public String cancelSubmit(@NotEmpty @PathVariable("logistics") Integer logistics) {
		return ManageViewConstant.Order.CANCEL_ORDER;
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
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID), orderDetailDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
			e.printStackTrace();
		}
		PageDTO pageDTO = (PageDTO) resultDTO.getBody().getData();
		model.addAttribute("pageDTO",pageDTO);
		return pageDTO;
	}
	
}