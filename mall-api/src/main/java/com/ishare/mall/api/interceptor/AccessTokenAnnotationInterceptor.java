package com.ishare.mall.api.interceptor;

import com.ishare.mall.api.annotation.AccessToken;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.common.OAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private OAuthService oAuthService;

    private static final Logger log = LoggerFactory.getLogger(AccessTokenAnnotationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof  HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            AccessToken accessToken = method.getMethodAnnotation(AccessToken.class);
            if (accessToken == null) return true;
            //如果有accessToken 1 检测 accessToken 是否存在，2检测 accessToken是否存在 ，过期或者不存在都返回未认证JSON 401
            String token = request.getParameter(OAuth.OAUTH_ACCESS_TOKEN);
            if (StringUtils.isBlank(token)) {
                throw new ApiLogicException("token不正确", HttpStatus.UNAUTHORIZED);
            }

            if (!oAuthService.checkAccessToken(token)) {
                throw new ApiLogicException("token不存在", HttpStatus.UNAUTHORIZED);
            }
        }
        return super.preHandle(request, response, handler);
    }

    public static Logger getLog() {
        return log;
    }
}
