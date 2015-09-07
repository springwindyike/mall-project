package com.ishare.mall.center.controller;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.form.login.LoginForm;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberLoginResultDTO;
import com.ishare.mall.common.base.dto.member.MemberPermissionDTO;
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

import javax.servlet.http.HttpSession;

/**
 * Created by YinLin on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
public class IndexController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    public static Logger getLog() {
        return log;
    }

    @RequestMapping("/res")
    @ResponseBody
    public Object result(Model m) {
    	System.out.println("________________");
        return m;
    }

    @RequestMapping(value = CenterURIConstant.Index.INDEX)
    public String index(){
        return CenterViewConstant.Index.LOGIN;
    }

    @RequestMapping(value = CenterURIConstant.Index.LOGIN, method = RequestMethod.GET)
    public String login() {
        return CenterViewConstant.Index.LOGIN;
    }
    
    @RequestMapping(value = CenterURIConstant.Index.LOGIN, method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute("loginAttribute") LoginForm loginForm) {
    	System.out.println(loginForm.getVerifyCode());
	    	 if (!(loginForm.getVerifyCode().equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
	    		 	System.out.println("验证码不正确");
	    		 	return CenterViewConstant.Index.LOGIN;
	    	}else {
	    		System.out.println("验证码正确");
	    		MemberDTO memberDTO = new MemberDTO();
	    		memberDTO.setAccount(loginForm.getAccount());
	    		memberDTO.setPassword(loginForm.getPassword());
	    		log.debug(memberDTO.toString());
	    		ResponseEntity<MemberLoginResultDTO> resultDTO = null;
	    		RestTemplate restTemplate = new RestTemplate();
	    		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_LOGIN), memberDTO, MemberLoginResultDTO.class);
	    		MemberLoginResultDTO memberLoginResultDTO = resultDTO.getBody();
	    		log.debug(memberLoginResultDTO.toString());
	    		return CenterViewConstant.Index.INDEX;
				}
    }
    @RequestMapping(value ="test")
    public String test() {
        log.debug("here");
        ResponseEntity<MemberPermissionDTO> resultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        log.debug(this.buildBizAppURI(APPURIConstant.Permission.REQUEST_MAPPING,"") + "/13885268940");
        resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.Permission.REQUEST_MAPPING,"") + "/13885268940", MemberPermissionDTO.class);
        MemberPermissionDTO memberPermissionDTO = resultDTO.getBody();
        log.debug(memberPermissionDTO.toString());
        return CenterViewConstant.Index.LOGIN;
    }
}
