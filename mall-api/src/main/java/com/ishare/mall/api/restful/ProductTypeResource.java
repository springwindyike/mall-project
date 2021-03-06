package com.ishare.mall.api.restful;

import com.google.common.collect.Maps;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.utils.Servlets;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.general.Response;
import org.apache.commons.lang.StringUtils;
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
 * Description:商品类型接口
 * Version 1.0
 */
@RestController
@RequestMapping("/producttypes")
public class ProductTypeResource extends BaseResource {

	private static final Logger log = LoggerFactory.getLogger(ProductTypeResource.class);
	@Autowired
	private RestTemplate restTemplate;
    /**
     * 根据Id获取该类别详细信息
     */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json"})
	public ResponseEntity detail(@NotEmpty @PathVariable("id") Integer id) {
		//用findOne立即加载实体对象
		Response response;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setId(id);
		ResponseEntity<Response<ProductTypeDTO>> resultEntity = null;
		HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
		try {
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_QUERY_BY_ID),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ProductTypeDTO>>() {
			});
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
     * 通过当前页和每页数量获取商品类型列表
	 * @param offset
	 * @param limit
	 * @return
     */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET,produces = {"application/json"})
	public ResponseEntity list(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
		Response response;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setLimit(limit);
		productTypeDTO.setOffset(offset);
		ResponseEntity<Response<PageDTO>> resultEntity = null;
		HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
		try {
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO>>() {
					});
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
	 * 通过父id获取当前商品类型列表
	 * @param offset	当前页
	 * @param limit	每页数量
	 * @param pid 父id编号
	 * @return
	 */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}/pid/{pid}", method = RequestMethod.GET,produces = {"application/json"})
	public ResponseEntity List(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("pid")String pid){
		Response response;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setLimit(limit);
		productTypeDTO.setOffset(offset);
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		searchParams.put("EQ_parent.id", pid);
		productTypeDTO.setMap(searchParams);
		ResponseEntity<Response<PageDTO>> resultEntity = null;
		HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
		try {
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO>>() {
					});
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
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setLimit(limit);
		productTypeDTO.setOffset(offset);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		productTypeDTO.setMap(searchParams);
		log.debug("searchParams: {}", searchParams);
		ResponseEntity<Response<PageDTO>> resultEntity = null;
		HttpEntity<ProductTypeDTO> requestDTO = new HttpEntity<ProductTypeDTO>(productTypeDTO);
		try {
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO>>() {
					});
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
	public static Logger getLog() {
		return log;
	}
}
