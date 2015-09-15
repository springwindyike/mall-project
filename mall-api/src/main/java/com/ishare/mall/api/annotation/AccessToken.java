package com.ishare.mall.api.annotation;

import java.lang.annotation.*;

/**
 * Created by YinLin on 2015/9/14.
 * Description : 注解验证token是否存在
 * Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessToken {

}
