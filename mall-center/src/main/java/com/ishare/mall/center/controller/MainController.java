package com.ishare.mall.center.controller;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	/**
	 * 访问主页
	 * @return
	 */
    @RequestMapping(value = CenterURIConstant.Main.INDEX, method = RequestMethod.GET)
    public String main(@CurrentMember CurrentMemberDTO memberDTO) {
        return CenterViewConstant.Main.MAIN;
    }

}
