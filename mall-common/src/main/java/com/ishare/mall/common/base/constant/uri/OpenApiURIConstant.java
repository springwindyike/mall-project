package com.ishare.mall.common.base.constant.uri;

/**
 * Created by YinLin on 2015/9/15.
 * Description : 对外开放API的接口地址
 * Version 1.0
 */
public interface OpenApiURIConstant {
    interface Oauth {
        //获取认证CODE
        String AUTHORIZE    = "authorize";
        //获取accessToken
        String ACCESS_TOKEN = "access/token";
    }
}
