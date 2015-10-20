package com.ishare.mall.api.service.product.impl;

import com.ishare.mall.api.service.BaseService;
import com.ishare.mall.api.service.product.ProductService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductListDTO;
import com.ishare.mall.common.base.exception.product.ProductServiceException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by wanghao on 2015/10/19.
 */
@Service
public class ProductServiceImpl extends BaseService implements ProductService{

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductDetailDTO findOne(Integer id) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(id);
        ResponseEntity<Response<ProductDetailDTO>> resultEntity = null;
        HttpEntity<ProductDetailDTO> requestDTO = new HttpEntity<ProductDetailDTO>(productDetailDTO);
        try{
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_ID),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ProductDetailDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ProductServiceException(e.getMessage());
        }
        //获取返回结果
        Response<ProductDetailDTO> response = resultEntity.getBody();
        return response.getData();
    }

    @Override
    public ProductDetailDTO findByCode(String code) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setCode(code);
        ResponseEntity<Response<ProductDetailDTO>> resultEntity = null;
        HttpEntity<ProductDetailDTO> requestDTO = new HttpEntity<ProductDetailDTO>(productDetailDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_CODE),
                    HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<ProductDetailDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ProductServiceException(e.getMessage());
        }
        //获取返回结果
        Response<ProductDetailDTO> response = resultEntity.getBody();
        //返回结果有错
        return response.getData();
    }

    @Override
    public PageDTO<ProductListDTO> findByPage(Integer offset, Integer limit,Map<String,Object> map) {
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        if(map != null && !map.isEmpty()){
            productListDTO.setMap(map);
        }
        ResponseEntity<Response<PageDTO<ProductListDTO>>> resultEntity = null;
        HttpEntity <ProductListDTO> requestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try{
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<PageDTO<ProductListDTO>>>() {});
        }catch (Exception e){
            log.error(e.getMessage());

        }
        //获取返回结果
        Response<PageDTO<ProductListDTO>> response = resultEntity.getBody();
        return response.getData();
    }
}
