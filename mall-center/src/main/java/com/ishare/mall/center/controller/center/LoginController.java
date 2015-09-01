package com.ishare.mall.center.controller.center;

import com.ishare.mall.center.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    @RequestMapping("/login")
    public String login() {
      return "";
    }
}
