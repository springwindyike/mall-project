package com.ishare.mall.biz.restful.product.type;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductTypeService;
import com.ishare.mall.core.utils.mapper.MapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.ProductType.REQUEST_MAPPING)
public class ProductTypeResource {

    private static final Logger log = LoggerFactory.getLogger(ProductTypeResource.class);

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 通过用户账号获取所有的用户权限
     * @param account 用户账户
     * @return 返回 MemberPermissionDTO JSON
     */
    @RequestMapping(value       = "/findFirstLevel",
            method      = RequestMethod.GET,
            headers     = "Accept=application/xml, application/json",
            produces    = {"application/json", "application/xml"})
    public ProductTypeDTO getProductTypeAll() {
        //查找第一级菜单
        List<ProductType> productTypeList = productTypeService.findByLevel(1);
        ProductTypeDTO returnProductDTO = new ProductTypeDTO();
        if (productTypeList != null && productTypeList.size() > 0) {
            //转换DTO
        	List<ProductTypeDTO> productFirstTypes = 	(List<ProductTypeDTO>) MapperUtils.mapAsList(productTypeList, ProductTypeDTO.class);
        	   for (ProductTypeDTO ptd:productFirstTypes){
        		   List<ProductType> chidlrenProductTypeList = productTypeService.findByParentId(ptd.getId());
        		   if(chidlrenProductTypeList != null){
        			   List<ProductTypeDTO> productChildTypes = 	(List<ProductTypeDTO>) MapperUtils.mapAsList(chidlrenProductTypeList, ProductTypeDTO.class);
            		   ptd.setChild(productChildTypes);
            		   for (ProductTypeDTO ptdt:productChildTypes){
            			   List<ProductType> chidlrenTwoProductTypeList = productTypeService.findByParentId(ptdt.getId());
            			   if(chidlrenTwoProductTypeList != null){
            				   List<ProductTypeDTO> productTwoChildTypes = 	(List<ProductTypeDTO>) MapperUtils.mapAsList(chidlrenTwoProductTypeList, ProductTypeDTO.class);
            				   ptdt.setChild(productTwoChildTypes);
            			   }
            		   }
        		   }
               }
        	returnProductDTO.setChild(productFirstTypes);
        	return returnProductDTO;
        }
      //  log.debug(roleDTO.toString());
		return null;
    }

    public static Logger getLog() {
        return log;
    }
}
