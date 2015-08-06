package com.ishare.mall.restful;

import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.old.model.Customer;
import com.ishare.mall.utils.Servlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product get(@PathVariable("id") Integer id) {
        Product product = new Product();
        log.debug("debug {id} " + String.valueOf(id));
        product.setId(id);
        return product;
    }

    /**
     * 通过当前页和每页数量获取商品列表 格式 /products/{currentPage}/{pageSize} GET
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/{currentPage}/{pageSize}", method = RequestMethod.GET)
    public Page<Product> get(Integer currentPage, Integer pageSize) {
        return null;
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

        int currentPage = Integer.valueOf(request.getParameter("page"));

        int pageSize = Integer.valueOf(request.getParameter("rows"));

        PageRequest pageRequest = new PageRequest(currentPage - 1, pageSize, Sort.Direction.DESC, "id");

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        log.debug("searchParams: {}", searchParams);
        Page<Product> result = productService.search(searchParams, pageRequest);

        log.debug("result {}", result.getContent());

        return result;
    }



}
