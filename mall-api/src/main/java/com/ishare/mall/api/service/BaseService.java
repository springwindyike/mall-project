package com.ishare.mall.api.service;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by YinLin on 2015/9/24.
 * Description :
 * Version 1.0
 */
public class BaseService {
    //核心APP地址
    @Value("#{settings['biz.app.url']}")
    protected String bizAppUrl;

    @Value("#{settings['ali.pay.return.url']}")
    protected String returnUrl;

    @Value("#{settings['ali.pay.partner']}")
    protected String partner;

    @Value("#{settings['ali.pay.key']}")
    protected String key;

    /**
     * 基础的path和apiPath
     * @param moduleRequestMapping
     * @param apiRequestMapping
     * @return
     */
    protected String buildBizAppURI(String moduleRequestMapping, String apiRequestMapping) {
        return bizAppUrl + moduleRequestMapping + apiRequestMapping;
    }
}
