package com.ishare.mall.common.base.exception;

/**
 * Created by YinLin on 2015/9/9.
 * Description :
 * Version 1.0
 */
public class BaseException extends Exception {
    protected String errorCode;
    protected String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
