package com.ishare.mall.center.controller;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberLoginResultDTO;
import com.ishare.mall.core.form.center.login.LoginForm;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
        return "center/index/login";
    }

    @RequestMapping(value = "/login")
    public String login(LoginForm loginForm) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setAccount("admin");
        memberDTO.setPassword("123");
        ResponseEntity<MemberLoginResultDTO> resultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        resultDTO = restTemplate.postForEntity("localhost:8888/bizapp/member/login", memberDTO, MemberLoginResultDTO.class);
        MemberLoginResultDTO memberLoginResultDTO = resultDTO.getBody();
        log.debug(memberLoginResultDTO.toString());
        return "center/index/login";
    }
}
