package com.ishare.mall.center.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.cms.BannerDTO;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;

@Controller
@RequestMapping(value = CenterURIConstant.Banner.REQUEST_MAPPING)
public class BannerController extends BaseController{
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger log = LoggerFactory.getLogger(BannerController.class);
	
	public static Logger getlog(){
		return log;
		
	}
	@RequestMapping(value=CenterURIConstant.Banner.REQUEST_MAPPING_FORWORD,method = RequestMethod.GET) 
	public String forwardToBannerList(){
		return CenterViewConstant.Banner.LIST_BANNER;
	}
	
	@RequestMapping(value=CenterURIConstant.Banner.REQUEST_MAPPING_ADD_FORWORD,method=RequestMethod.GET)
	public String forwardToBannerAdd(){
		return CenterViewConstant.Banner.ADD_BANNER;
	}
	 @RequestMapping(value = CenterURIConstant.Banner.REQUEST_MAPPING_FIND_ALL_BANNER, method = RequestMethod.GET,produces = {"application/json"})
		@ResponseBody
	public PageDTO findAllBanner(@CurrentMember CurrentMemberDTO currentMemberDTO,HttpServletRequest request,Model model)throws Exception{
		BannerDTO bannerDTO = new BannerDTO();
		int displayLength =Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		bannerDTO.setLimit(displayLength);
		bannerDTO.setOffset(currentPage);
		HttpEntity<BannerDTO> requestDTO = new HttpEntity<BannerDTO>(bannerDTO);
		ResponseEntity<Response<PageDTO<BannerDTO>>> resultDTO =null;
		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Banner.REQUEST_MAPPING, APPURIConstant.Banner.REQUEST_MAPPING_ALL_BANNER),
				HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<PageDTO<BannerDTO>>>() {});
		} catch (Exception e) {
			log.error("call bizp app"+APPURIConstant.Banner.REQUEST_MAPPING+APPURIConstant.Banner.REQUEST_MAPPING_ALL_BANNER+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if (response !=null) {
			if (response.isSuccess()) {
				PageDTO pageDTO = (PageDTO) response.getData();
				model.addAttribute("pageDTO",pageDTO);
				return pageDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else {
			throw new Exception("get response error");
		}
	}
	@RequestMapping(value = CenterURIConstant.Banner.REQUEST_MAPPING_FIND_ALL_BANNER_LIST,method =RequestMethod.GET,produces = {"application/json"})
	public List<BannerDTO>  findAllBannerList(@CurrentMember CurrentMemberDTO currentMemberDTO,HttpServletRequest request,Model model)throws Exception{
		BannerDTO bannerDTO = new BannerDTO();
		HttpEntity<BannerDTO> requestDTO = new HttpEntity<BannerDTO>(bannerDTO);
		ResponseEntity<Response<BannerDTO>> resultDTO =null;
		try {
			
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Banner.REQUEST_MAPPING, APPURIConstant.Banner.REQUEST_MAPPING_ALL_BANNER_LIST),
				HttpMethod.GET,null,new ParameterizedTypeReference<Response<BannerDTO>>() {}	);
		} catch (Exception e) {
			log.error("call bizp app"+APPURIConstant.Banner.REQUEST_MAPPING+APPURIConstant.Banner.REQUEST_MAPPING_ALL_BANNER_LIST+"error");
			throw new Exception(e.getMessage());
		}
		Response response =resultDTO.getBody();
		if (response !=null) {
			if (response.isSuccess()) {
				List<BannerDTO> returnDTO =(List<BannerDTO>) response.getData();
				return returnDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}
	/**
	 * 系统栏目删除
	 * @param id
	 * @param currentMemberDTO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value =  CenterURIConstant.Banner.REQUEST_MAPPING_DELETE_BY_ID)
	public String delete(@NotEmpty @PathVariable("id")Integer id,@CurrentMember CurrentMemberDTO currentMemberDTO)throws Exception{
		BannerDTO bannerDTO = new BannerDTO();
		bannerDTO.setId(id);
		ResponseEntity<Response<BannerDTO>> resultDTO = null;
		HttpEntity<BannerDTO> requestDTO = new HttpEntity<BannerDTO>(bannerDTO);
		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Banner.REQUEST_MAPPING, APPURIConstant.Banner.REQUEST_MAPPING_DELETE_BY_ID),
				HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<BannerDTO>>() {	}	);
		} catch (Exception e) {
			log.error("call bizp app"+APPURIConstant.Banner.REQUEST_MAPPING+APPURIConstant.Banner.REQUEST_MAPPING_DELETE_BY_ID+"error");
			throw new Exception(e.getMessage());
		}
		Response response =resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if (response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return CenterViewConstant.Banner.BANNER_UPDATE_SUCCESS;
		
	}
	/**
	 *跳转UPDATE页面
	 * @return
	 */
	@RequestMapping(value=CenterURIConstant.Banner.REQUEST_MAPPING_UPDATE_BY_ID)
	public String forwordUpdateBanner(@NotEmpty @PathVariable("id") Integer id,Model model)throws Exception{
		BannerDTO bannerDTO = new BannerDTO();
		bannerDTO.setId(id);
		ResponseEntity<Response<BannerDTO>> resultDTO = null;
		HttpEntity<BannerDTO> requestDTO = new HttpEntity<BannerDTO>(bannerDTO);
		try {
			
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Banner.REQUEST_MAPPING, APPURIConstant.Banner.REQUEST_MAPPING_UPDATE_BY_ID),
					HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<BannerDTO>>() {	});
		} catch (Exception e) {
			log.error("call bizp app"+APPURIConstant.Banner.REQUEST_MAPPING+APPURIConstant.Banner.REQUEST_MAPPING_UPDATE_BY_ID+"error");
			throw new Exception(e.getMessage());
		}
		Response response =resultDTO.getBody();
		if (response !=null) {
			if (response.isSuccess()) {
				BannerDTO  returnDTO =(BannerDTO) response.getData();
				model.addAttribute("returnDTO",requestDTO);
			}else {
				throw new Exception(response.getMessage());
			}
		}else {
			throw new Exception("get response error");
		}
		return CenterViewConstant.Banner.BANNER_UPDATE;
	}
	
	
//	@ResponseBody
//	@RequestMapping(value = CenterURIConstant.Banner.REQUEST_MAPPING_ADD,method = RequestMethod.POST)
//	public String addBanner(){
//		BannerDTO bannerDTO = new BannerDTO();
//		BeanUtils.copyProperties(add, target);
//		
//		
//		return bizAppUrl;
//		
//		
//	}
//	
	
	
}
