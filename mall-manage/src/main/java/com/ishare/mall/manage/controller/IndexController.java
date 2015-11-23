package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.manage.annoation.CurrentManageUser;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.exception.IncorrectCaptchaException;
import com.ishare.mall.manage.service.channel.ChannelService;
import com.ishare.mall.manage.service.manageuser.ManageUserService;
import com.ishare.mall.manage.service.member.MemberService;
import com.ishare.mall.manage.service.order.impl.OrderService;
import com.ishare.mall.manage.service.product.ProdcutService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ManageUserService manageUserService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProdcutService prodcutService;
	@Autowired
	private ChannelService channelService;
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	public static Logger getLog() {
		return log;
	}

	@RequestMapping("/index")
	public String test() {
		return "success";
	}

	/**
	 * 访问登录页面
	 *
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Index.LOGIN, method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.getPrincipal() != null && subject.isAuthenticated()) {
			return "redirect:/main.dhtml";
		}
		SecurityUtils.getSubject().logout();
		return ManageViewConstant.Index.LOGIN;
	}

	/**
	 * 登录流程
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Index.LOGIN, method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		Object exceptionClass = request.getAttribute("shiroLoginFailure");
		String error = null;
		if (exceptionClass instanceof UnknownAccountException) {
			error = "用户名不存在";
		} else if (exceptionClass instanceof IncorrectCredentialsException) {
			error = "密码错误";
		} else if (exceptionClass instanceof IncorrectCaptchaException) {
			error = "验证码错误";
		} else {
			error = "其他错误 ： ";
		}
		log.debug("error" + error);
		model.addAttribute("error", error);
		return ManageViewConstant.Index.LOGIN;
	}

	/**
	 * 访问主页
	 *
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Main.INDEX, method = RequestMethod.GET)
	public String main(@CurrentManageUser CurrentManageUserDTO currentManageUserDTO) {
		return ManageViewConstant.Main.MAIN;
	}

	/**
	 *访问欢迎主界面
	 * @param currentManageUserDTO
	 * @return
	 */
//	@RequestMapping(value = ManageURIConstant.Welcome.INDEX,method=RequestMethod.GET)
//	public String welcome(@CurrentManageUser CurrentManageUserDTO currentManageUserDTO, Model model) {
//		model.addAttribute("memberNum", memberService.findAll().size());
//		return ManageViewConstant.Welcome.WELCOME;
//	}

	@RequestMapping(value = ManageURIConstant.Welcome.INDEX, method = RequestMethod.GET)
	public String welcome(@CurrentManageUser CurrentManageUserDTO currentManageUserDTO, Model model) {
		System.out.println( memberService.findCount()+"*************");
		model.addAttribute("memberNum", memberService.findCount());
		model.addAttribute("productThisWeekCount",prodcutService.findThisWeekCount());
		model.addAttribute("orderMum",orderService.findcount());
		model.addAttribute("goodsNum",prodcutService.findCount());
		model.addAttribute("memberThisWeekNum",memberService.findThisWeekCount());
		model.addAttribute("channelNum",channelService.findCount());
		model.addAttribute("channelThisWeekNum",channelService.findThisWeekCount());
		return ManageViewConstant.Welcome.WELCOME;
	}


}
