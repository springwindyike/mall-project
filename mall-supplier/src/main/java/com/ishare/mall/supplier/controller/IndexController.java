package com.ishare.mall.supplier.controller;

import com.ishare.mall.supplier.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/11/19.
 * Description :
 * Version 1.0
 */
@RestController
public class IndexController extends BaseController {
    @RequestMapping(value = "test")
    public String test() {
        return "success";
    }
}
