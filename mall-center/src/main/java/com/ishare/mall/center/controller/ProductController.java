package com.ishare.mall.center.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.form.product.AddProductForm;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.general.Response;


/**
 * Created by liaochenglei on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING)
public class ProductController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public static Logger getLog() {
        return log;
    }
    
    @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_SAVE,method = RequestMethod.GET)
    public String addProduct(@ModelAttribute("productAttribute") AddProductForm apf) {
        return CenterViewConstant.Product.ADD_PRODUCT;
    }
    
    @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_SAVE, method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("productAttribute") AddProductForm addProductForm,HttpSession session,@CurrentMember MemberDTO member) {JSONObject jsonObject = new JSONObject((String)session.getAttribute("URL"));
	ProductDetailDTO productDetailDTO = new ProductDetailDTO();
/*	productDetailDTO.setName(apf.getProductName());
	productDetailDTO.setDescription(apf.getDescription());
	productDetailDTO.setTypeCode(apf.getTypeCode());
	productDetailDTO.setBasePrice(apf.getBasePrice());
	productDetailDTO.setMarketPrice(apf.getMarketPrice());
	productDetailDTO.setInventory(apf.getInventory());*/
	BeanUtils.copyProperties(addProductForm,productDetailDTO);
	if(jsonObject.has("url") ){
	 	productDetailDTO.setDefaultImageUrl(jsonObject.getString("url"));
	}
	productDetailDTO.setName(addProductForm.getProductName());
	productDetailDTO.setBrandId(1);
	productDetailDTO.setChannelId(1);
	productDetailDTO.setTypeId(1);
	productDetailDTO.setCreateByAccount("18566469285");
	ResponseEntity<Response> resultDTO = null;
	RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(
					APPURIConstant.Product.REQUEST_MAPPING,
					APPURIConstant.Product.REQUEST_MAPPING_SAVE),
					productDetailDTO, Response.class);
		} catch (Exception e) {
e.printStackTrace();
}
		ProductDetailDTO productDTOResult = (ProductDetailDTO) resultDTO.getBody().getData();
		return CenterViewConstant.Product.LIST_PRODUCT;}
    
    @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_UPDATE, method = RequestMethod.GET)
    public String updateProductGet(@NotEmpty @PathVariable("id") Integer id,Model model,@ModelAttribute("productAttribute") AddProductForm apf	) {

        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(id);
        ResponseEntity<Response> resultEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        resultEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_ID),productDetailDTO,Response.class);
        ProductDetailDTO returnTO =  (ProductDetailDTO) resultEntity.getBody().getData();
        model.addAttribute("productDetailDTO", returnTO);
        return CenterViewConstant.Product.UPDATE_PRODUCT;
    
    }
    
    @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_UPDATE, method = RequestMethod.POST)
    public String updateProductPost(@NotEmpty @PathVariable("id") Integer id,@ModelAttribute("productAttribute") AddProductForm addProductForm,HttpSession session,@CurrentMember MemberDTO member) {
    	JSONObject jsonObject = new JSONObject((String)session.getAttribute("URL"));
    	ProductDetailDTO productDetailDTO = new ProductDetailDTO();
    /*productDetailDTO.setName(apf.getProductName());
    	productDetailDTO.setDescription(apf.getDescription());
    	productDetailDTO.setTypeCode(apf.getTypeCode());
    	productDetailDTO.setBasePrice(apf.getBasePrice());
    	productDetailDTO.setMarketPrice(apf.getMarketPrice());
    	productDetailDTO.setInventory(apf.getInventory());*/
    	BeanUtils.copyProperties(addProductForm,productDetailDTO);
    	if(jsonObject.has("url") ){
    	 	productDetailDTO.setDefaultImageUrl(jsonObject.getString("url"));
    	}
    	productDetailDTO.setName(addProductForm.getProductName());
    	productDetailDTO.setBrandId(1);
    	productDetailDTO.setChannelId(1);
    	productDetailDTO.setTypeId(1);
    	productDetailDTO.setId(id);
    	productDetailDTO.setCreateByAccount("18566469285");
    	ResponseEntity<Response> resultDTO = null;
    	RestTemplate restTemplate = new RestTemplate();
			try {
				resultDTO = restTemplate.postForEntity(this.buildBizAppURI(
						APPURIConstant.Product.REQUEST_MAPPING,
						APPURIConstant.Product.REQUEST_MAPPING_SAVE),
						productDetailDTO, Response.class);
			} catch (Exception e) {
e.printStackTrace();
}
			if(resultDTO.getBody().isSuccess()){
				return CenterViewConstant.Product.LIST_PRODUCT;
			}
			return null;
    }
    
    @RequestMapping(value =  CenterURIConstant.Product.REQUEST_MAPPING_FIND_BY_ID, method = RequestMethod.GET,produces = {"application/json"})
    public ProductDetailDTO findById(@NotEmpty @PathVariable("id") Integer id) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(id);
        ResponseEntity<Response> resultEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        resultEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_ID),productDetailDTO,Response.class);
        ProductDetailDTO returnTO =  (ProductDetailDTO) resultEntity.getBody().getData();
        return returnTO;
    }
    
    @RequestMapping(value = CenterURIConstant.Product.ALL_TYPE_PRODUCT, produces = {"application/json"})
    @ResponseBody
    public ProductTypeDTO getType() {
    	ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_FIRST_LEVEL), Response.class);
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		return productTypeDTOResult;
    }
    
  @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_DEL, method = RequestMethod.GET)
	public String delProduct(@NotEmpty @PathVariable("id") Integer id) {
		ProductDetailDTO productDetailDTO = new ProductDetailDTO();
		productDetailDTO.setId(id);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_DEL), productDetailDTO, Response.class);
		if (resultDTO.getBody().isSuccess()){
				return "success";
  }	 return null;
  }
  
  @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_FORWORD, method = RequestMethod.GET)
 	public String forwardTOproductList() {
	  return CenterViewConstant.Product.LIST_PRODUCT;
  }
  
  @RequestMapping(value = CenterURIConstant.Product.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findByChannelId(HttpServletRequest request, Model model) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setChannelId(8);
		int displayLength = Integer.parseInt(request.getParameter("iDisplayLength"))==0?1:Integer.parseInt(request.getParameter("iDisplayLength"));
		int displayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int currentPage = displayStart/displayLength+1;
		productDTO.setLimit(displayLength);
		productDTO.setOffset(currentPage);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_CHANNEL_ID), productDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
				e.printStackTrace();		
				}
		PageDTO pageDTO = (PageDTO) resultDTO.getBody().getData();
		model.addAttribute("pageDTO",pageDTO);
		return pageDTO;
	}
  
	@RequestMapping(value = "/findBySearchCondition/{searchCondition}")
	public String findBySearchCondition(@PathVariable("searchCondition") String searchCondition,Model model){/*
		ProductDetailDTO memberDTO = new ProductDetailDTO();
		memberDTO.setMobile(searchCondition);
		memberDTO.setAccount(searchCondition);
		memberDTO.setName(searchCondition);
		ResponseEntity<MemberDTO> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING,APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CONDITION),memberDTO,MemberDTO.class);
		MemberDTO memberDTOResult = resultDTO.getBody();
		model.addAttribute("pageDTO",memberDTOResult.getPageDTO());
		return CenterViewConstant.Member.MEMBER_LIST;
	*/
	return null;	
	}
}
