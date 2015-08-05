package com.ishare.mall.controller;

import com.ishare.mall.core.model.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YinLin on 2015/7/30.
 * Description: Test
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping(value="get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
       return "success";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Product list() {
        Product product = new Product();
        product.setCode("010101");
        return product;
    }
}
