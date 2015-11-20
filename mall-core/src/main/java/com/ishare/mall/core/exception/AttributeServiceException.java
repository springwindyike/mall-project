package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class AttributeServiceException extends BaseServiceException {
    
    public AttributeServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public AttributeServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public AttributeServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public AttributeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttributeServiceException(String message) {
        super(message);
    }

    public AttributeServiceException(Throwable cause) {
        super(cause);
    }
}
