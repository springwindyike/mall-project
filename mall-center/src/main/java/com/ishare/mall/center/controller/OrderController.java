package com.ishare.mall.center.controller;

import javax.servlet.http.HttpServletRequest;

import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
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
import org.springframework.web.bind.annotation.ResponseBody;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
	 * 获取当前渠道下所有的order
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


	/**
	 * 根据条件查询Order
	 * @param searchCondition
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = CenterURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findBySearchCondition(@CurrentMember CurrentMemberDTO currentMemberDTO, @PathVariable("searchCondition") String searchCondition,Model model,HttpServletRequest request) throws Exception{
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(searchCondition);
		orderDetailDTO.setChannelId(currentMemberDTO.getChannelId());
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));

		int currentPage = displayStart/displayLength+1;
		orderDetailDTO.setLimit(displayLength);
		orderDetailDTO.setOffset(currentPage);
		ResponseEntity<Response<PageDTO<OrderDetailDTO>>> resultDTO = null;
		HttpEntity<OrderDetailDTO> requestDTO = new HttpEntity<OrderDetailDTO>(orderDetailDTO);

		if(searchCondition != null && !"".equals(searchCondition)){
			try{
				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING,APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION),
						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>() {});

			}catch (Exception e){
				log.error("call bizp app " + APPURIConstant.Order.REQUEST_MAPPING + APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION + "error");
				throw new Exception(e.getMessage());
			}
		}else {
			try{
				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID),
						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<OrderDetailDTO>>>(){});
			}catch (Exception e){
				log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID + "error");
				throw new Exception(e.getMessage());
			}
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
	
}