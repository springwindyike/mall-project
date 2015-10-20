package com.ishare.mall.manage.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.manage.service.manageuser.ManageUserService;

/**
 * Created by YinLin on 2015/9/8.
 * Description : 给Request注入当前用户
 * Version 1.0
 */
public class SysManageUserFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(SysManageUserFilter.class);
    @Autowired
    private ManageUserService manageUserService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.debug("来咯·");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        log.debug("username : " + username);
        if (username == null) return true;
        CurrentManageUserDTO currentManageUserDTO = manageUserService.getCurrentManageUser(username);
        log.debug("username : " + username);
        request.setAttribute(CommonConstant.Common.CURRENT_MANAGEUSER, currentManageUserDTO);
        return true;
    }

}
