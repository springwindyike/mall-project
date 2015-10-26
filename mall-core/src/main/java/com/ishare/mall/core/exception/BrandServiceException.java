package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class BrandServiceException extends BaseServiceException {
    
    public BrandServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public BrandServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public BrandServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public BrandServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrandServiceException(String message) {
        super(message);
    }

    public BrandServiceException(Throwable cause) {
        super(cause);
    }
}
