package com.ishare.mall.core.exception;

import com.ishare.mall.common.base.exception.BaseException;
/**
 * 
 * @author zhangzhaoxin
 * 系统栏目
 */

public class BannerServiceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	 public BannerServiceException (String systemName, String errorPropertyKey, String message, Throwable cause) {
	        super(systemName, errorPropertyKey, message, cause);
	    }

	    public BannerServiceException (String systemName, String errorPropertyKey, Throwable cause) {
	        super(systemName, errorPropertyKey, cause);
	    }

	    public BannerServiceException (String systemName, String errorPropertyKey) {
	        super(systemName, errorPropertyKey);
	    }

	    public BannerServiceException (String message, Throwable cause) {
	        super(message, cause);
	    }

	    public BannerServiceException (String message) {
	        super(message);
	    }

	    public BannerServiceException (Throwable cause) {
	        super(cause);
	    }
	
	
	
	
}
