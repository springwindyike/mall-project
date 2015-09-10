package com.ishare.mall.center.method;

import com.ishare.mall.center.annoation.CurrentMember;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by YinLin on 2015/9/10.
 * Description : 解析@CurrentMember
 * Version 1.0
 */
public class CurrentMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public CurrentMemberMethodArgumentResolver() {
    }
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentMember.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentMember currentMember = parameter.getParameterAnnotation(CurrentMember.class);
        return webRequest.getAttribute(currentMember.value(), NativeWebRequest.SCOPE_REQUEST);
    }
}
