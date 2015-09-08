package com.ishare.mall.center.shiro.filter;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by YinLin on 2015/9/8.
 * Description :
 * Version 1.0
 */
public class SysMemberFilter extends PathMatchingFilter {
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(CommonConstant.Common.CURRENT_MEMBER, new MemberDTO());
        return true;
    }
}
