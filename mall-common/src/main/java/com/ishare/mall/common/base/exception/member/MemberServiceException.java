package com.ishare.mall.common.base.exception.member;

import com.ishare.mall.common.base.exception.BaseException;

/**
 * Created by wanghao on 2015/9/22.
 */
public class MemberServiceException extends BaseException{
    public MemberServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public MemberServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public MemberServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public MemberServiceException(String message) {
        super(message);
    }

    public MemberServiceException(Throwable th) {
        super(th);
    }

    public MemberServiceException(String mgs, Throwable th) {
        super(mgs, th);
    }
}
