package com.ishare.mall.center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YinLin on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller("/index")
public class IndexController {
    @RequestMapping("/res")
    @ResponseBody
    public Object result(Model m) {
        return m;
    }
}
