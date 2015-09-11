package com.ishare.mall.center.annoation;

import com.ishare.mall.common.base.constant.CommonConstant;

import java.lang.annotation.*;

/**
 * Created by YinLin on 2015/9/11.
 * Description : 封装分页插件
 * Version 1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageRequest {
    /**
     * 当前页面在Request中的key
     * @return
     */
    String value() default CommonConstant.Common.CURRENT_MEMBER;
}
