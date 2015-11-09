package com.ishare.mall.manage.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.product.BrandDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.dto.product.TreeNodeDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.form.BrandForm;
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
    	productTypeDTO.setCode(categoryForm.getCode());
    	productTypeDTO.setLevel(categoryForm.getLevel()+2);
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
    	if(resultDTO.getBody().isSuccess()==true){
    		   return "redirect:/category/list.dhtml";
    	}
		return null;
    }
    
    /**
     * 根据id删除分类
     * @param 
     * @param 
     * @return
     * @throws Exception
     */
  	@ResponseBody
  	@RequestMapping(value =  ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_DEL_BY_ID)
  	public Response delete(@NotEmpty @PathVariable("id") Integer id) throws Exception{
  		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
  		productTypeDTO.setId(id);
  		ResponseEntity<Response<ProductTypeDTO>> resultDTO = null;
  		HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
  		try{
  			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_DEL_BY_ID),
  					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ProductTypeDTO>>() {});
  		}catch (Exception e){
  			log.error("call bizp app "+APPURIConstant.ProductType.REQUEST_MAPPING+APPURIConstant.ProductType.REQUEST_MAPPING_DEL_BY_ID+"error");
  			throw new Exception(e.getMessage());
  		}
  		Response response = resultDTO.getBody();
  		if(response == null){
  			throw new Exception("get response error");
  		}
  		return response;
  	}
  	/**
  	 * 编辑分类
  	 * @param 
  	 * @return
  	 * @throws Exception
  	 */
	@ResponseBody
	@RequestMapping(value = ManageURIConstant.Category.REQUEST_MAPPING_CATEGORY_UPDATE)
	public Response update(@NotEmpty @PathVariable("id") Integer id, @NotEmpty @PathVariable("name") String name) throws Exception{
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setId(id);
		productTypeDTO.setTypeName(name);
		ResponseEntity<Response<ProductTypeDTO>> resultEntity = null;
		HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
		try{
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_UPDATE_BY_ID),
  					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ProductTypeDTO>>() {});
  			
		}catch (Exception e){
			e.printStackTrace();
			log.error("call bizp app "+APPURIConstant.ProductType.REQUEST_MAPPING+ APPURIConstant.ProductType.REQUEST_MAPPING_UPDATE_BY_ID+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultEntity.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
	return response;
	}
}