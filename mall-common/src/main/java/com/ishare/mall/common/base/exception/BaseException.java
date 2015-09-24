package com.ishare.mall.common.base.exception;

import com.ishare.mall.common.base.constant.BaseConstant;
import com.ishare.mall.common.base.constant.IShareErrorConstant;
import com.ishare.mall.common.base.utils.PropertyUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;

/**
 * Created by YinLin on 2015/9/9.
 * Description :
 * Version 1.0
 */
public class BaseException extends RuntimeException {

    private static final Log log = LogFactory.getLog(BaseException.class);

    private String systemName;
    private String errorCode;
    private String message;
    private HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

    public BaseException(String systemName, String errorPropertyKey, String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.errorCode = getErrorCode(errorPropertyKey);
        this.status = status;
    }

    public BaseException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = getErrorCode(errorPropertyKey);
    }

    public BaseException(String systemName, String errorPropertyKey, HttpStatus status, Throwable cause) {
        super("BaseException", cause);
        this.errorCode = getErrorCode(errorPropertyKey);
        this.systemName = systemName;
        this.status = status;
    }

    public BaseException(String systemName, String errorPropertyKey, Throwable cause) {
        super("BaseException", cause);
        this.errorCode = getErrorCode(errorPropertyKey);
        this.systemName = systemName;
    }

    public BaseException(String systemName, HttpStatus status, String errorPropertyKey) {
        this.errorCode = getErrorCode(errorPropertyKey);
        this.systemName = systemName;
        this.status = status;
    }

    public BaseException(String systemName, String errorPropertyKey) {
        this.errorCode = getErrorCode(errorPropertyKey);
        this.systemName = systemName;
    }

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(Throwable th) {
        super(th);
    }

    public BaseException(String mgs, Throwable th) {
        super(mgs, th);
    }

    public String getErrorCode(String propKey) {
        String errorCode = IShareErrorConstant.ERROR_NULL;
        try {
            return PropertyUtil.getPropertyValue(BaseConstant.Prop.ERROR_CODE, propKey);
        } catch (Exception ex) {
            log.error("[Ishare] error occurred: PropertyUtil get " + BaseConstant.Prop.ERROR_CODE + " " + propKey + " failure!", ex);
        }
        return errorCode;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public static Log getLog() {
        return log;
    }
}
