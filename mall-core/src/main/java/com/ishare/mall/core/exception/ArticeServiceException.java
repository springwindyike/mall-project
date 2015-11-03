package com.ishare.mall.core.exception;



import com.ishare.mall.common.base.exception.BaseException;
/**
 * 
 * @author zhangzhaoxin
 *
 */
public class ArticeServiceException extends BaseException {
    
    /**
	 * 系统日志
	 */
	private static final long serialVersionUID = 1L;

	public ArticeServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ArticeServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ArticeServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ArticeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticeServiceException(String message) {
        super(message);
    }

    public ArticeServiceException(Throwable cause) {
        super(cause);
    }
}
