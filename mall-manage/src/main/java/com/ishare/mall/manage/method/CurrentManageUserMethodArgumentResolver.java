package com.ishare.mall.manage.method;

import com.ishare.mall.manage.annoation.CurrentManageUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by YinLin on 2015/9/10.
 * Description : 解析@CurrentManageUser
 * Version 1.0
 */
public class CurrentManageUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public CurrentManageUserMethodArgumentResolver() {
    }
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentManageUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentManageUser currentManageUser = parameter.getParameterAnnotation(CurrentManageUser.class);
        return webRequest.getAttribute(currentManageUser.value(), NativeWebRequest.SCOPE_REQUEST);
    }
}
