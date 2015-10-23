package com.ishare.mall.manage.controller;

import java.util.List;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.dto.product.TreeNodeDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.form.CategoryForm;

/**
 * Created by liaochenglei on 2015/9/28. 
 * Description : 
 * Version 1.0
 */
@Controller
@RequestMapping(ManageURIConstant.Category.REQUEST_MAPPING)
public class CategoryController extends BaseController {

	@Autowired
	private RestTemplate restTemplate;
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
	
	@RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_ALL_TYPE, method = RequestMethod.GET,produces = {"application/json"})
	@ResponseBody
	public List<TreeNodeDTO> allCategory(Model model) {
		ResponseEntity<Response> resultDTO = null;
		TreeNodeDTO treeNodeDTO = new TreeNodeDTO();
	 	HttpEntity<TreeNodeDTO> requestDTO = new HttpEntity<TreeNodeDTO>(treeNodeDTO);
	//	RestTemplate restTemplate = new RestTemplate();
	/*	resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_ALL_TYPE), Response.class);*/
    	try {
    		resultDTO = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_ALL_TYPE),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response>() {
                    });
		
    	} catch (RestClientException e) {
			e.printStackTrace();
		}
    	List<TreeNodeDTO> TreeNodeDTOList =  (List<TreeNodeDTO>) resultDTO.getBody().getData();
		return TreeNodeDTOList;
    }
	
	@RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_ADD, method = RequestMethod.GET)
	public String add() {
		return ManageViewConstant.Category.ADD_CATEGORY;
	}
	
    @RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_ADD,method = RequestMethod.POST)
    public String saveCategory(CategoryForm categoryForm) {
    	ResponseEntity<Response> resultDTO = null;
    	ProductTypeDTO productTypeDTO = new ProductTypeDTO();
    	//productTypeDTO.setCode(categoryForm.getCode());
    	productTypeDTO.setLevel(categoryForm.getLevel()+1);
    	productTypeDTO.setNote(categoryForm.getNote());
    	productTypeDTO.setParentId(categoryForm.getParentId());
    	productTypeDTO.setTypeName(categoryForm.getName());
    	HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
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