package com.ishare.mall.api.restful;


import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.utils.Servlets;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.information.BrandDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;


/**
 * Created by YinLin on 2015/8/10.
 * Description:品牌接口
 * Version 1.0
 */
@RestController
@RequestMapping("/brands")
public class BrandResource  extends BaseResource {

    private static final Logger log = LoggerFactory.getLogger(BrandResource.class);
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 通过类型ID获取单个类型信息  格式 /brands/{id} GET
     * @param id 商品ID
     * @return ProductDetailDTO 返回的数据对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity get(@NotEmpty @PathVariable("id") Integer id) {
        log.debug("brand id is "+id);
        Response response;
        BrandDetailDTO brandDetailDTO = new BrandDetailDTO();
        brandDetailDTO.setId(id);
        ResponseEntity<Response<BrandDetailDTO>> responseEntity = null;
        HttpEntity<BrandDetailDTO> httpEntity = new HttpEntity<BrandDetailDTO>(brandDetailDTO);
        try{
            responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.Brand.REQUEST_MAPPING_GET_BRAND_DETAIL),
                    HttpMethod.POST, httpEntity,new ParameterizedTypeReference<Response<BrandDetailDTO>>(){});

        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setMessage("服务器内部错误");
            response.setSuccess(Response.Status.FAILURE);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 通过当前页和每页数量获取商品列表 格式 /products/offset/{offset}/limit/{limit} GET
     * @param offset 分页下标
     * @param limit 分页size
     * @return 返回ProductListDTO
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity get(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
        log.debug("offset = " + offset + " limit = " + limit);
        Response response;
        BrandDetailDTO brandDetailDTO = new BrandDetailDTO();
        brandDetailDTO.setOffset(offset);
        brandDetailDTO.setLimit(limit);
        ResponseEntity<Response<PageDTO>> responseResponseEntity = null;
        HttpEntity<BrandDetailDTO> httpEntity = new HttpEntity<BrandDetailDTO>(brandDetailDTO);
        try{
            responseResponseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,httpEntity,new ParameterizedTypeReference<Response<PageDTO>>(){});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setMessage("服务器内部错误");
            response.setSuccess(Response.Status.FAILURE);
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = responseResponseEntity.getBody();
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

    /**
     * 根据条件查询商品
     * @param request http request
     * @return 返回查询结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity search(final HttpServletRequest request) {
        Response response;
        int offset = 1;
        int limit = 15;
        if (StringUtils.isNotEmpty(request.getParameter(OFFSET))) {
            offset = Integer.valueOf(request.getParameter(OFFSET));
            if (offset <= 0) {
                offset = 1;
            }
        }
        if (StringUtils.isNotEmpty(request.getParameter(LIMIT))) {
            limit = Integer.valueOf(request.getParameter(LIMIT));
            if (limit <= 0) {
                limit = 15;
            }
        }
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        log.debug("searchParams: {}", searchParams);
        BrandDetailDTO brandDetailDTO = new BrandDetailDTO();
        brandDetailDTO.setLimit(limit);
        brandDetailDTO.setOffset(offset);
        brandDetailDTO.setMap(searchParams);
        ResponseEntity<Response<PageDTO>> responseResponseEntity = null;
        HttpEntity<BrandDetailDTO> httpEntity = new HttpEntity<BrandDetailDTO>(brandDetailDTO);
        try {
            responseResponseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,httpEntity,new ParameterizedTypeReference<Response<PageDTO>>(){});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setMessage("服务器内部错误");
            response.setSuccess(Response.Status.FAILURE);
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response = responseResponseEntity.getBody();
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }
    public static Logger getLog() {
        return log;
    }
}
