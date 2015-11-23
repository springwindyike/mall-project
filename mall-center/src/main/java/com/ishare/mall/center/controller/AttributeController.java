package com.ishare.mall.center.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.attribute.AttributeDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.BrandDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.general.Response;
/**
 * 系统日志Controller
 * @author zhangzhaoxin
 *
 */
@Controller
@RequestMapping(value = CenterURIConstant.Attribute.REQUEST_MAPPING)
public class AttributeController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;

	private static final Logger log = LoggerFactory.getLogger(AttributeController.class);


	public static Logger getlog(){
		return log;

	}

    @RequestMapping(value = CenterURIConstant.Attribute.REQUEST_MAPPING_SAVE, method = RequestMethod.GET)
    public AttributeDTO addAttributeGet(@RequestParam int productTypeId,@RequestParam String attributeName,@RequestParam int attributeGroupId)throws Exception {
	AttributeDTO attributeDTO = new AttributeDTO();
	attributeDTO.setAttributeGroupId(attributeGroupId);
	attributeDTO.setName(attributeName);
	attributeDTO.setProductTypeId(productTypeId);

	HttpEntity<AttributeDTO> requestDTO = new HttpEntity<AttributeDTO>(attributeDTO);
	ResponseEntity<Response<AttributeDTO>> resultDTO = null;
	try {
		resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Attribute.REQUEST_MAPPING,APPURIConstant.Attribute.REQUEST_MAPPING_SAVE),
				HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<AttributeDTO>>() {});
	} catch (Exception e) {
		log.error("call bizp app " + APPURIConstant.Attribute.REQUEST_MAPPING + APPURIConstant.Attribute.REQUEST_MAPPING_SAVE + "error");
		throw new Exception(e.getMessage());
	}
	Response response = resultDTO.getBody();
	if(response != null) {
		if(response.isSuccess()){
			AttributeDTO pageDTO = (AttributeDTO)response.getData();
		}else {
			throw new Exception(response.getMessage());
		}
	}else{
		throw new Exception("get response error");
	}
	return null;
}
}








