package com.ishare.mall.core.service.oauth.impl;

import com.ishare.mall.common.base.dto.oauth.OAuthObject;
import com.ishare.mall.core.service.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static com.ishare.mall.common.base.constant.ResourceConstant.OAUTH.EXPIRE_IN;

/**
 * Created by YinLin on 2015/8/12.
 * Description : OAuthServiceImpl oauth的第三方认证最核心部分
 * Version 1.0
 */
//@Service
public class OAuthServiceImpl implements OAuthService {

    private Cache cache;
//    @Autowired
//    private ChannelService channelService;

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
    }

    @Override
    public void addAccessToken(String accessToken, String account, String clientId) {
        OAuthObject authObject = new OAuthObject();
        authObject.setAccount(accessToken);
        authObject.setAccount(account);
        authObject.setClientId(clientId);
        this.cache.put(accessToken, account);
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public String getAccountByAuthCode(String authCode) {
        OAuthObject authObject = (OAuthObject)cache.get(authCode).get();
        return authObject == null? null : authObject.getAccount();
    }

    @Override
    public String getAccountByAccessToken(String accessToken) {
        OAuthObject authObject = (OAuthObject)cache.get(accessToken).get();
        return authObject == null? null : authObject.getAccount();
    }

    @Override
    public long getExpireIn() {
        return EXPIRE_IN;
    }

    @Override
    public boolean checkClientId(String clientId) {
        return true;//channelService.findByAppId(clientId) != null;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return true;//channelService.findByAppSecret(clientSecret) != null;
    }
}
