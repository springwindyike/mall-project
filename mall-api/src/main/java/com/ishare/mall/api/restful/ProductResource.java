package com.ishare.mall.api.restful;

import com.google.common.collect.Maps;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductListDTO;
import com.ishare.mall.api.utils.Servlets;
import com.ishare.mall.api.utils.page.PageUtils;
import com.ishare.mall.common.base.general.Response;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * Created by YinLin on 2015/7/30.
 * Description:产品接口相关
 * Version 1.0
 */
@RestController
@RequestMapping("/products")
public class ProductResource extends BaseResource{

    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 通过商品ID获取单个商品信息  格式 /products/{id} GET
     * @param id 商品ID
     * @return ProductDetailDTO 返回的数据对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity get(@NotEmpty @PathVariable("id") Integer id) {
        //用findOne立即加载实体对象
        //Product product = productService.findOne(id);
        //转换为接口输出数据对象DTO 映射规则需要自己添加
        Response response;
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(id);
        ResponseEntity<Response<ProductDetailDTO>> resultEntity = null;
        HttpEntity<ProductDetailDTO> requestDTO = new HttpEntity<ProductDetailDTO>(productDetailDTO);
        try{
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_ID),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ProductDetailDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 通过商品编号获取单个商品信息  格式 /products/{id} GET
     * @param code 商品ID
     * @return ProductDetailDTO 返回的数据对象
     */
	@RequestMapping(value = "code/{code}", method = RequestMethod.GET,produces = {"application/json"})
	public ResponseEntity getByCode(@NotEmpty @PathVariable("code") String code) {
		//用findOne立即加载实体对象
		//Product product = productService.findByCode(code);
		//转换为接口输出数据对象DTO 映射规则需要自己添加
        Response response;
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setCode(code);
        ResponseEntity<Response<ProductDetailDTO>> resultEntity = null;
        HttpEntity<ProductDetailDTO> requestDTO = new HttpEntity<ProductDetailDTO>(productDetailDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_CODE),
                    HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<ProductDetailDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
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
        Response response;
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        ResponseEntity<Response<ProductListDTO>> resultEntity = null;
        HttpEntity <ProductListDTO> requestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try{
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 通过品牌名称获取当前商品列表 格式/products/{currentPage}/{pageSize}/brand/{name} GET
     * @param offset 当前页
     * @param limit 每页数据
     * @param name 品牌名字
     * @return 返回list
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}/brand/{name}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity listByBrandName(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("name")String name) {
        Response response;
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        Map<String, Object> searchParams = Maps.newConcurrentMap();
        searchParams.put("LIKE_brand.name", name);
        productListDTO.setMap(searchParams);
        ResponseEntity<Response<ProductListDTO>> resultEntity = null;
        HttpEntity<ProductListDTO> resquestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,resquestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 通过品牌id获取当前商品列表 格式/products/{currentPage}/{pageSize}/brandId/{brandId} GET
     * @param offset 当前页
     * @param limit 每页数据
     * @param brandId 品牌ID
     * @return 返回list
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}/brandId/{brandId}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity listByBrandId(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("brandId")Integer brandId) {
        Response response;
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        Map<String, Object> searchParams = Maps.newConcurrentMap();
        searchParams.put("EQ_brand.id", brandId);
        productListDTO.setMap(searchParams);
        ResponseEntity<Response<ProductListDTO>> resultEntity = null;
        HttpEntity<ProductListDTO> resquestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,resquestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    /**
     * 通过类别名称获取当前商品列表 格式/products/{currentPage}/{pageSize}/type/{name} GET
     * @param offset 当前页
     * @param limit 每页数据
     * @param name 类别名称
     * @return 返回list
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}/type/{name}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity listByTypeName(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("name")String name) {
        Response response;
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        Map<String, Object> searchParams = Maps.newConcurrentMap();
        searchParams.put("LIKE_type.name", name);
        productListDTO.setMap(searchParams);
        ResponseEntity<Response<ProductListDTO>> resultEntity = null;
        HttpEntity<ProductListDTO> resquestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,resquestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    /**
     * 通过类别Id获取当前商品列表 格式/products/{currentPage}/{pageSize}/typeId/{typeId} GET
     * @param offset 当前页
     * @param limit 每页数据
     * @param typeId 类别ID
     * @return 返回list
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}/typeId/{typeId}", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity listByTypeId(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("typeId")String typeId) {
        Response response;
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        Map<String, Object> searchParams = Maps.newConcurrentMap();
        searchParams.put("EQ_type.id", typeId);
        productListDTO.setMap(searchParams);
        ResponseEntity<Response<ProductListDTO>> resultEntity = null;
        HttpEntity<ProductListDTO> resquestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,resquestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
    * 通过品名字（关键字）获取当前商品列表 格式/products/{currentPage}/{pageSize}/name/{name} GET
    * @param offset 当前页
    * @param limit 每页数据
    * @param name 产品名字（关键字）
    * @return 返回list
    */
   @RequestMapping(value = "/offset/{offset}/limit/{limit}/name/{name}", method = RequestMethod.GET,produces = {"application/json"})
   public ResponseEntity listByName(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("name")String name) {
       Response response;
       ProductListDTO productListDTO = new ProductListDTO();
       productListDTO.setLimit(limit);
       productListDTO.setOffset(offset);
       Map<String, Object> searchParams = Maps.newConcurrentMap();
       searchParams.put("LIKE_name", name);
       productListDTO.setMap(searchParams);
       ResponseEntity<Response<ProductListDTO>> resultEntity = null;
       HttpEntity<ProductListDTO> resquestDTO = new HttpEntity<ProductListDTO>(productListDTO);
       try {
           resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                   HttpMethod.POST,resquestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
       }catch (Exception e){
           log.error(e.getMessage());
           response = new Response();
           response.setSuccess(Response.Status.FAILURE);
           response.setMessage("系统错误");
           return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }

       //获取返回结果
       response = resultEntity.getBody();
       //返回结果有错
       if (response == null || !response.isSuccess()){
           return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return new ResponseEntity(response, HttpStatus.OK);
   }
    
    /**
     * search
     * @param request http请求
     * @return 返回结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity get(final HttpServletRequest request) {
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
        ProductListDTO productListDTO = new ProductListDTO();
        productListDTO.setLimit(limit);
        productListDTO.setOffset(offset);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        productListDTO.setMap(searchParams);
        ResponseEntity<Response<ProductListDTO>> resultEntity = null;
        HttpEntity<ProductListDTO> resquestDTO = new HttpEntity<ProductListDTO>(productListDTO);
        try {
            resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM),
                    HttpMethod.POST,resquestDTO,new ParameterizedTypeReference<Response<ProductListDTO>>() {});
        }catch (Exception e){
            log.error(e.getMessage());
            response = new Response();
            response.setSuccess(Response.Status.FAILURE);
            response.setMessage("系统错误");
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //获取返回结果
        response = resultEntity.getBody();
        //返回结果有错
        if (response == null || !response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
