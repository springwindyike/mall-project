package com.ishare.mall.api.restful;

import com.google.common.collect.Maps;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductTypeService;
import com.ishare.mall.api.utils.Servlets;
import com.ishare.mall.api.utils.page.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    /**
     * 类型详细信息
     */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductTypeDTO detail(@NotEmpty @PathVariable("id") Integer id) {
		//用findOne立即加载实体对象
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setId(id);
		ResponseEntity<ProductTypeDTO> resultEntiy = null;
		RestTemplate restTemplate = new RestTemplate();
		resultEntiy = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING,APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_ID),productTypeDTO,ProductTypeDTO.class);
		//ProductType productType = productTypeService.findOne(id);
		ProductTypeDTO returnTO = resultEntiy.getBody();
		//return productType;
		return returnTO;
	 }
   
    /**
     * 通过当前页和每页数量获取商品类型列表
	 * @param offset
	 * @param limit
	 * @return
     */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
	public PageDTO list(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setLimit(limit);
		productTypeDTO.setOffset(offset);
		ResponseEntity<ProductTypeDTO> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate();
		responseEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING,APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),productTypeDTO,ProductTypeDTO.class);
		ProductTypeDTO returnTO = responseEntity.getBody();
		//PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
		//Page<ProductType> result = productTypeService.search(null, pageRequest);
		//return PageUtils.mapper(result, pageRequest, ProductType.class);
		return returnTO.getPageDTO();
	}
	
	/**
	 * 通过父id获取当前商品类型列表
	 * @param offset	当前页
	 * @param limit	每页数量
	 * @param pid 父id编号 
	 * @return
	 */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}/pid/{pid}", method = RequestMethod.GET)
	public PageDTO List(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("pid")String pid){
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setLimit(limit);
		productTypeDTO.setOffset(offset);
		//PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		searchParams.put("EQ_parent.id", pid);
		productTypeDTO.setMap(searchParams);
		ResponseEntity<ProductTypeDTO> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate();
		responseEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING,APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),productTypeDTO,ProductTypeDTO.class);
		ProductTypeDTO returnTO = responseEntity.getBody();
		//Page<ProductType> result = productTypeService.search(searchParams, pageRequest);
		//return PageUtils.mapper(result, pageRequest, ProductType.class);
		return returnTO.getPageDTO();
	}
	
	
	/**
	* search
	* @param request http请求
	* @return 返回结果
	*/
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public PageDTO get(final HttpServletRequest request) {
		//PageRequest pageRequest = PageUtils.getPageRequest(request, Sort.Direction.DESC, "id");
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
		ResponseEntity<ProductTypeDTO> responseEntity = null;
		//Page<ProductType> result = productTypeService.search(searchParams, pageRequest);
		RestTemplate restTemplate = new RestTemplate();
		responseEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING,APPURIConstant.ProductType.REQUEST_MAPPING_FIND_BY_PARAM),productTypeDTO,ProductTypeDTO.class);
		ProductTypeDTO returnTO = responseEntity.getBody();
		log.debug("result {}", returnTO.getPageDTO().getContent());
		return returnTO.getPageDTO();
		//return PageUtils.mapper(result, pageRequest, ProductType.class);
	}
	public static Logger getLog() {
		return log;
	}
}
