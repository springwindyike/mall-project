package com.ishare.mall.biz.restful.product.type;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductTypeService;
import com.ishare.mall.core.utils.mapper.MapperUtils;

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
     * 通过用户账号获取所有的用户菜单，一次性三级菜单都进行返回
     * @return 返回 MemberPermissionDTO JSON
     */
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIND_FIRST_LEVEL, method = RequestMethod.GET,headers = "Accept=application/xml, application/json",produces = {"application/json", "application/xml"})
    public Response getProductTypeAll() {
        //查找第一级菜单
        List<ProductType> productTypeList;
        Response response = new Response();
		try {
			productTypeList = productTypeService.findByLevel(1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
	}
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
            			   List<ProductType> chidlrenTwoProductTypeList;
						try {
							chidlrenTwoProductTypeList = productTypeService.findByParentId(ptdt.getId());
						} catch (Exception e) {
							log.error(e.getMessage(), e);
							response.setMessage("系统错误");
							response.setSuccess(false);
							return response;
					}
        if(chidlrenTwoProductTypeList != null){
        List<ProductTypeDTO> productTwoChildTypes = 	(List<ProductTypeDTO>) MapperUtils.mapAsList(chidlrenTwoProductTypeList, ProductTypeDTO.class);
        ptdt.setChild(productTwoChildTypes);
            			   }
            		   }
        		   }
               }
        	returnProductDTO.setChild(productFirstTypes);
        	response.setData(returnProductDTO);
        	return response;
        }
		return null;
    }
    
    
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIRST_LEVEL, method = RequestMethod.GET,headers = "Accept=application/xml, application/json",produces = {"application/json", "application/xml"})
    public Response getProductFirstType() {
    	  List<ProductType> productTypeList;
      Response response = new Response();
      ProductTypeDTO returnProductDTO = new ProductTypeDTO();
    	try {
			productTypeList = productTypeService.findByLevel(1);
		        if (productTypeList != null && productTypeList.size() > 0) {
		            //转换DTO
		        	List<ProductTypeDTO> productFirstTypes = 	(List<ProductTypeDTO>) MapperUtils.mapAsList(productTypeList, ProductTypeDTO.class);
		        	returnProductDTO.setChild(productFirstTypes);
		        	response.setData(returnProductDTO);
		        	}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
	}
     	return response;
    }

    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_CHILDREN_LEVEL, method = RequestMethod.POST,headers = "Accept=application/xml, application/json",produces = {"application/json", "application/xml"})
    public Response getProductChildType(@RequestBody ProductTypeDTO productTypeDTO) {
    	  List<ProductType> productTypeList;
      Response response = new Response();
      ProductTypeDTO returnProductDTO = new ProductTypeDTO();
    	try {
			productTypeList = productTypeService.findByParentId(productTypeDTO.getParentId());
		        if (productTypeList != null && productTypeList.size() > 0) {
		            //转换DTO
		        	List<ProductTypeDTO> productFirstTypes = 	(List<ProductTypeDTO>) MapperUtils.mapAsList(productTypeList, ProductTypeDTO.class);
		        	returnProductDTO.setChild(productFirstTypes);
		        	response.setData(returnProductDTO);
		        	}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
	}
     	return response;
    }
    /**
     * 根据ID获取商品类别的详细信息
     * @param productTypeDTO
     * @return
     */
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ProductTypeDTO findByID(@RequestBody ProductTypeDTO productTypeDTO){
        ProductType productType = productTypeService.findOne(productTypeDTO.getId());
       // List <ProductTypeDTO> productTypeDTOList = new ArrayList<ProductTypeDTO>();
        if(productType == null) return productTypeDTO;
        ProductTypeDTO returnTO = (ProductTypeDTO)MapperUtils.map(productType,ProductTypeDTO.class);;
        List<ProductType> productTypeChild = productTypeService.findByParentId(productType.getId());
        List <ProductTypeDTO> productTypeDTOChildList = new ArrayList<ProductTypeDTO>();
        if (productTypeChild != null && productTypeChild.size() > 0){
            for (ProductType ptc:productTypeChild){
                ProductTypeDTO productTypeDTOChild = new ProductTypeDTO();
                BeanUtils.copyProperties(ptc, productTypeDTOChild);
                productTypeDTOChildList.add(productTypeDTOChild);
                List<ProductType> productTypeGrandson = productTypeService.findByParentId(ptc.getId());
                if (productTypeGrandson != null && productTypeChild.size() > 0){
                    List <ProductTypeDTO> productTypeDTOGrandsonList = new ArrayList<ProductTypeDTO>();
                    for (ProductType ptg:productTypeGrandson){
                        ProductTypeDTO productTypeDTOGrand = new ProductTypeDTO();
                        BeanUtils.copyProperties(ptg,productTypeDTOGrand);
                        productTypeDTOGrandsonList.add(productTypeDTOGrand);
                    }
                    productTypeDTOChild.setChild(productTypeDTOGrandsonList);
                }
            }
            returnTO.setChild(productTypeDTOChildList);
        }
        return returnTO;
    }
    
    

//    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM, method = RequestMethod.POST,
//            headers = "Accept=application/xml, application/json",
//            produces = {"application/json", "application/xml"},
//            consumes = {"application/json", "application/xml"})
//    public ProductTypeDTO findByParam(@RequestBody ProductTypeDTO productTypeDTO){
//        List<ProductTypeDTO> list = new ArrayList<ProductTypeDTO>();
//        Integer limit = productTypeDTO.getLimit();
//        Integer offset = productTypeDTO.getOffset();
//        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
//        Page<ProductType> result = null;
//        if(productTypeDTO.getMap() != null && !productTypeDTO.getMap().isEmpty()){
//            result = productTypeService.search(productTypeDTO.getMap(), pageRequest);
//        }else {
//            result = productTypeService.search(null, pageRequest);
//        }
//        PageDTO pageDTO = new PageDTO();
//        if(result != null && result.getSize() > 0 && result.getContent() != null && result.getContent().size() >0){
//            for(ProductType productType:result){
//                List<ProductType> productTypeList = result.getContent();
//
//            }
//        }
//        return null;
//    }

    public static Logger getLog() {
        return log;
    }
}
