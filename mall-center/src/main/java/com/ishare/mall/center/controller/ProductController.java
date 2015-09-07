package com.ishare.mall.center.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.form.product.AddProductForm;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;


/**
 * Created by liaochenglei on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
public class ProductController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public static Logger getLog() {
        return log;
    }
    
    @RequestMapping(value = CenterURIConstant.Product.ADD_PRODUCT)
    public String addProduct(@ModelAttribute("productAttribute") AddProductForm apf) {
    	System.out.println("啦啦");
        return CenterViewConstant.Product.ADD_PRODUCT;
    }
}
