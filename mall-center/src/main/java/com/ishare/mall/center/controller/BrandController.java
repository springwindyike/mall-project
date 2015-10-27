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
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.BrandDTO;
import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.common.base.general.Response;


/**
 * Created by liaochenglei on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(value = CenterURIConstant.Brand.REQUEST_MAPPING)
public class BrandController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    public static Logger getLog() {
        return log;
    }
    
   
  
  @RequestMapping(value = CenterURIConstant.Brand.REQUEST_MAPPING_FORWORD, method = RequestMethod.GET)
 	public String forwardTOproductList() {
	  return CenterViewConstant.Brand.LIST_BRAND;
  }
  
  @RequestMapping(value = CenterURIConstant.Brand.REQUEST_MAPPING_FIND_ALL_BRAND, method = RequestMethod.GET,produces = {"application/json"})
	@ResponseBody
	public PageDTO findAllBrand(@CurrentMember CurrentMemberDTO currentMemberDTO, HttpServletRequest request, Model model) throws Exception{
		BrandDTO brandDTO = new BrandDTO();
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		brandDTO.setLimit(displayLength);
		brandDTO.setOffset(currentPage);
		HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
/*		ResponseEntity<Response> resultDTO = null;*/
		ResponseEntity<Response<PageDTO<BrandDTO>>> resultDTO = null;

		try {/*
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING,APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND), brandDTO, Response.class);
		*/

			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING,APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<BrandDTO>>>() {});

			
		} catch (Exception e) {
			log.error("call bizp app " + APPURIConstant.Brand.REQUEST_MAPPING + APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND + "error");
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
