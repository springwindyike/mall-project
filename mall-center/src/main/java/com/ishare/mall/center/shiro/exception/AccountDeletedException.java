package com.ishare.mall.center.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Administrator on 2015/11/16.
 */
public class AccountDeletedException extends AuthenticationException {

    public AccountDeletedException(){
        super();
    }

    public AccountDeletedException(String massage){
        super(massage);
    }

    public AccountDeletedException(Throwable cause){
        super(cause);
    }

    public AccountDeletedException(String massage, Throwable cause){
        super(massage,cause);
    }
}
