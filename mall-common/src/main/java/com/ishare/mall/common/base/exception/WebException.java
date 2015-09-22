package com.ishare.mall.common.base.exception;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
@SuppressWarnings("serial")
public class WebException extends BaseException {

    public WebException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public WebException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

}
