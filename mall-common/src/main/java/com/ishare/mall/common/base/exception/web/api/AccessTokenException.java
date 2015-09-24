package com.ishare.mall.common.base.exception.web.api;

import com.ishare.mall.common.base.exception.WebException;

/**
 * Created by YinLin on 2015/9/24.
 * Description : ’Î∂‘Œ¥¥´token“Ï≥£
 * Version 1.0
 */
public class AccessTokenException extends WebException {

    public AccessTokenException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public AccessTokenException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public AccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(Throwable cause) {
        super(cause);
    }
}
