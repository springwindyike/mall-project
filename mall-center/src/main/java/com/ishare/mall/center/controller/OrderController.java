package com.ishare.mall.center.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.ishare.mall.center.form.SearchForm;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderResultDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;

import java.util.*;


/**
 * Created by ZhangZhaoxin on 2015/9/14. 
 * Description : 
 * Version 1.0
 */
@Controller
@RequestMapping(CenterURIConstant.Order.REQUEST_MAPPING)
public class OrderController extends BaseController {

	@Autowired
	private RestTemplate restTemplate;

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
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_SHOW, method = RequestMethod.GET)
	public String list() {
		return CenterViewConstant.Order.LIST_ORDER;
	}

	/**
	 * 跳转销售列表
	 *
	 * @return
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_SELLER_LIST, method = RequestMethod.GET)
	public String sellerList() {
		return CenterViewConstant.Order.SELLER_LIST_ORDER;
	}

	/**
	 * 获取当前渠道下所有的order
	 *
	 * @return Page<OrderDetailDTO>
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findByChannelId(@CurrentMember CurrentMemberDTO currentMemberDTO, HttpServletRequest request, Model model) throws Exception{

		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setChannelId(currentMemberDTO.getChannelId());
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID),
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
	 * 获取当前渠道销售产品的所有的order
	 *
	 * @return Page<OrderDetailDTO>
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUESR_MAPPING_FIND_BY_SELLER_ID, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findBySellerId(@CurrentMember CurrentMemberDTO currentMemberDTO, HttpServletRequest request, Model model) throws Exception{

		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setSellerId(currentMemberDTO.getChannelId());
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID),
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


//	/**
//	 * 根据条件查询Order
//	 * @param searchCondition
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION, method = RequestMethod.GET)
//	@ResponseBody
//	public PageDTO findBySearchCondition(@CurrentMember CurrentMemberDTO currentMemberDTO, @PathVariable("searchCondition") String searchCondition,Model model,HttpServletRequest request) throws Exception{
//		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//		orderDetailDTO.setOrderId(searchCondition);
//		orderDetailDTO.setChannelId(currentMemberDTO.getChannelId());
//		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
//		int displayStart = Integer.parseInt(request.getParameter("start"));
//
//		int currentPage = displayStart/displayLength+1;
//		orderDetailDTO.setLimit(displayLength);
//		orderDetailDTO.setOffset(currentPage);
//		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
//		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);
//
//		if(searchCondition != null && !"".equals(searchCondition)){
//			try{
//				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION),
//						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>() {});
//
//			}catch (Exception e){
//				log.error("call bizp app " + APPURIConstant.Order.REQUEST_MAPPING + APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION + "error");
//				throw new Exception(e.getMessage());
//			}
//		}else {
//			try{
//				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID),
//						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>(){});
//			}catch (Exception e){
//				log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID + "error");
//				throw new Exception(e.getMessage());
//			}
//		}
//		Response response = resultDTO.getBody();
//		if(response != null) {
//			if(response.isSuccess()){
//				PageDTO pageDTO = (PageDTO)response.getData();
//				model.addAttribute("pageDTO",pageDTO);
//				return pageDTO;
//			}else {
//				throw new Exception(response.getMessage());
//			}
//		}else{
//			throw new Exception("get response error");
//		}
//	}

	@RequestMapping(value = "test")
	@ResponseBody
	public PageDTO test() {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		List list = new ArrayList();
		Set<OrderItemDetailDTO> set = new HashSet<OrderItemDetailDTO>();
		for (int i=0;i<5;i++){
			OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
			orderItemDetailDTO.setOrderId("1");
			orderItemDetailDTO.setProductId(i);
			set.add(orderItemDetailDTO);
		}
		orderDetailDTO.setItems(set);
		list.add(orderDetailDTO);
		PageDTO<OrderDetailDTO> pageDTO = new PageDTO<>();
		pageDTO.setContent(list);
		pageDTO.setLimit(1);
		pageDTO.setTotalElements(1L);
		pageDTO.setOffset(1);
		pageDTO.setITotalDisplayRecords(1L);
		pageDTO.setPageSize(1);
		pageDTO.setTotalPages(1);
		pageDTO.setITotalDisplayRecords(1L);
		pageDTO.setITotalRecords(1L);
		return pageDTO;
	}
	
    @RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_FIND_BY_ID, produces = {"application/json"})
    @ResponseBody
	public OrderDetailDTO findById(@NotEmpty @PathVariable("id") String id) {

		ResponseEntity<Response<OrderDetailDTO>> resultDTO = null;
/*		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(id);*/
/*		RestTemplate restTemplate = new RestTemplate();
*/		try {

			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_ID),
					HttpMethod.GET, null, new ParameterizedTypeReference<Response<OrderDetailDTO>>(){},id);
		
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		OrderDetailDTO resultOrderDetailDto = (OrderDetailDTO) resultDTO
				.getBody().getData();
		return resultOrderDetailDto;
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
		return CenterViewConstant.Order.ORDER_DETAIL;
	}

	/**
	 * 访问物流页面
	 *
	 * @return
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_LOGISTICS, method = RequestMethod.GET)
	public String logistics(@NotEmpty @PathVariable("id") String id, @NotEmpty @PathVariable("order") String order, HttpServletRequest request) {
		request.setAttribute("id", id);
		request.setAttribute("order", order);
		return CenterViewConstant.Order.LOGISTICS_ORDER;
	}

	/**
	 * 访问发货页面
	 *
	 * @return
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_DELIVER, method = RequestMethod.GET)
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
		model.addAttribute("orderDetailDTO", response.getData());
		return CenterViewConstant.Order.DELIVER_ORDER;
	}

	/**
	 * 访问取消页面
	 *
	 * @return
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_CANCEL, method = RequestMethod.GET)
	public String cancel(@NotEmpty @PathVariable("id") String id, HttpServletRequest request) {
		request.setAttribute("orderId", id);
		return CenterViewConstant.Order.CANCEL_ORDER;
	}

	/**
	 * 审核通过
	 * @param id
	 * @param currentMemberDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/confirmOrder/{id}")
	public boolean confirmOrder(@NotEmpty @PathVariable("id") String id,@CurrentMember CurrentMemberDTO currentMemberDTO) throws Exception{
		Map mpa = new HashMap();
		mpa.put("id",id);
		mpa.put("account", currentMemberDTO.getAccount());
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
	 * 根据条件查询Order
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCH_CONDITION,
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public PageDTO findBySearchCondition(final HttpServletRequest request,SearchForm searchForm,@CurrentMember CurrentMemberDTO currentMemberDTO) throws Exception{
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		boolean flag = true;
		searchParams.put("EQ_channel.id",currentMemberDTO.getChannelId());
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
	 * 访问编辑页面
	 *
	 * @return
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_EDIT, method = RequestMethod.GET)
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
	 * 取消
	 *
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_CANCEL_SUBMIT, method = RequestMethod.POST)
	@ResponseBody
	public OrderResultDTO cancelSubmit(
			@CurrentMember CurrentMemberDTO currentMemberDTO,
			@NotEmpty @RequestParam("orderId") String orderId,
			@NotEmpty @RequestParam("note") String note) throws Exception{
		OrderResultDTO orderResultDTO = new OrderResultDTO();
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		orderDetailDTO.setUpdateBy(currentMemberDTO.getAccount());
		orderDetailDTO.setLog(note);
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
}