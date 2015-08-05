package com.ishare.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YinLin on 2015/7/30.
 * Description:
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping(value="get",method = RequestMethod.GET)
    @ResponseBody
    public String get() {
       return "success";
    }
}
