package com.ishare.mall.center.controller.base;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by YinLin on 2015/9/1.
 * Description : 基本controller
 * Version 1.0
 */
public class BaseController {
    //核心APP地址
    @Value("#{settings['biz.app.url']}")
    protected String bizAppUrl;
}
