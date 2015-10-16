package com.ishare.mall.manage.controller;

import javax.servlet.http.HttpServletRequest;

import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.manage.annoation.CurrentMember;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

import java.util.HashSet;
import java.util.Set;

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
	public String edit(@NotEmpty @PathVariable("id") String id, HttpServletRequest request) {
		request.setAttribute("orderId", id);
		return ManageViewConstant.Order.EDIT_ORDER;
	}

	/**
	 * 取消
	 *
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_EDIT_SUBMIT, method = RequestMethod.POST)
	@ResponseBody
	public OrderResultDTO editSubmit(
			@CurrentMember CurrentMemberDTO currentMemberDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("updatePrice") String updatePrice,
			@NotEmpty @RequestParam("updateNum") String updateNum,
			@NotEmpty @RequestParam("updateConsignee") String updateConsignee,
			@NotEmpty @RequestParam("note") String note) {
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setRecipients(updateConsignee);

		Set<OrderItemDetailDTO> item = new HashSet<OrderItemDetailDTO>();
		OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
		orderItemDetailDTO.setProductPrice(Float.parseFloat(updatePrice));
		orderItemDetailDTO.setAmount(Integer.parseInt(updateNum));
		item.add(orderItemDetailDTO);

		orderDetailDTO.setItems(item);
		orderDetailDTO.setUpdateBy(currentMemberDTO.getId().toString());
		orderDetailDTO.setLog(note);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_EDIT), orderDetailDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
			e.printStackTrace();
			orderResultDTO.setMessage("编辑失败！");
			orderResultDTO.setSuccess(false);
			return orderResultDTO;
		}
		orderDetailDTO = (OrderDetailDTO) resultDTO.getBody().getData();
		orderResultDTO.setMessage("编辑成功！");
		orderResultDTO.setSuccess(true);
		orderResultDTO.setOrderDetailDTO(orderDetailDTO);
		return orderResultDTO;
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
	 * 发货
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_DELIVER_SUBMIT, method = RequestMethod.POST)
	@ResponseBody
	public OrderResultDTO deliverSubmit(
			@CurrentMember CurrentMemberDTO currentMemberDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("expressId") String expressId, 
			@NotEmpty @RequestParam("expressOrder") String expressOrder, 
			@NotEmpty @RequestParam("note") String note) {
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setExpressId(expressId);
		orderDetailDTO.setExpressOrder(expressOrder);
		orderDetailDTO.setUpdateBy(currentMemberDTO.getId().toString());
		orderDetailDTO.setLog(note);
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
	public String logistics(@NotEmpty @PathVariable("id") String id, @NotEmpty @PathVariable("order") String order, HttpServletRequest request) {
		request.setAttribute("id", id);
		request.setAttribute("order", order);
		return ManageViewConstant.Order.LOGISTICS_ORDER;
	}
	/**
	 * 访问取消页面
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_CANCEL, method = RequestMethod.GET)
	public String cancel(@NotEmpty @PathVariable("id") String id, HttpServletRequest request) {
		request.setAttribute("orderId", id);
		return ManageViewConstant.Order.CANCEL_ORDER;
	}
	/**
	 * 取消
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_CANCEL_SUBMIT, method = RequestMethod.POST)
	@ResponseBody
	public OrderResultDTO cancelSubmit(
			@CurrentMember CurrentMemberDTO currentMemberDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("note") String note) {
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setUpdateBy(currentMemberDTO.getId().toString());
		orderDetailDTO.setLog(note);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_CANCEL), orderDetailDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
			e.printStackTrace();
			orderResultDTO.setMessage("取消失败！");
			orderResultDTO.setSuccess(false);
			return orderResultDTO;
		}
		orderDetailDTO = (OrderDetailDTO) resultDTO.getBody().getData();
		orderResultDTO.setMessage("取消成功！");
		orderResultDTO.setSuccess(true);
		orderResultDTO.setOrderDetailDTO(orderDetailDTO);
		return orderResultDTO;
	}
	/**
	 * 获取平台所有的order
	 *
	 * @return Page<OrderDetailDTO>
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO<?> findAll(HttpServletRequest request, Model model) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL), orderDetailDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
			e.printStackTrace();
		}
		PageDTO pageDTO = (PageDTO) resultDTO.getBody().getData();
		model.addAttribute("pageDTO",pageDTO);
		return pageDTO;
	}
	
	/**
	 * 根据条件查询Order
	 * @param searchCondition
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findBySearchCondition(@PathVariable("searchCondition") String searchCondition,Model model,HttpServletRequest request) throws Exception{
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(searchCondition);
		orderDetailDTO.setChannelId(8);
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));

		int currentPage = displayStart/displayLength+1;
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<Response> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		RestTemplate restTemplate = new RestTemplate();
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {});
//			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION), orderDetailDTO, Response.class);

		}catch (Exception e){
			log.error("call bizp app " + APPURIConstant.Order.REQUEST_MAPPING + APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION + "error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response != null) {
			if(response.isSuccess()){
				PageDTO pageDTO = (PageDTO)response.getData();
				model.addAttribute("pageDTO",pageDTO);
				return pageDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}
}