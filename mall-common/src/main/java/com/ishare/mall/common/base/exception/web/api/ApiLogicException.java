package com.ishare.mall.common.base.exception.web.api;

import com.ishare.mall.common.base.exception.WebException;

/**
 * Created by YinLin on 2015/9/23.
 * Description : ApiÂß¼­Òì³£
 * Version 1.0
 */
public class ApiLogicException extends WebException {

    public ApiLogicException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ApiLogicException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ApiLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiLogicException(String message) {
        super(message);
    }

    public ApiLogicException(Throwable cause) {
        super(cause);
    }
}
