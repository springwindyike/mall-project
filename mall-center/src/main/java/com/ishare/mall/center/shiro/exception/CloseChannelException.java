package com.ishare.mall.center.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Administrator on 2015/11/16.
 */
public class CloseChannelException extends AuthenticationException {

    public CloseChannelException(){
        super();
    }

    public CloseChannelException(String massage){
        super(massage);
    }

    public CloseChannelException(Throwable cause){
        super(cause);
    }

    public CloseChannelException(String massage,Throwable cause){
        super(massage,cause);
    }
}
