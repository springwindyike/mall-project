package com.ishare.mall.common.base.exception.service.channel;

import com.ishare.mall.common.base.exception.BaseServiceException;

/**
 * Created by YinLin on 2015/9/25.
 * Description :
 * Version 1.0
 */
public class ChannelServiceException extends BaseServiceException {

    public ChannelServiceException(String systemName, String errorPropertyKey, String message, Throwable cause) {
        super(systemName, errorPropertyKey, message, cause);
    }

    public ChannelServiceException(String systemName, String errorPropertyKey, Throwable cause) {
        super(systemName, errorPropertyKey, cause);
    }

    public ChannelServiceException(String systemName, String errorPropertyKey) {
        super(systemName, errorPropertyKey);
    }

    public ChannelServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelServiceException(String message) {
        super(message);
    }

    public ChannelServiceException(Throwable cause) {
        super(cause);
    }
}
