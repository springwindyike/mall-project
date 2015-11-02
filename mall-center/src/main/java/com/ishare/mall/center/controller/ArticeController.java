package com.ishare.mall.center.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.cms.ArticleDTO;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
/**
 * 系统日志Controller
 * @author zhangzhaoxin
 *
 */
@Controller
@RequestMapping(value = CenterURIConstant.Artice.REQUEST_MAPPING)
public class ArticeController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;

	private static final Logger log = LoggerFactory.getLogger(ArticeController.class);


	public static Logger getlog(){
		return log;

	}

/**
 * 查询所有系统日志
 * @param currentMemberDTO
 * @param request
 * @param model
 * @return
 * @throws Exception
 */
	@RequestMapping(value = CenterURIConstant.Artice.REQUEST_MAPPING_FIND_ALL_ATRICE, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findAllArtice(@CurrentMember CurrentMemberDTO currentMemberDTO, HttpServletRequest request, Model model)throws Exception{

		ArticleDTO atricleDTO = new ArticleDTO();
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displaystart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displaystart/displayLength+1;
		atricleDTO.setLimit(displayLength);
		atricleDTO.setOffset(currentPage);
		HttpEntity<ArticleDTO> requestDTO = new HttpEntity<ArticleDTO>(atricleDTO);
		ResponseEntity<Response<PageDTO<ArticleDTO>>> resultDTO = null;

		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Artice.REQUEST_MAPPING, APPURIConstant.Artice.REQUEST_MAPPING_ALL_Artice),
					HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<PageDTO<ArticleDTO>>>() {}); 
		} catch (Exception e) {

			log.error("call biz app"+APPURIConstant.Artice.REQUEST_MAPPING+APPURIConstant.Artice.REQUEST_MAPPING_ALL_Artice+"error");
			throw new Exception(e.getMessage());
		}
		Response responsee = resultDTO.getBody();
		if (responsee !=null) {
			if (responsee.isSuccess()) {
				PageDTO pageDTO = (PageDTO) responsee.getData();
				return pageDTO;
			}else {
				throw new Exception(responsee.getMessage());

			}

		}else {
			throw new Exception(responsee.getMessage());

			}

	}

	//		 @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_DEL, method = RequestMethod.GET)
	//		 public String delArtice(@NotEmpty @PathVariable("id") Integer id){
	//		ResponseEntity<Response> resultDTO = null;
	//		RestTemplate restTemplate =  new RestTemplate();
	//		resultDTO=restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Artice.REQUEST_MAPPING, APPURIConstant.Artice.REQUEST_MAPPING_DEL,resultDTO,Response.class)
	//			 
	//			 
	//			return bizAppUrl;
	//			 
	//		 }
	//		
	  @RequestMapping(value = CenterURIConstant.Artice.REQUEST_MAPPING_FORWORD_ALL_ATRICE, method = RequestMethod.GET)
	 	public String forwardTOproductList() {
		  return CenterViewConstant.Artice.ARTICE_LIST;
	  }

}









