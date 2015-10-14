package com.ishare.mall.center.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Zhnngzhaoxin on 2015/10/13.
 * Description : 
 * Version 1.0
 */
public class NoPermissionLoginException extends AuthenticationException {
    public NoPermissionLoginException() {
        super();
    }

    public NoPermissionLoginException(String message) {
        super(message);
    }

    public NoPermissionLoginException(Throwable cause) {
        super(cause);
    }

    public NoPermissionLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
