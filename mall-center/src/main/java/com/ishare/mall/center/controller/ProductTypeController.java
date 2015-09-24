package com.ishare.mall.center.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ProductTypeDTO getType() {
    	ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIRST_LEVEL), Response.class);
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		return productTypeDTOResult;
    }
}
