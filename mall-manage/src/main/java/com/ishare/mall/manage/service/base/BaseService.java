package com.ishare.mall.manage.service.base;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by YinLin on 2015/10/9.
 * Description :
 * Version 1.0
 */
public class BaseService {

    //核心APP地址
    @Value("#{settings['biz.app.url']}")
    protected String bizAppUrl;

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
