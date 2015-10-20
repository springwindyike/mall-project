package com.ishare.mall.common.base.exception.manageuser;

import com.ishare.mall.common.base.exception.BaseException;

/**
 * Created by wanghao on 2015/9/22.
 */
public class ManageUserServiceException extends BaseException{
    public ManageUserServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ManageUserServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ManageUserServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ManageUserServiceException(String message) {
        super(message);
    }

    public ManageUserServiceException(Throwable th) {
        super(th);
    }

    public ManageUserServiceException(String mgs, Throwable th) {
        super(mgs, th);
    }
}
