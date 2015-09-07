package com.ishare.mall.center.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.form.login.LoginForm;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.MemberLoginDTO;
import com.ishare.mall.common.base.dto.member.MemberLoginResultDTO;
import com.ishare.mall.common.base.dto.member.MemberPermissionDTO;

/**
 * Created by ZhangZhaoxin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@Controller
public class MainController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    public static Logger getLog() {
        return log;
    }

    @RequestMapping(value = CenterURIConstant.Main.INDEX, method = RequestMethod.GET)
    public String main() {
        return CenterViewConstant.Main.MAIN;
    }
    
}
