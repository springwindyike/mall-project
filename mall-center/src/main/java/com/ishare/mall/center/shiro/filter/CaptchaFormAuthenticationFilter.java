package com.ishare.mall.center.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by YinLin on 2015/9/9.
 * Description : 带验证码的认证
 * Version 1.0
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);

    public CaptchaFormAuthenticationFilter() {}

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //CaptchaMemberPasswordToken token = createToken(request, response);
        return true;
    }


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        return super.createToken(request, response);
    }
}
