package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.exception.IncorrectCaptchaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping
public class IndexController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	public static Logger getLog() {
    return log;
	}
	
	@RequestMapping("/index")
	public String test() {
	   return "success";
    }
    
	@RequestMapping(value = ManageURIConstant.Index.LOGIN, method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.getPrincipal() != null && subject.isAuthenticated()) {
		    return "redirect:/main.dhtml";
	    }
		SecurityUtils.getSubject().logout();
		return ManageViewConstant.Index.LOGIN;
	}
	
	@RequestMapping(value = ManageURIConstant.Index.LOGIN, method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		Object exceptionClass = request.getAttribute("shiroLoginFailure");
		String error = null;
		if(exceptionClass instanceof UnknownAccountException) {
		    error = "用户名不存在";
		} else if(exceptionClass instanceof IncorrectCredentialsException) {
		    error = "密码错误";
		} else if(exceptionClass instanceof IncorrectCaptchaException) {
		    error = "验证码错误";
		} else {
		    error = "其他错误 ： ";
	    }
		log.debug("error" + error);
		model.addAttribute("error", error);
		return CenterViewConstant.Index.LOGIN;
	}
	
}
