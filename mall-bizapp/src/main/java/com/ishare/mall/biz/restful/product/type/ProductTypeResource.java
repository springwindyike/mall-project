package com.ishare.mall.biz.restful.product.type;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.dto.product.TreeNodeDTO;
import com.ishare.mall.common.base.exception.brand.BrandServiceException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.exception.ProductTypeServiceException;
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
    /**
     * 直接返回所有的allType
     * @return
     */
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIND_ALL_TYPE, method = RequestMethod.POST,headers = "Accept=application/xml, application/json",produces = {"application/json", "application/xml"})
    public Response getProductTypeAllTwo() {
        List<ProductType> productTypeList;
        Response response = new Response();
		try {
			productTypeList = productTypeService.findAllType();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
	}
		 if (productTypeList != null && productTypeList.size() > 0) {
	            //转换DTO
	        	List<TreeNodeDTO> productTypes = 	(List<TreeNodeDTO>) MapperUtils.mapAsList(productTypeList, TreeNodeDTO.class);
	        	for(TreeNodeDTO tnd : productTypes){
	        		if (tnd.getParentId()==null){
	        			tnd.setParentId(0);
	        		}
	        	}
	        	response.setData(productTypes);
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
     * 
     * @param productTypeDTO
     * @return
     */
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_ID, method = RequestMethod.POST,headers = "Accept=application/xml, application/json",produces = {"application/json", "application/xml"})
    public Response findById(@RequestBody ProductTypeDTO productTypeDTO) {
    	ProductType productType ;
    	 Response response = new Response();
    	try {
			productType = productTypeService.findOne(productTypeDTO.getId());
			if(productType != null){
				ProductTypeDTO returnDTO = 	(ProductTypeDTO) MapperUtils.map(productType, ProductTypeDTO.class);
				response.setData(returnDTO);
			}
		} catch (ProductTypeServiceException e) {
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
   @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_QUERY_BY_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response queryByID(@RequestBody ProductTypeDTO productTypeDTO){
		Response response = new Response();
		try{
			ProductType productType = productTypeService.findOne(productTypeDTO.getId());
			if(productType == null) return response;
			ProductTypeDTO returnTO = (ProductTypeDTO)MapperUtils.map(productType,ProductTypeDTO.class);
			List<ProductType> productTypeChild = productTypeService.findByParentId(productType.getId());
			List <ProductTypeDTO> productTypeDTOChildList = new ArrayList<ProductTypeDTO>();
			if (productTypeChild != null && productTypeChild.size() > 0){
				for (ProductType ptc:productTypeChild){
					ProductTypeDTO productTypeDTOChild = (ProductTypeDTO)MapperUtils.map(ptc,ProductTypeDTO.class);
					productTypeDTOChildList.add(productTypeDTOChild);
					List<ProductType> productTypeGrandson = productTypeService.findByParentId(ptc.getId());
					if (productTypeGrandson != null && productTypeChild.size() > 0){
						List <ProductTypeDTO> productTypeDTOGrandsonList = new ArrayList<ProductTypeDTO>();
						for (ProductType ptg:productTypeGrandson){
							ProductTypeDTO productTypeDTOGrand = (ProductTypeDTO)MapperUtils.map(ptg,ProductTypeDTO.class);
							productTypeDTOGrandsonList.add(productTypeDTOGrand);
						}
						productTypeDTOChild.setChild(productTypeDTOGrandsonList);
					}
				}
				returnTO.setChild(productTypeDTOChildList);
			}
			response.setData(returnTO);
		}catch (ProductTypeServiceException e){
			log.error(e.getMessage());
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
	   	}
		return response;
    }
    
    

    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response findByParam(@RequestBody ProductTypeDTO productTypeDTO){
		Response response = new Response();
        List<ProductTypeDTO> list = new ArrayList<ProductTypeDTO>();
        Integer limit = productTypeDTO.getLimit();
        Integer offset = productTypeDTO.getOffset();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
        Page<ProductType> result = null;
		try {
			if(productTypeDTO.getMap() != null && !productTypeDTO.getMap().isEmpty()){
				result = productTypeService.search(productTypeDTO.getMap(), pageRequest);
			}else {
				result = productTypeService.search(null, pageRequest);
			}
			PageDTO pageDTO = new PageDTO();
			if(result != null && result.getSize() > 0 && result.getContent() != null && result.getContent().size() >0){
				List<ProductType> productTypeList = result.getContent();
				for (ProductType pt:productTypeList){
					ProductTypeDTO ptd = (ProductTypeDTO)MapperUtils.map(pt,ProductTypeDTO.class);
					List<ProductType> productTypeChild = productTypeService.findByParentId(pt.getId());
					List <ProductTypeDTO> productTypeDTOChildList = new ArrayList<ProductTypeDTO>();
					if (productTypeChild != null && productTypeChild.size() > 0){
						for (ProductType ptc:productTypeChild){
							ProductTypeDTO productTypeDTOChild = (ProductTypeDTO)MapperUtils.map(ptc,ProductTypeDTO.class);
							productTypeDTOChildList.add(productTypeDTOChild);
							List<ProductType> productTypeGrandson = productTypeService.findByParentId(ptc.getId());
							if (productTypeGrandson != null && productTypeChild.size() > 0){
								List <ProductTypeDTO> productTypeDTOGrandsonList = new ArrayList<ProductTypeDTO>();
								for (ProductType ptg:productTypeGrandson){
									ProductTypeDTO productTypeDTOGrand = (ProductTypeDTO)MapperUtils.map(ptg,ProductTypeDTO.class);
									productTypeDTOGrandsonList.add(productTypeDTOGrand);
								}
								productTypeDTOChild.setChild(productTypeDTOGrandsonList);
							}
						}
						ptd.setChild(productTypeDTOChildList);
					}
					list.add(ptd);
				}
				pageDTO.setContent(list);
				pageDTO.setTotalPages(result.getTotalPages());
				pageDTO.setITotalDisplayRecords(result.getTotalElements());
				pageDTO.setITotalRecords(result.getTotalElements());
				pageDTO.setLimit(limit);
				pageDTO.setOffset(offset);
				log.debug("total page = " + result.getTotalPages() + "total element = " + result.getTotalElements());
			}
			response.setData(pageDTO);
		}catch (ProductTypeServiceException e){
			log.error(e.getMessage());
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}

        return response;
    }

    public static Logger getLog() {
        return log;
    }
    
    @RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_SAVE, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response saveProductType(@RequestBody ProductTypeDTO productTypeDTO){
        ProductType productType = new ProductType();
        int temp =0;
        List<	ProductType> allChild = productTypeService.findByParentId(productTypeDTO.getParentId());
        if (allChild!=null && !allChild.isEmpty()){
            for(ProductType pt:allChild){
            	int begin = Integer.parseInt(pt.getCode());
             if(temp<begin) {
            	 temp = begin;
             }
            }  productTypeDTO.setCode(String.valueOf(temp+1));
        }else {
        	productTypeDTO.setCode(productTypeDTO.getCode()+"001");
        }
      
        BeanUtils.copyProperties(productTypeDTO, productType);
        ProductType parent = productTypeService.findOne(productTypeDTO.getParentId());
       
 
      
        
        productType.setParent(parent);
        productType.setName(productTypeDTO.getTypeName());
    			try {
					productTypeService.saveProductType(productType);
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
    
    /**
     * 根据id删除品牌
     * @param memberDTO
     * @return
     */
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_DEL_BY_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response delete(@RequestBody ProductTypeDTO productTypeDTO){
   	Response response = new Response();
        try {
        	if (productTypeService.findOne(productTypeDTO.getId())!= null){
        		productTypeService.delProductType(productTypeDTO.getId());;
        	} else {
        		response.setSuccess(false);
        		response.setMessage("删除分类失败");
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
     * 修改分类信息
     * @return
     */
    @RequestMapping(value = APPURIConstant.ProductType.REQUEST_MAPPING_UPDATE_BY_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response update(@RequestBody ProductTypeDTO productTypeDTO){
        Response response = new Response();
        try {
        	ProductType productType = productTypeService.findOne(productTypeDTO.getId());
            if (productType != null){
            	productType.setName(productTypeDTO.getTypeName());
            	productTypeService.saveProductType(productType);
            }
        }catch (BrandServiceException e){
            log.error(e.getMessage());
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }
}
