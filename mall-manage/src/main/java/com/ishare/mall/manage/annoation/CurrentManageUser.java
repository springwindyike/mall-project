package com.ishare.mall.manage.annoation;

import com.ishare.mall.common.base.constant.CommonConstant;

import java.lang.annotation.*;

/**
 * Created by YinLin on 2015/9/10.
 * Description : 当前登录用户
 * Version 1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentManageUser {
    /**
     * 当前用户在Request中的key
     * @return
     */
    String value() default CommonConstant.Common.CURRENT_MANAGEUSER;
}
