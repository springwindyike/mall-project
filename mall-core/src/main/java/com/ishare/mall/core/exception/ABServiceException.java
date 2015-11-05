package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseException;

public class ABServiceException extends BaseException {

	public ABServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ABServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ABServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ABServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ABServiceException(Throwable cause) {
        super(cause);
    }
	
	
	
	
	

}
