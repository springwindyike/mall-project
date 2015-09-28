package com.ishare.mall.common.base.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
@SuppressWarnings("serial")
public class WebException extends BaseException {


    public WebException(String systemName, String errorPropertyKey, String message, HttpStatus status, Throwable cause) {
        super(systemName, errorPropertyKey, message, status, cause);
    }

    public WebException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public WebException(String systemName, String errorPropertyKey, HttpStatus status, Throwable cause) {
        super(systemName, errorPropertyKey, status, cause);
    }

    public WebException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public WebException(String systemName, HttpStatus status, String errorPropertyKey) {
        super(systemName, status, errorPropertyKey);
    }

    public WebException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public WebException(String message, HttpStatus status) {
        super(message, status);
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(Throwable th) {
        super(th);
    }

    public WebException(String mgs, Throwable th) {
        super(mgs, th);
    }
}
