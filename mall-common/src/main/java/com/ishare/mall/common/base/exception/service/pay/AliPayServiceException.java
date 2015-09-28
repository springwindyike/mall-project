package com.ishare.mall.common.base.exception.service.pay;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/23.
 * Description :
 * Version 1.0
 */
public class AliPayServiceException extends BaseServiceException {

    public AliPayServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public AliPayServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public AliPayServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public AliPayServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AliPayServiceException(String message) {
        super(message);
    }

    public AliPayServiceException(Throwable cause) {
        super(cause);
    }
}
