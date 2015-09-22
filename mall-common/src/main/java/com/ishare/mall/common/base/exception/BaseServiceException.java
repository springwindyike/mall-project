package com.ishare.mall.common.base.exception;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class BaseServiceException extends BaseException {

    public BaseServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public BaseServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public BaseServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public BaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseServiceException(String message) {
        super(message);
    }

    public BaseServiceException(Throwable cause) {
        super(cause);
    }
}
