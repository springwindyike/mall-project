package com.ishare.mall.biz.restful.product.brand;

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
import com.ishare.mall.common.base.dto.information.BrandDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.BrandDTO;
import com.ishare.mall.common.base.exception.brand.BrandServiceException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.exception.ProductServiceException;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.information.BrandService;

/**
 * Created by YinLin on 2015/9/15.
 * Description : 品牌APP对外接口
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Brand.REQUEST_MAPPING)
public class BrandResource {

    private static final Logger log = LoggerFactory.getLogger(BrandResource.class);

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = APPURIConstant.Brand.REQUEST_MAPPING_GET_BRAND_DETAIL,method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json",},
            consumes = {"application/json",})
    public Response get(@RequestBody BrandDetailDTO brandDetailDTO) {
        Response response = new Response();
        try{
            //用findOne立即加载实体对象
            Brand brand = brandService.findOne(brandDetailDTO.getId());
            if(brand == null){
                response.setMessage("品牌不存在");
            }
            BeanUtils.copyProperties(brand,brandDetailDTO);
        }catch (BrandServiceException e){
            log.error(e.getMessage());
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        response.setData(brandDetailDTO);
        return response;
    }

    @RequestMapping(value = APPURIConstant.Brand.REQUEST_MAPPING_FIND_BY_PARAM,method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json",},
            consumes = {"application/json",})
    public Response findByParam(@RequestBody BrandDetailDTO brandDetailDTO){
        Response response = new Response();
        List<BrandDetailDTO> list = new ArrayList<BrandDetailDTO>();
        int offset = brandDetailDTO.getOffset();
        int limit = brandDetailDTO.getLimit();
        try {
            Page<Brand> page = null;
            PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
            if(brandDetailDTO.getMap() != null && !brandDetailDTO.getMap().isEmpty()){
                page = brandService.search(brandDetailDTO.getMap(),pageRequest);
            }else {
                page = brandService.search(null,pageRequest);
            }
            PageDTO pageDTO = new PageDTO();
            if(page != null && page.getContent() != null && page.getContent().size() >0){
                List<Brand> brandList = page.getContent();
                for (Brand brand:brandList){
                    BrandDetailDTO brandDetail = new BrandDetailDTO();
                    brandDetail.setOffset(offset);
                    brandDetail.setLimit(limit);
                    BeanUtils.copyProperties(brand,brandDetail);
                    list.add(brandDetail);
                }
                pageDTO.setContent(list);
                pageDTO.setTotalPages(page.getTotalPages());
                pageDTO.setITotalDisplayRecords(page.getTotalElements());
                pageDTO.setITotalRecords(page.getTotalElements());
                pageDTO.setOffset(offset);
                pageDTO.setLimit(limit);
            }else {
                response.setMessage("没有数据");
            }
            response.setData(pageDTO);
        }catch (BrandServiceException e){
            log.error(e.getMessage());
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }
    
    /**
     * 获取所有的品牌
     *
     * @return Page<ProductDTO>
     */
    @RequestMapping(value = APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response findByChannelId(@RequestBody BrandDTO brandDTO) {
        List<BrandDTO> brandList = new ArrayList<>();
        int offset = brandDTO.getOffset();
        int limit = brandDTO.getLimit();
        Response response = new Response();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit,Sort.Direction.DESC, "id");
        Page<Brand> result;
		try {
			result = brandService.findAllBrand(pageRequest);
		} catch (BrandServiceException e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
        PageDTO<BrandDTO> pageDTO = new PageDTO<BrandDTO>();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Brand> listBrand = result.getContent();
         for (Brand brand:listBrand){
        	 		BrandDTO brandDTOTemp = new BrandDTO();
                BeanUtils.copyProperties(brand, brandDTOTemp);
                brandList.add(brandDTOTemp);
            }
            pageDTO.setContent(brandList);
            pageDTO.setTotalPages(result.getTotalPages());
            pageDTO.setITotalDisplayRecords(result.getTotalElements());
            pageDTO.setITotalRecords(result.getTotalElements());
            response.setData(pageDTO);
        }
        return response;
    }
    
    /**
     * 根据id删除品牌
     * @param memberDTO
     * @return
     */
    @RequestMapping(value = APPURIConstant.Brand.REQUEST_MAPPING_DELETE_BY_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response delete(@RequestBody BrandDTO brandDTO){
   	 Product product = new Product();
   	Response response = new Response();
        try {
			brandService.delBrand(brandDTO.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
        return response;
   }
}
