package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class OrderServiceException extends BaseServiceException {
    
    public OrderServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public OrderServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public OrderServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(Throwable cause) {
        super(cause);
    }
}
