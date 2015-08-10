package com.ishare.mall.restful;

import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.common.base.dto.product.ProductListDTO;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import com.ishare.mall.utils.Servlets;
import com.ishare.mall.utils.page.PageUtils;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by YinLin on 2015/7/30.
 * Description:产品接口相关
 * Version 1.0
 */
@RestController
@RequestMapping("/products")
public class ProductResource {

    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    private ProductService productService;
    /**
     * 通过商品ID获取单个商品信息  格式 /products/{id} GET
     * @param id 商品ID
     * @return ProductDTO 返回的数据对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDTO get(@NotEmpty @PathVariable("id") Integer id) {
        //用findOne立即加载实体对象
        Product product = productService.findOne(id);
        if (product != null) {
            //转换为接口输出数据对象DTO 映射规则需要自己添加
            return (ProductDTO) MapperUtils.map(product, ProductDTO.class);
        }
        return null;
    }

    /**
     * 通过当前页和每页数量获取商品列表 格式 /products/{currentPage}/{pageSize} GET
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/{currentPage}/{pageSize}", method = RequestMethod.GET)
    public Page<ProductListDTO> get(@NotEmpty @PathVariable("currentPage")Integer currentPage, @NotEmpty @PathVariable("pageSize")Integer pageSize) {
        PageRequest pageRequest = new PageRequest(currentPage - 1, pageSize, Sort.Direction.DESC, "id");
        Page<Product> result = productService.search(null, pageRequest);
        Page<ProductListDTO> resultList = PageUtils.mapper(result, pageRequest, ProductListDTO.class);
        return resultList;
    }

    /**
     * 通过品牌id获取当前商品列表 格式/products/{currentPage}/{pageSize}/brand/{brandId} GET
     * @param currentPage 当前页
     * @param pageSize 每页数据
     * @param name 品牌名字
     * @return
     */
    @RequestMapping(value = "/{currentPage}/{pageSize}/brand/{name}", method = RequestMethod.GET)
    public Page<Product> get(Integer currentPage, Integer pageSize, String name) {
        return  null;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<Product> get(final HttpServletRequest request) {
        int currentPage = 1;
        int pageSize = 15;
        if (StringUtils.isNotEmpty(request.getParameter("page"))) {
            currentPage = Integer.valueOf(request.getParameter("page"));
            if (currentPage <= 0) {
                currentPage = 1;
            }
        }
        if (StringUtils.isNotEmpty(request.getParameter("pageSize"))) {
            pageSize = Integer.valueOf(request.getParameter("pageSize"));
            if (pageSize <= 0) {
                pageSize = 15;
            }
        }

        PageRequest pageRequest = new PageRequest(currentPage - 1, pageSize, Sort.Direction.DESC, "id");

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        log.debug("searchParams: {}", searchParams);

        Page<Product> result = productService.search(searchParams, pageRequest);

        log.debug("result {}", result.getContent());

        return result;
    }

}
