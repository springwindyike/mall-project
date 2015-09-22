package com.ishare.mall.manage.controller;

import com.ishare.mall.manage.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping
public class IndexController extends BaseController {
    @RequestMapping("/index")
    public String test() {
       return "success";
    }
}
