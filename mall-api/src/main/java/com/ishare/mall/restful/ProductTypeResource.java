package com.ishare.mall.restful;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductTypeService;
import com.ishare.mall.utils.Servlets;
import com.ishare.mall.utils.page.PageUtils;

/**
 * Created by YinLin on 2015/8/10.
 * Description:商品类型接口
 * Version 1.0
 */
@RestController
@RequestMapping("/producttypes")
public class ProductTypeResource {
	
	private static final Logger log = LoggerFactory.getLogger(ProductTypeResource.class);
	@Autowired
	private ProductTypeService productTypeService;
    /**
     * 类型详细信息
     */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductType detail(@NotEmpty @PathVariable("id") Integer id) {
		System.out.println(id);
		//用findOne立即加载实体对象
		ProductType productType = productTypeService.findOne(id);
		if (productType != null)
		log.debug(productType.toString());
		return productType;	 
	 }
   
    /**
     * 通过当前页和每页数量获取商品类型列表
	 * @param offset
	 * @param limit
	 * @return
     */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
	public Page<ProductType> list(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
		Page<ProductType> result = productTypeService.search(null, pageRequest);
		return PageUtils.mapper(result, pageRequest, ProductType.class);
	}
	
	/**
	 * 通过父id获取当前商品类型列表
	 * @param offset	当前页
	 * @param limit	每页数量
	 * @param pid 父id编号 
	 * @return
	 */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}/pid/{pid}", method = RequestMethod.GET)
	public Page<ProductType> List(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("pid")String pid){
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		searchParams.put("EQ_parent.id", pid);
		Page<ProductType> result = productTypeService.search(searchParams, pageRequest);
		return PageUtils.mapper(result, pageRequest, ProductType.class);
	}
	
	
	/**
	* search
	* @param request http请求
	* @return 返回结果
	*/
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Page<ProductType> get(final HttpServletRequest request) {
		PageRequest pageRequest = PageUtils.getPageRequest(request, Sort.Direction.DESC, "id");
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		log.debug("searchParams: {}", searchParams);
		
		Page<ProductType> result = productTypeService.search(searchParams, pageRequest);
		
		log.debug("result {}", result.getContent());
		
		return PageUtils.mapper(result, pageRequest, ProductType.class);
	}
	public static Logger getLog() {
		return log;
	}
}
