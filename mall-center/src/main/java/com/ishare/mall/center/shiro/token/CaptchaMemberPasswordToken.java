package com.ishare.mall.center.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by YinLin on 2015/9/9.
 * Description :
 * Version 1.0
 */
public class CaptchaMemberPasswordToken extends UsernamePasswordToken {

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaMemberPasswordToken(String account, char[] password,
                               boolean rememberMe, String host, String captcha) {
        super(account, password, rememberMe, host);
    }
}
