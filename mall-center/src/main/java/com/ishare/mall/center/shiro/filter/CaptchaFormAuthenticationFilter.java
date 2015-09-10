package com.ishare.mall.center.shiro.filter;

import com.ishare.mall.center.shiro.exception.IncorrectCaptchaException;
import com.ishare.mall.center.shiro.token.CaptchaMemberPasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
        CaptchaMemberPasswordToken token = createToken(request, response);
        try {
            this.doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    private void doCaptchaValidate(HttpServletRequest request, CaptchaMemberPasswordToken token) {
        String captcha = (String) request.getSession().getAttribute("code");
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }


    @Override
    protected CaptchaMemberPasswordToken createToken(ServletRequest request, ServletResponse response) {
        //return super.createToken(request, response);
        String account = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaMemberPasswordToken(account, password.toCharArray(), rememberMe, host, captcha);
    }

    /**
     * 获取验证码
     * @param request
     * @return
     */
    private String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, "code");
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
}
