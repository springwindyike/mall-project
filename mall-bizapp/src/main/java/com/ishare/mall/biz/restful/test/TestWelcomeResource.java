package com.ishare.mall.biz.restful.test;

import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/11/12.
 */
@RestController
@RequestMapping("/welcome")
public class TestWelcomeResource {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
                    produces = { "application/json"})
    public Response<ProductDetailDTO> findOne(@NotEmpty @PathVariable("id") Integer id) {
        Response response = new Response();
        Product product = productService.findOne(id);
        ProductDetailDTO productDetailDTO = (ProductDetailDTO) MapperUtils.map(product, ProductDetailDTO.class);
        response.setData(productDetailDTO);
        response.setCode(200);
        return response;
    }
}
