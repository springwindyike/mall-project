package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class ProductServiceException extends BaseServiceException {
    
    public ProductServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ProductServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ProductServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ProductServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductServiceException(String message) {
        super(message);
    }

    public ProductServiceException(Throwable cause) {
        super(cause);
    }
}
