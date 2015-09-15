package com.ishare.mall.api.interceptor;

import com.ishare.mall.api.annotation.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YinLin on 2015/9/15.
 * Description : accessToken认证
 * Version 1.0
 */
public class AccessTokenAnnotationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenAnnotationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod)handler;
        AccessToken accessToken = method.getMethodAnnotation(AccessToken.class);
        if (accessToken == null) return true;
        //如果有accessToken 1 检测 accessToken 是否存在，2检测 accessToken是否存在 ，过期或者不存在都返回未认证JSON 401
        return super.preHandle(request, response, handler);
    }

    public static Logger getLog() {
        return log;
    }
}
