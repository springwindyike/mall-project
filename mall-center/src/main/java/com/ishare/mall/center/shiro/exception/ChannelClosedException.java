package com.ishare.mall.center.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ChannelClosedException extends AuthenticationException {

    public ChannelClosedException(){
        super();
    }

    public ChannelClosedException(String massage){
        super(massage);
    }

    public ChannelClosedException(Throwable cause){
        super(cause);
    }

    public ChannelClosedException(String massage, Throwable cause){
        super(massage,cause);
    }
}
