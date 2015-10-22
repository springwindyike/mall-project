package com.ishare.mall.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.express.ExpressDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.controller.base.BaseController;

/**
 * Created by liaochenglei on 2015/9/28. 
 * Description : 
 * Version 1.0
 */
@Controller
@RequestMapping(ManageURIConstant.Category.REQUEST_MAPPING)
public class CategoryController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(CategoryController.class);

	public static Logger getLog() {
		return log;
	}


	@RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_LIST, method = RequestMethod.GET)
	public String listCategory(Model model) {
/*    	ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_FIRST_LEVEL), Response.class);
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		model.addAttribute("type", productTypeDTOResult);*/
		return ManageViewConstant.Category.LIST_CATEGORY;
    }
	
	@RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_ALL_TYPE, method = RequestMethod.GET)
	@ResponseBody
	public ProductTypeDTO allCategory(Model model) {
    	ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_FIRST_LEVEL), Response.class);
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		return productTypeDTOResult;
    }
	
	@RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_ADD, method = RequestMethod.GET)
	public String add() {
		return ManageViewConstant.Category.ADD_CATEGORY;
	}
	
    @RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_ADD_TEST, method = RequestMethod.GET)
    public String saveCategory() {/*
    	ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_SAVE), Response.class);
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		return productTypeDTOResult;
    */

    	ResponseEntity<Response> resultDTO = null;
    	ProductTypeDTO productTypeDTO = new ProductTypeDTO();
    	productTypeDTO.setCode("2001");
    	productTypeDTO.setLevel(1);
    	productTypeDTO.setNote("卧槽");
    	productTypeDTO.setParentId(null);
    	productTypeDTO.setTypeName("皮靴");
    	HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		resultDTO = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_SAVE),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response>() {
                    });
		
    	} catch (RestClientException e) {
			e.printStackTrace();
		}
		String returnExchange =	(String) resultDTO.getBody().getData();
  return returnExchange;
		
    }
}