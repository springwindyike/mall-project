package com.ishare.mall.center.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by YinLin on 2015/9/10.
 * Description :
 * Version 1.0
 */
public class WebUtils {
    @Value("#{settings['biz.app.url']}")
    private String bizApp;
    public WebUtils() {
        System.out.println(bizApp);
    }
}
