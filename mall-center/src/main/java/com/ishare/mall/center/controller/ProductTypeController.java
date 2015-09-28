package com.ishare.mall.center.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.general.Response;


/**
 * Created by liaochenglei on 2015/9/24.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(value = CenterURIConstant.ProductType.REQUEST_MAPPING)
public class ProductTypeController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ProductTypeController.class);

    public static Logger getLog() {
        return log;
    }

    @RequestMapping(value = CenterURIConstant.ProductType.REQUEST_MAPPING_FITST_LEVEL, produces = {"application/json"})
    @ResponseBody
    public ProductTypeDTO getFirstLevelType() {
    	ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIRST_LEVEL), Response.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		return productTypeDTOResult;
    }
    
    @RequestMapping(value = CenterURIConstant.ProductType.REQUEST_MAPPING_CHILDREN_LEVEL, produces = {"application/json"})
    @ResponseBody
	public ProductTypeDTO getChildLevel(
			@NotEmpty @PathVariable("parentId") Integer parentId) {
		ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setParentId(parentId);
		RestTemplate restTemplate = new RestTemplate();
		try {
	        resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING,APPURIConstant.ProductType.REQUEST_MAPPING_CHILDREN_LEVEL),productTypeDTO,Response.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ProductTypeDTO productTypeDTOResult = (ProductTypeDTO) resultDTO
				.getBody().getData();
		return productTypeDTOResult;
	}
    
    @RequestMapping(value = CenterURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_ID, produces = {"application/json"})
    @ResponseBody
	public ProductTypeDTO findById(@NotEmpty @PathVariable("id") Integer id) {

		ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setId(id);
		RestTemplate restTemplate = new RestTemplate();
		try {
	        resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING,APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_ID),productTypeDTO,Response.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ProductTypeDTO productTypeDTOResult = (ProductTypeDTO) resultDTO
				.getBody().getData();
		return productTypeDTOResult;
	
    }
}
