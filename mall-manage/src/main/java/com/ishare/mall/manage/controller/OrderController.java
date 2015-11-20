package com.ishare.mall.manage.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.ishare.mall.common.base.constant.CodeConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.order.*;
import com.ishare.mall.manage.annoation.CurrentManageUser;
import com.ishare.mall.manage.form.SearchForm;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.controller.base.BaseController;

import java.util.*;

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

	@Autowired
	private RestTemplate restTemplate;

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
	public String edit(@NotEmpty @PathVariable("id") String id, HttpServletRequest request)throws Exception{
		request.setAttribute("orderId", id);

		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(id);
		ResponseEntity<Response<OrderDetailDTO>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GOTO_EDIT),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<OrderDetailDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GOTO_EDIT + "error");
			throw new Exception(e.getMessage());
		}
		orderDetailDTO = resultDTO.getBody().getData();
		//订单下只有一个订单项
		Iterator<OrderItemDetailDTO> it = orderDetailDTO.getItems().iterator();
		while (it.hasNext()) {
			OrderItemDetailDTO orderItemDetailDTO = it.next();
			request.setAttribute("updatePrice", orderItemDetailDTO.getProductPrice());
			request.setAttribute("updateNum", orderItemDetailDTO.getAmount());
		}
		request.setAttribute("updateConsignee", orderDetailDTO.getRecipients());

		return ManageViewConstant.Order.EDIT_ORDER;
	}

	/**
	 * 编辑
	 *
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_EDIT_SUBMIT, method = RequestMethod.POST)
	@ResponseBody
	public OrderResultDTO editSubmit(
			@CurrentManageUser CurrentManageUserDTO currentManageUserDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("updatePrice") String updatePrice,
			@NotEmpty @RequestParam("updateNum") String updateNum,
			@NotEmpty @RequestParam("updateConsignee") String updateConsignee,
			@NotEmpty @RequestParam("note") String note)throws Exception{
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
		orderDetailDTO.setUpdateBy(currentManageUserDTO.getId().toString());
		orderDetailDTO.setLog(note);
//		ResponseEntity<Response> resultDTO = null;
//		try {
//			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_EDIT), orderDetailDTO, Response.class);
//		} catch (Exception e) {
//			log.debug("error");
//			e.printStackTrace();
//			orderResultDTO.setMessage("编辑失败！");
//			orderResultDTO.setSuccess(false);
//			return orderResultDTO;
//		}
//		orderDetailDTO = (OrderDetailDTO) resultDTO.getBody().getData();
		ResponseEntity<Response<OrderDetailDTO>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_EDIT),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<OrderDetailDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GOTO_EDIT + "error");
			orderResultDTO.setMessage("编辑失败！");
			orderResultDTO.setSuccess(false);
			return orderResultDTO;
		}
		orderDetailDTO = resultDTO.getBody().getData();

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
	public String deliver(@NotEmpty @PathVariable("id") String id, HttpServletRequest request,Model model) throws Exception{
		request.setAttribute("orderId", id);
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(id);
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
 		Response<OrderDetailDTO> response = null;
		ResponseEntity<Response<OrderDetailDTO>> responseEntity = null;
		try{
			responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GET_ORDER_DETAIL),
					HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<OrderDetailDTO>>(){});
		}catch (Exception e){
			log.error("has error:",e.getStackTrace());
		}
		response = responseEntity.getBody();
		if (response == null || !response.isSuccess()){
			throw new Exception("get response error.");
		}
		model.addAttribute("orderDetailDTO",response.getData());
		return ManageViewConstant.Order.DELIVER_ORDER;
	}
	/**
	 * 发货
	 * 
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_DELIVER_SUBMIT, method = RequestMethod.POST)
	public String deliverSubmit(
			@CurrentManageUser CurrentManageUserDTO currentManageUserDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("expressId") String expressId, 
			@NotEmpty @RequestParam("expressOrder") String expressOrder, 
			@NotEmpty @RequestParam("note") String note,Model model) throws Exception{
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setExpressId(expressId);
		orderDetailDTO.setExpressOrder(expressOrder);
		orderDetailDTO.setUpdateBy(currentManageUserDTO.getId().toString());
		orderDetailDTO.setLog(note);
//		ResponseEntity<Response> resultDTO = null;
//		try {
//			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_DELIVER), orderDetailDTO, Response.class);
//		} catch (Exception e) {
//			log.debug("error");
//			e.printStackTrace();
//			orderResultDTO.setMessage("发货失败！");
//			orderResultDTO.setSuccess(false);
//			return orderResultDTO;
//		}
//		orderDetailDTO = (OrderDetailDTO) resultDTO.getBody().getData();
		ResponseEntity<Response<OrderDetailDTO>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_DELIVER),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<OrderDetailDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app " + APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GOTO_EDIT + "error");
			orderResultDTO.setMessage("发货失败！");
			orderResultDTO.setSuccess(false);
			model.addAttribute("status",false);
			return ManageViewConstant.Order.LIST_ORDER;
		}
		orderDetailDTO = resultDTO.getBody().getData();

		orderResultDTO.setMessage("发货成功！");
		orderResultDTO.setSuccess(true);
		orderResultDTO.setOrderDetailDTO(orderDetailDTO);
		model.addAttribute("status",true);
		//return orderResultDTO;
		return ManageViewConstant.Order.LIST_ORDER;
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
			@CurrentManageUser CurrentManageUserDTO currentManageUserDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("note") String note) throws Exception{
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setUpdateBy(currentManageUserDTO.getId().toString());
		orderDetailDTO.setLog(note);
//		ResponseEntity<Response> resultDTO = null;
//		try {
//			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_CANCEL), orderDetailDTO, Response.class);
//		} catch (Exception e) {
//			log.debug("error");
//			e.printStackTrace();
//			orderResultDTO.setMessage("取消失败！");
//			orderResultDTO.setSuccess(false);
//			return orderResultDTO;
//		}
//		orderDetailDTO = (OrderDetailDTO) resultDTO.getBody().getData();
		ResponseEntity<Response<OrderDetailDTO>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_CANCEL),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<OrderDetailDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GOTO_EDIT + "error");
			orderResultDTO.setMessage("取消失败！");
			orderResultDTO.setSuccess(false);
			return orderResultDTO;
		}
		orderDetailDTO = resultDTO.getBody().getData();

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
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_GET_ORDER_LIST, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findAll(HttpServletRequest request, Model model)throws Exception{

		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>(){});
		}catch (Exception e){
			log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID + "error");
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
	
	/**
	 * 根据条件查询Order
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION,
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public PageDTO findBySearchCondition(final HttpServletRequest request,SearchForm searchForm) throws Exception{
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		boolean flag = true;
		if (StringUtils.isNotEmpty(searchForm.getOrderId())){
			searchParams.put("EQ_orderId",searchForm.getOrderId());
			flag = false;
		}
		if (StringUtils.isNotEmpty(searchForm.getChannelName())){
			searchParams.put("EQ_channel.name",searchForm.getChannelName());
			flag = false;
		}
		if (StringUtils.isNotEmpty(searchForm.getCreateBy())){
			searchParams.put("EQ_createBy.account",searchForm.getCreateBy());
			flag = false;
		}
		if (StringUtils.isNotEmpty(searchForm.getDatemin())){
			searchParams.put("GTE_createTime",searchForm.getDatemin());
			flag = false;
		}
		if (StringUtils.isNotEmpty(searchForm.getDatemax())){
			searchParams.put("LTE_createTime",searchForm.getDatemax());
			flag = false;
		}
		if (searchForm.getPayWay() != null){
			searchParams.put("EQ_paymentWay",searchForm.getPayWay());
			flag = false;
		}
		if (searchForm.getStatus() != null){
			searchParams.put("EQ_state",searchForm.getStatus());
			flag = false;
		}
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));

		int currentPage = displayStart/displayLength+1;
		searchParams.put("limit", displayLength);
		searchParams.put("offset", currentPage);
		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
		HttpEntity<Map> requestDTO = new HttpEntity<Map>(searchParams);
		if(!flag){
			try{
				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL_BY_SEARCHCONDITION),
						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>() {});

			}catch (Exception e){
				log.error("call bizp app " + APPURIConstant.Order.REQUEST_MAPPING + APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL_BY_SEARCHCONDITION + "error");
				throw new Exception(e.getMessage());
			}
		}else {
			try{
				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL),
						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>(){});
			}catch (Exception e){
				log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL + "error");
				throw new Exception(e.getMessage());
			}
		}
		Response response = resultDTO.getBody();
		if(response != null) {
			if(response.isSuccess()){
				PageDTO pageDTO = (PageDTO)response.getData();
				return pageDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}

	/**
	 * 获取订单详细
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOrderDetail/{id}",method = RequestMethod.GET)
	public String getOrderDetail(@NotEmpty @PathVariable("id") String id,Model model) throws Exception{
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(id);
		ResponseEntity<Response<OrderDetailDTO>> responseEntity = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try {
			responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GET_ORDER_DETAIL),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<OrderDetailDTO>>(){});
		}catch (Exception e){
			log.error("error",e.getStackTrace());
		}
		Response<OrderDetailDTO> response = responseEntity.getBody();
		if (response == null || !response.isSuccess()){
			throw new Exception("get response error");
		}
		model.addAttribute("orderDetailDTO",response.getData());
		return ManageViewConstant.Order.ORDER_DETAIL;
	}

	/**
	 * 审核通过
	 * @param id
	 * @param currentManageUserDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/confirmOrder/{id}")
	public boolean confirmOrder(@NotEmpty @PathVariable("id") String id,@CurrentManageUser CurrentManageUserDTO currentManageUserDTO) throws Exception{
		Map mpa = new HashMap();
		mpa.put("id",id);
		mpa.put("account", currentManageUserDTO.getUsername());
		HttpEntity<Map> requestDTO = new HttpEntity<Map>(mpa);
		ResponseEntity<Response> responseEntity = null;
		try{
			responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_CONFIRM_ORDER),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {
			});
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			throw new Exception(e);
		}
		Response<OrderDetailDTO> response = responseEntity.getBody();
		if (response == null || !response.isSuccess()){
			throw new Exception("get response error");
		}
		return true;
	}

	/**
	 * 跳转到退货管理页面
	 * @return
	 */
	@RequestMapping("/orderRefundProduct")
	public String forward2RefundProduct(){
		return ManageViewConstant.Order.REFUND_PRODUCT;
	}

	/**
	 * 跳转到退款管理页面
	 * @return
	 */
	@RequestMapping("/orderRefundMoney")
	public String forward2RefundMoney(){
		return ManageViewConstant.Order.REFUND_MONEY;
	}
	/**
	 * 获取退款列表
	 * @return
	 */
	@RequestMapping(value = "getRefundMoney/{refundType}",method = RequestMethod.GET)
	@ResponseBody
	public PageDTO<OrderRefundDTO> getRefundMoney(HttpServletRequest request,@NotEmpty @PathVariable("refundType") Integer refundType) throws Exception{
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		Map map = Maps.newConcurrentMap();
		map.put("EQ_refundType",refundType);
		map.put("limit", displayLength);
		map.put("offset", currentPage);
		map.put("EQ_refundState",CodeConstant.Refund.REFUND_STATE_WAIT_MANAGE_CONFIRM);
		HttpEntity<Map> requestDTO = new HttpEntity<Map>(map);
		ResponseEntity<Response<PageDTO<OrderRefundDTO>>> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_MONEY),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderRefundDTO>>>() {
					});
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			throw new Exception(e);
		}
		Response<PageDTO<OrderRefundDTO>> response = responseEntity.getBody();
		if (response == null || !response.isSuccess()){
			throw new Exception("get response error");
		}
		return response.getData();
	}

	/**
	 * 获取退款详情
	 * @param refundId
	 * @return
	 */
	@RequestMapping(value = "getRefundDetail/{refundId}",method = RequestMethod.GET)
	public String getRefundDetail(@NotEmpty @PathVariable("refundId") String refundId,Model model) throws Exception {
		ResponseEntity<Response<OrderRefundDTO>> responseEntity = null;
		try {
			responseEntity =  restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_DETAIL_BY_REFUND_ID),
					HttpMethod.POST, null, new ParameterizedTypeReference<Response<OrderRefundDTO>>() {}, refundId);
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			throw new Exception(e);
		}

		Response<OrderRefundDTO> response = responseEntity.getBody();
		if(response == null || !response.isSuccess()){
			throw new Exception("get response error");
		}
		model.addAttribute("orderRefundDTO",response.getData());
		return ManageViewConstant.Order.REFUND_MONEY_DETAIL;
	}

	/**
	 * 确认退款
	 * @param refundId
	 * @return
	 */
	@RequestMapping(value = "confirmRefund/{refundId}",method = RequestMethod.GET)
	public String confirmRefund(@NotEmpty @PathVariable("refundId") String refundId,Model model) throws Exception {
		ResponseEntity<Response<OrderRefundDTO>> responseEntity = null;
		try {
			responseEntity =  restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GO_TO_CONFIRM),
					HttpMethod.POST, null, new ParameterizedTypeReference<Response<OrderRefundDTO>>() {}, refundId);
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			throw new Exception(e);
		}

		Response<OrderRefundDTO> response = responseEntity.getBody();
		if(response == null || !response.isSuccess()){
			throw new Exception("get response error");
		}
		model.addAttribute("orderRefundDTO",response.getData());
		return ManageViewConstant.Order.REFUND_MONEY_DETAIL;
	}

	/**
	 * 跳转到确认退款页面
	 * @param refundId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "forward2ConfirmRefund/{refundId}")
	public String forward2ConfirmRefund(@NotEmpty @PathVariable("refundId") String refundId,Model model) throws Exception{
		ResponseEntity<Response<OrderRefundDTO>> responseEntity = null;
		try {
			responseEntity =  restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_DETAIL_BY_REFUND_ID),
					HttpMethod.POST, null, new ParameterizedTypeReference<Response<OrderRefundDTO>>() {}, refundId);
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			throw new Exception(e);
		}

		Response<OrderRefundDTO> response = responseEntity.getBody();
		if(response == null || !response.isSuccess()){
			throw new Exception("get response error");
		}
		model.addAttribute("orderRefundDTO",response.getData());
		return ManageViewConstant.Order.FORWARD_TO_REFUND_PAGE;
	}

	/***
	 * 确认退款
	 * @param refundId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "go2Confirm/{refundId}")
	public String go2Confirm(@NotEmpty @PathVariable("refundId") String refundId,HttpServletRequest request,Model model,@CurrentManageUser CurrentManageUserDTO currentManageUserDTO)throws Exception{
		ResponseEntity<Response> responseEntity = null;
		OrderRefundDTO orderRefundDTO = new OrderRefundDTO();
		orderRefundDTO.setRefundId(refundId);
		orderRefundDTO.setAdminMessage(request.getParameter("admin_message"));
		orderRefundDTO.setManageId(currentManageUserDTO.getId());
		HttpEntity<OrderRefundDTO> requestDTO = new HttpEntity<OrderRefundDTO>(orderRefundDTO);
		try{
			responseEntity =  restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GO_TO_CONFIRM),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {});
		}catch (Exception e){
			log.error("error", e.getStackTrace());
			model.addAttribute("status", false);
			throw new Exception(e);
		}
		Response response = responseEntity.getBody();
		if(response == null || !response.isSuccess()){
			model.addAttribute("status", false);
		}
		model.addAttribute("status",true);
		return ManageViewConstant.Order.REFUND_MONEY;
	}

	/**
	 * 按条件查询脱货退款订单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_GET_REFUND_BY_CONDITION,
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public PageDTO getRefundMoneyByCondition(final HttpServletRequest request) throws Exception{
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		int refundType = Integer.valueOf(request.getParameter("refundType"));
		String findBy = request.getParameter("findBy");
		String detail = request.getParameter("detail");
		String datemin = request.getParameter("datemin");
		String datemax = request.getParameter("datemax");
		searchParams.put("EQ_refundType",refundType);
		if(StringUtils.isNotEmpty(findBy) && StringUtils.isNotEmpty(detail)){
			searchParams.put("EQ_"+findBy,detail);
		}
		if (StringUtils.isNotEmpty(datemin)){
			searchParams.put("GTE_buyerDate",datemin);
		}
		if (StringUtils.isNotEmpty(datemax)){
			searchParams.put("LTE_buyerDate",datemax);
		}
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		searchParams.put("limit", displayLength);
		searchParams.put("offset", currentPage);
		ResponseEntity<Response<PageDTO<OrderRefundDTO>>> resultDTO = null;
		HttpEntity<Map> requestDTO = new HttpEntity<Map>(searchParams);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_BY_CONDITION),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderRefundDTO>>>() {});

		}catch (Exception e){
			log.error("call bizp app " + APPURIConstant.Order.REQUEST_MAPPING + APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL_BY_SEARCHCONDITION + "error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response != null) {
			if(response.isSuccess()){
				PageDTO pageDTO = (PageDTO)response.getData();
				return pageDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}
}