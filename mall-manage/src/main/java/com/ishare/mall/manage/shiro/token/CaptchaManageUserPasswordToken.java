package com.ishare.mall.manage.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by YinLin on 2015/9/9.
 * Description :
 * Version 1.0
 */
public class CaptchaManageUserPasswordToken extends UsernamePasswordToken {

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaManageUserPasswordToken(String username, char[] password,
                               boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
