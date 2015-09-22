package com.ishare.mall.manage.controller.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YinLin on 15/7/24.
 */
public class BaseController {
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

    public Map<String, Object> getEasyUIData(Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", page.getContent());
        map.put("total", page.getTotalElements());
        return map;
    }
}
