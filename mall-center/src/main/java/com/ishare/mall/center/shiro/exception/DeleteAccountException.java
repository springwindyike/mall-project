package com.ishare.mall.center.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Administrator on 2015/11/16.
 */
public class DeleteAccountException extends AuthenticationException {

    public DeleteAccountException(){
        super();
    }

    public DeleteAccountException(String massage){
        super(massage);
    }

    public DeleteAccountException(Throwable cause){
        super(cause);
    }

    public DeleteAccountException(String massage,Throwable cause){
        super(massage,cause);
    }
}
