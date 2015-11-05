package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by liaochenglei on 2015/11/5.
 * Description :
 * Version 1.0
 */
public class BrandProductTypeServiceException extends BaseServiceException {
    
    public BrandProductTypeServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public BrandProductTypeServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public BrandProductTypeServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public BrandProductTypeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrandProductTypeServiceException(String message) {
        super(message);
    }

    public BrandProductTypeServiceException(Throwable cause) {
        super(cause);
    }
}
