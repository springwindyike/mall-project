package com.ishare.mall.biz.restful.Attribute;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.attribute.AttributeDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.product.Attribute;
import com.ishare.mall.core.model.product.AttributeGroup;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.information.AttributeGroupService;
import com.ishare.mall.core.service.information.AttributeService;
import com.ishare.mall.core.service.product.ProductTypeService;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Attribute.REQUEST_MAPPING)
public class AttributeResource {
	
    private static final Logger log = LoggerFactory.getLogger(AttributeResource.class);
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private AttributeGroupService attributeGroupService;
		 @Autowired
		 private ProductTypeService productTypeService;

    public static Logger getLog() {
        return log;
    }

    @RequestMapping(value = APPURIConstant.Attribute.REQUEST_MAPPING_SAVE, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response saveProduct(@RequestBody AttributeDTO attributeDTO){
        Attribute attribute = new Attribute();
        AttributeGroup attributeGroup = new AttributeGroup();
        ProductType productType = new ProductType();
        BeanUtils.copyProperties(attributeDTO, attribute);
        attribute.setAttributeGroup(attributeGroupService.findOne(attributeDTO.getAttributeGroupId()));
        
        attribute.setProductType(productTypeService.findOne(attributeDTO.getProductTypeId()));
             
    			
    			try {
					attributeService.save(attribute);
				} catch (Exception e) { 
									log.error(e.getMessage(), e);
	            Response response = new Response();
	            response.setMessage("系统错误");
	            response.setSuccess(false);
	            return response;
	            }
    			 Response response = new Response();
    			return response;
}
}
