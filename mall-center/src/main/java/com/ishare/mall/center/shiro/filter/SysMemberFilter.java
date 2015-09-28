package com.ishare.mall.center.shiro.filter;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by YinLin on 2015/9/8.
 * Description : 给Request注入当前用户
 * Version 1.0
 */
public class SysMemberFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(SysMemberFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(CommonConstant.Common.CURRENT_MEMBER, (MemberDTO)((HttpServletRequest)request).getSession().getAttribute(CommonConstant.Common.CURRENT_MEMBER));
        return true;
    }

}
