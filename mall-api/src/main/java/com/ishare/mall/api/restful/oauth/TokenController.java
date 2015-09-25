package com.ishare.mall.api.restful.oauth;

import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.common.base.dto.oauth.OAuthDTO;
import com.ishare.mall.common.base.dto.oauth.OAuthObject;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YinLin on 2015/9/25.
 * Description :
 * Version 1.0
 */
@Controller
public class TokenController extends BaseResource {

    @Autowired
    private OAuthService oAuthService;
    @RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpEntity token(HttpServletRequest request) throws ApiLogicException{
        String grant_type = request.getParameter("grant_type");
        String appid = request.getParameter("appid");
        String secret = request.getParameter("secret");
        String account = request.getParameter("account");
        OAuthObject oAuthObject = oAuthService.createToken(grant_type, appid, secret, account);
        OAuthDTO authDTO = new OAuthDTO();
        authDTO.setAccess_token(oAuthObject.getAccessToken());
        authDTO.setExpires_in(oAuthService.getExpireIn());
        Response response = new Response();
        response.setCode(200);
        response.setData(authDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
