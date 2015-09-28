package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class ProductTypeServiceException extends BaseServiceException {
    
    public ProductTypeServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ProductTypeServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ProductTypeServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ProductTypeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeServiceException(String message) {
        super(message);
    }

    public ProductTypeServiceException(Throwable cause) {
        super(cause);
    }
}
