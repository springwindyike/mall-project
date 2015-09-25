package com.ishare.mall.api.restful.oauth;

import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.common.base.constant.uri.OpenApiURIConstant;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

import static com.ishare.mall.common.base.constant.ResourceConstant.OAUTH.INVALID_ACCOUNT_DESCRIPTION;
import static com.ishare.mall.common.base.constant.ResourceConstant.OAUTH.INVALID_CLIENT_DESCRIPTION;

/**
 * Created by YinLin on 2015/8/11.
 * Description : Oauth2 接口 token认证接口
 * Version 1.0
 */
@Controller
@Deprecated
public class AuthorizeController {

    private static final Logger log = LoggerFactory.getLogger(AuthorizeController.class);

    @Autowired
    private OAuthService oAuthService;

    /**
     * 获取accessToken code
     * @param model
     * @param request
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = OpenApiURIConstant.Oauth.AUTHORIZE, method = RequestMethod.GET)
    public HttpEntity authorize(Model model, HttpServletRequest request) throws URISyntaxException {
        try {
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
            //检查传入的客户端id是否正确
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                throw new ApiLogicException(INVALID_CLIENT_DESCRIPTION, HttpStatus.BAD_REQUEST);
            }

            //获取客户端account 手机号
            String account = request.getParameter("account");
            // 如果没有就创建 检测account合法性
            if (!oAuthService.checkAccount(account)) {
                throw new ApiLogicException(INVALID_ACCOUNT_DESCRIPTION, HttpStatus.BAD_REQUEST);
            }
            //生成授权码
            String authorizationCode = null;
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();
                oAuthService.addAuthCode(authorizationCode, account, oauthRequest.getClientId());
            } else {
                throw new ApiLogicException("response_type未传值", HttpStatus.BAD_REQUEST);
            }
            //进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request,
                            HttpServletResponse.SC_FOUND);
            //设置授权码
            builder.setCode(authorizationCode);
            //得到到客户端重定向地址
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

//            Response response = new Response();
//            response.setCode(HttpStatus.OK.value());
//            //设置code
//            response.setData(authorizationCode);

            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
            //根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            String uri = response.getLocationUri();
            //URL url = new URL(uri);
            //uri = uri.replaceAll("=", "%3D").replaceAll("/", "%2F").replaceAll(":","%3A");
            log.debug("uri" + uri);
            headers.setLocation(new URI(uri));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthSystemException e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("获取Code失败！",HttpStatus.BAD_REQUEST);
        } catch (OAuthProblemException e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("获取Code失败！",HttpStatus.BAD_REQUEST);
        }
    }

    public static Logger getLog() {
        return log;
    }
}
