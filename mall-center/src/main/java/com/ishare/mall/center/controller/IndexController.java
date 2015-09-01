package com.ishare.mall.center.controller;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.core.form.center.login.LoginForm;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YinLin on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ChannelService channelService;
    @RequestMapping("/res")
    @ResponseBody
    public Object result(Model m) {
        return m;
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "index/center";
    }

    @RequestMapping(value = "/login")
    public String login(LoginForm loginForm) {

        return "index/center";
    }
}
