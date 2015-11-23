package com.ishare.mall.center.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.ishare.mall.center.form.SearchForm;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderRefundDTO;
import com.ishare.mall.common.base.dto.order.OrderResultDTO;
import com.ishare.mall.common.base.utils.DateUntil;
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
		model.addAttribute("orderDetailDTO", response.getData());
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
		searchParams.put("EQ_channel.id",currentMemberDTO.getChannelId());
		if (StringUtils.isNotEmpty(searchForm.getOrderId())){
			searchParams.put("EQ_orderId",searchForm.getOrderId());
		}
		if (StringUtils.isNotEmpty(searchForm.getChannelName())){
			searchParams.put("EQ_channel.name",searchForm.getChannelName());
		}
		if (StringUtils.isNotEmpty(searchForm.getCreateBy())){
			searchParams.put("EQ_createBy.account",searchForm.getCreateBy());
		}
		if (StringUtils.isNotEmpty(searchForm.getDatemin())){
			searchParams.put("GTE_createTime",searchForm.getDatemin());
		}
		if (StringUtils.isNotEmpty(searchForm.getDatemax())){
			searchParams.put("LTE_createTime",searchForm.getDatemax());
		}
		if (searchForm.getPayWay() != null){
			searchParams.put("EQ_paymentWay",searchForm.getPayWay());
		}
		if (searchForm.getStatus() != null){
			searchParams.put("EQ_state",searchForm.getStatus());
		}
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));

		int currentPage = displayStart/displayLength+1;
		searchParams.put("limit", displayLength);
		searchParams.put("offset", currentPage);
		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
		HttpEntity<Map> requestDTO = new HttpEntity<Map>(searchParams);
			try{
				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION),
						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>() {});

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

		return CenterViewConstant.Order.EDIT_ORDER;
	}

	/**
	 * 取消
	 *
	 * @return
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_CANCEL_SUBMIT, method = RequestMethod.POST)
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

	/**
	 * 跳转到退款管理页面
	 * @return
	 */
	@RequestMapping("/orderRefundMoney")
	public String forward2RefundMoney(){
		return CenterViewConstant.Order.REFUND_MONEY;
	}
	/**
	 * 跳转到退货管理页面
	 * @return
	 */
	@RequestMapping("/orderRefundProduct")
	public String forward2RefundProduct(){
		return CenterViewConstant.Order.REFUND_PRODUCT;
	}

	/**
	 * 获取退款退货列表
	 * @return
	 */
	@RequestMapping(value = "getRefundMoney/{refundType}",method = RequestMethod.GET)
	@ResponseBody
	public PageDTO<OrderRefundDTO> getRefundMoney(HttpServletRequest request,@NotEmpty @PathVariable("refundType") Integer refundType,@CurrentMember CurrentMemberDTO currentMemberDTO) throws Exception{
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		Map map = new HashMap();
		map.put("EQ_refundType", refundType);
		map.put("limit", displayLength);
		map.put("offset", currentPage);
		map.put("EQ_channelId",currentMemberDTO.getChannelId());
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
		return CenterViewConstant.Order.REFUND_MONEY_DETAIL;
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
		return CenterViewConstant.Order.FORWARD_TO_REFUND_PAGE;
	}

	/***
	 * 确认退款
	 * @param refundId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "go2Confirm/{refundId}")
	public String go2Confirm(@NotEmpty @PathVariable("refundId") String refundId,HttpServletRequest request,Model model,@CurrentMember CurrentMemberDTO currentMemberDTO)throws Exception{
		ResponseEntity<Response> responseEntity = null;
		OrderRefundDTO orderRefundDTO = new OrderRefundDTO();
		orderRefundDTO.setRefundId(refundId);
		orderRefundDTO.setSellerMessage(request.getParameter("seller_message"));
		orderRefundDTO.setCenterState(Integer.parseInt(request.getParameter("seller_state")));
		orderRefundDTO.setManageId(currentMemberDTO.getId());
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
	 * 按条件查询退货退款订单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ManageURIConstant.Order.REQUEST_MAPPING_GET_REFUND_BY_CONDITION,
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public PageDTO getRefundMoneyByCondition(final HttpServletRequest request,@CurrentMember CurrentMemberDTO currentMemberDTO) throws Exception{
		Map<String, Object> searchParams = new HashMap<>();
		int refundType = Integer.valueOf(request.getParameter("refundType"));
		String findBy = request.getParameter("findBy");
		String detail = request.getParameter("detail");
		String datemin = request.getParameter("datemin");
		String datemax = request.getParameter("datemax");
		searchParams.put("EQ_refundType",refundType);
		searchParams.put("EQ_channelId",currentMemberDTO.getChannelId());
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

	/**
	 * 跳转到卖家收货页面
	 * @param refundId
	 * @return
	 */
	@RequestMapping(value = "/forward2ConfirmReceive/{refundId}",
			method = RequestMethod.GET,
			produces = {"application/json"})
	public String forward2ConfirmReceive(@NotEmpty @PathVariable("refundId") String refundId,Model model) throws Exception{
		ResponseEntity<Response<OrderRefundDTO>> responseEntity = null;
		try{
			responseEntity =  restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_DETAIL_BY_REFUND_ID),
					HttpMethod.POST, null, new ParameterizedTypeReference<Response<OrderRefundDTO>>() {}, refundId);
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			throw new Exception("get response error.");
		}
		Response<OrderRefundDTO> response = responseEntity.getBody();
		if(response == null || !response.isSuccess()){
			throw new Exception("get response error.");
		}
		OrderRefundDTO orderRefundDTO = response.getData();
		orderRefundDTO.setBuyerShipDateStr(DateUntil.getDateStr(null,orderRefundDTO.getBuyerShipDate()));
		model.addAttribute("orderRefundDTO",orderRefundDTO);
		return CenterViewConstant.Order.REFUND_RECEIVE;
	}

	/**
	 * 确认收到货
	 * @param refundId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/confirmReceive/{refundId}",
			method = RequestMethod.POST,
			produces = {"application/json"})
	public String confirmReceive(@NotEmpty @PathVariable("refundId") String refundId) throws Exception{
		ResponseEntity<Response> responseEntity = null;
		try{
			responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_REFUND_CONFIRM),
					HttpMethod.POST, null, new ParameterizedTypeReference<Response>(){}, refundId);
		}catch (Exception e){
			log.error(e.getLocalizedMessage());
			throw new Exception("get response error.");
		}
		Response<OrderRefundDTO> response = responseEntity.getBody();
		if(response == null || !response.isSuccess()){
			throw new Exception("get response error.");
		}
		return "S";
	}
}