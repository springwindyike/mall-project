package com.ishare.mall.center.shiro.filter;

import com.ishare.mall.center.service.member.MemberService;
import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by YinLin on 2015/9/8.
 * Description : 给Request注入当前用户
 * Version 1.0
 */
public class SysMemberFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(SysMemberFilter.class);
    @Autowired
    private MemberService memberService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.debug("来咯·");
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        log.debug("account : " + account);
        if (account == null) return true;
        CurrentMemberDTO currentMemberDTO = memberService.getCurrentMember(account);
        log.debug("account : " + account);
        request.setAttribute(CommonConstant.Common.CURRENT_MEMBER, currentMemberDTO);
        return true;
    }

}
