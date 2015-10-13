package com.ishare.mall.manage.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by YinLin on 2015/9/9.
 * Description : 自定义验证码错误异常
 * Version 1.0
 */
public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
