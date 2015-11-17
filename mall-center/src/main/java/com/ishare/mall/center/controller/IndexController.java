package com.ishare.mall.center.controller;


import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.form.register.RegisterForm;
import com.ishare.mall.center.service.product.ProductService;
import com.ishare.mall.center.shiro.exception.AccountDeletedException;
import com.ishare.mall.center.shiro.exception.ChannelClosedException;
import com.ishare.mall.center.shiro.exception.IncorrectCaptchaException;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.member.MemberRegisterDTO;
import com.ishare.mall.common.base.dto.member.MemberRegisterResultDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;



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

    @Autowired
    private ProductService productService;

    @RequestMapping("/res")
    @ResponseBody
    public Object result(Model m) {
    	System.out.println("________________");
        return m;
    }


    @RequestMapping(value = CenterURIConstant.Index.LOGIN, method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null && subject.isAuthenticated()) {
            return "redirect:/main.dhtml";
        }
        SecurityUtils.getSubject().logout();
        return CenterViewConstant.Index.LOGIN;
    }

    /**
     * 访问主页
     * @return
     */
    @RequestMapping(value = CenterURIConstant.Main.INDEX, method = RequestMethod.GET)
    public String main(@CurrentMember CurrentMemberDTO memberDTO) {
        return CenterViewConstant.Main.MAIN;
    }
    
    @RequestMapping(value = CenterURIConstant.Index.LOGIN, method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        Object exceptionClass = request.getAttribute("shiroLoginFailure");
        String error = null;
        if(exceptionClass instanceof UnknownAccountException) {
            error = "用户名不存在";
        } else if(exceptionClass instanceof IncorrectCredentialsException) {
            error = "密码错误";
        } else if(exceptionClass instanceof IncorrectCaptchaException) {
            error = "验证码错误";
        } else if(exceptionClass instanceof AccountDeletedException){
            error = "用户被禁用";
        } else if(exceptionClass instanceof ChannelClosedException){
            error = "用户渠道已关闭";
        } else {
            error = "其他错误";
        }
        log.debug("error" + error);
        model.addAttribute("error", error);
        return CenterViewConstant.Index.LOGIN;
    }
    @RequestMapping(value = CenterURIConstant.Index.LOGOUT, method = RequestMethod.GET)
    public String logout(Model model) {
        SecurityUtils.getSubject().logout();
        return CenterViewConstant.Index.LOGIN;
    }
    
    /**
     * 访问注册页面
     * @return
     */
    @RequestMapping(value = CenterURIConstant.Index.REGISTER, method = RequestMethod.GET)
    public String register() {
        return CenterViewConstant.Index.REGISTER;
    }
    /**
     * 注册提交
     * @return
     */
    @ResponseBody
    @RequestMapping(value = CenterURIConstant.Index.REGISTER, method = RequestMethod.POST)
    public MemberRegisterResultDTO registerMember(@ModelAttribute("registerAttribute") RegisterForm registerForm) {
    			log.debug(registerForm.toString());
    			MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
    			BeanUtils.copyProperties(registerForm,memberRegisterDTO);
    			ResponseEntity<MemberRegisterResultDTO> resultDTO = null;
    			RestTemplate restTemplate = new RestTemplate();
    			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING,APPURIConstant.Member.REQUEST_MAPPING_REGISTER_MEMBER),memberRegisterDTO,MemberRegisterResultDTO.class);
    			MemberRegisterResultDTO memberRegisterResultDTO = resultDTO.getBody();   			
       return memberRegisterResultDTO;
    }
    
    /**
     * 注册账号验证
     * @return
     */
    @ResponseBody
    @RequestMapping(value = CenterURIConstant.Index.ACCOUNT_VALID, method = RequestMethod.POST)
    public ValidformRespDTO accountValid(@RequestParam("name") String name, @RequestParam("param") String param) {
    	MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
    	memberRegisterDTO.setAccount(param);
			ResponseEntity<ValidformRespDTO> resultDTO = null;
			RestTemplate restTemplate = new RestTemplate();
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_VALID_BY_ACCOUNT), memberRegisterDTO, ValidformRespDTO.class);
			ValidformRespDTO validformRespDTO = resultDTO.getBody();
			return validformRespDTO;
    }
    
    /**
     * Channel验证（公司名称）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = CenterURIConstant.Index.CHANNEL_VALID, method = RequestMethod.POST)
    public ValidformRespDTO channelValid(@RequestParam("name") String name, @RequestParam("param") String param) {
    	ChannelTokenResultDTO channelRegisterDTO = new ChannelTokenResultDTO();
    	channelRegisterDTO.setName(param);
			ResponseEntity<ValidformRespDTO> resultDTO = null;
			RestTemplate restTemplate = new RestTemplate();
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_FIND_VALID_BY_NAME), channelRegisterDTO, ValidformRespDTO.class);
			ValidformRespDTO validformRespDTO = resultDTO.getBody();
			return validformRespDTO;
    }


    @RequestMapping(value = "welcome",method = RequestMethod.GET,produces = { "application/json"} )
    public String welcome(Model model) {
        model.addAttribute("product", productService.findOne(66));
        return "main/welcome";
    }
}
