package com.ishare.mall.common.base.exception.web.api;

import com.ishare.mall.common.base.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * Created by YinLin on 2015/9/23.
 * Description : ApiÂß¼­Òì³£
 * Version 1.0
 */
public class ApiLogicException extends WebException {


    public ApiLogicException(String systemName, String errorPropertyKey, String message, HttpStatus status, Throwable cause) {
        super(systemName, errorPropertyKey, message, status, cause);
    }

    public ApiLogicException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ApiLogicException(String systemName, String errorPropertyKey, HttpStatus status, Throwable cause) {
        super(systemName, errorPropertyKey, status, cause);
    }

    public ApiLogicException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ApiLogicException(String systemName, HttpStatus status, String errorPropertyKey) {
        super(systemName, status, errorPropertyKey);
    }

    public ApiLogicException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ApiLogicException(String message, HttpStatus status) {
        super(message, status);
    }

    public ApiLogicException(String message) {
        super(message);
    }

    public ApiLogicException(Throwable th) {
        super(th);
    }

    public ApiLogicException(String mgs, Throwable th) {
        super(mgs, th);
    }
}
