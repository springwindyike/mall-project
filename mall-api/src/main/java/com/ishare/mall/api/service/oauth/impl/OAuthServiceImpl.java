package com.ishare.mall.api.service.oauth.impl;

import com.ishare.mall.api.service.channel.ChannelService;
import com.ishare.mall.api.service.member.MemberService;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.dto.oauth.OAuthObject;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.ishare.mall.common.base.constant.ResourceConstant.OAUTH.EXPIRE_IN;

/**
 * Created by YinLin on 2015/8/12.
 * Description : OAuthServiceImpl oauth的第三方认证最核心部分
 * Version 1.0
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MemberService memberService;

    private static final Logger log = LoggerFactory.getLogger(OAuthServiceImpl.class);

    private Cache cache;

    @Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

    @Override
    public void addAuthCode(String authCode, String account, String clientId) {
        OAuthObject authObject = new OAuthObject();
        authObject.setAccessCode(authCode);
        authObject.setAccount(account);
        authObject.setClientId(clientId);
        this.cache.put(authCode, authObject);
        log.debug(this.cache.get(authCode, OAuthObject.class).toString());
    }

    @Override
    public OAuthObject addAccessToken(String accessToken, String account, String clientId) {
        OAuthObject authObject = new OAuthObject();
        authObject.setAccessToken(accessToken);
        authObject.setAccount(account);
        authObject.setClientId(clientId);
        this.cache.put(accessToken, authObject);
        return authObject;
    }

    @Override
    public boolean checkAuthCode(String authCode, String clientId) {
        log.debug("authCode : " + authCode);
        OAuthObject authObject = this.cache.get(authCode, OAuthObject.class);
        log.debug("authObject : " + authObject.toString());
        if (authObject == null) return false;
        return clientId.equals(authObject.getClientId());
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public String getAccountByAuthCode(String authCode) {
        OAuthObject authObject = getAuthObjectByAccessToken(authCode);
        return authObject == null? null : authObject.getAccount();
    }

    @Override
    public String getAccountByAccessToken(String accessToken) {
        OAuthObject authObject = getAuthObjectByAccessToken(accessToken);
        return authObject == null ? null : authObject.getAccount();
    }

    @Override
    public OAuthObject getAuthObjectByAccessToken(String accessToken) {
        return cache.get(accessToken, OAuthObject.class);
    }

    @Override
    public long getExpireIn() {
        return EXPIRE_IN;
    }

    @Override
    public boolean checkClientId(String clientId) {
        ChannelTokenResultDTO channelTokenResultDTO;
        try {
            channelTokenResultDTO = channelService.findByAppId(clientId);
        } catch (Exception e) {
            return false;
        }
        return channelTokenResultDTO != null;
    }

    @Override
    public boolean checkClientSecret(String clientId, String clientSecret) {
        ChannelTokenResultDTO channelTokenResultDTO;
        try {
            channelTokenResultDTO = channelService.findByAppId(clientId);
        } catch (Exception e) {
            return false;
        }
        return clientSecret.equals(channelTokenResultDTO.getAppSecret());
    }

    @Override
    public boolean checkAccount(String account) {
        return true;
    }

    @Override
    public OAuthObject createToken(String type, String appid, String secret, String account) {

        if (StringUtils.isBlank(type) || !type.equals(CheckValue.GRANT_TYPE)) {
            throw new ApiLogicException("grant_type：不正确", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(account)) {
            throw new ApiLogicException("account:参数未传", HttpStatus.BAD_REQUEST);
        }

        if (!checkClientSecret(appid, secret)) {
            throw new ApiLogicException("appid/secret:不正确", HttpStatus.BAD_REQUEST);
        }

        //检测和创建用户
        memberService.checkAndCreateMember(account, appid);

        //生成Access Token
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        try {
            final String accessToken = oauthIssuerImpl.accessToken();
            return this.addAccessToken(accessToken, account, appid);
        } catch (OAuthSystemException e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("token:获取失败", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String getClientId(String token) {
        OAuthObject authObject = getAuthObjectByAccessToken(token);
        return authObject == null? null : authObject.getClientId();
    }
}
