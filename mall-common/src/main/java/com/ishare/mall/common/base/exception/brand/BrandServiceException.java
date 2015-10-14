package com.ishare.mall.common.base.exception.brand;

import com.ishare.mall.common.base.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Created by wang hao on 2015/10/14.
 */
public class BrandServiceException extends BaseException{
    public BrandServiceException(String systemName, String errorPropertyKey, String message, HttpStatus status, Throwable cause) {
        super(systemName, errorPropertyKey, message, status, cause);
    }

    public BrandServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public BrandServiceException(String systemName, String errorPropertyKey, HttpStatus status, Throwable cause) {
        super(systemName, errorPropertyKey, status, cause);
    }

    public BrandServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public BrandServiceException(String systemName, HttpStatus status, String errorPropertyKey) {
        super(systemName, status, errorPropertyKey);
    }

    public BrandServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public BrandServiceException(String message, HttpStatus status) {
        super(message, status);
    }

    public BrandServiceException(String message) {
        super(message);
    }

    public BrandServiceException(Throwable th) {
        super(th);
    }

    public BrandServiceException(String mgs, Throwable th) {
        super(mgs, th);
    }
}
