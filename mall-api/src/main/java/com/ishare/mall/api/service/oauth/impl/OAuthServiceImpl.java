package com.ishare.mall.api.service.oauth.impl;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.dto.oauth.OAuthObject;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.api.service.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.ishare.mall.common.base.constant.ResourceConstant.OAUTH.EXPIRE_IN;

/**
 * Created by YinLin on 2015/8/12.
 * Description : OAuthServiceImpl oauth的第三方认证最核心部分
 * Version 1.0
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    private Cache cache;

    @Autowired
    private ChannelService channelService;
    //核心APP地址
    @Value("#{settings['biz.app.url']}")
    protected String bizAppUrl;

    /**
     * 基础的path和apiPath
     * @param moduleRequestMapping
     * @param apiRequestMapping
     * @return
     */
    protected String buildBizAppURI(String moduleRequestMapping, String apiRequestMapping) {
        return bizAppUrl + moduleRequestMapping + apiRequestMapping;
    }

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
    public boolean checkAuthCode(String authCode, String clientId) {
        OAuthObject authObject = (OAuthObject)cache.get(authCode);
        if (authObject == null) return false;
        return clientId.equals(authObject.getClientId());
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
    public OAuthObject getAuthObjectByAccessToken(String accessToken) {
        return (OAuthObject)cache.get(accessToken).get();
    }

    @Override
    public long getExpireIn() {
        return EXPIRE_IN;
    }

    @Override
    public boolean checkClientId(String clientId) {
        ResponseEntity<ChannelTokenResultDTO> resultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_ID + clientId), ChannelTokenResultDTO.class);
        ChannelTokenResultDTO channelTokenResultDTO = resultDTO.getBody();
        return channelTokenResultDTO != null;
    }

    @Override
    public boolean checkClientSecret(String clientId, String clientSecret) {
        ResponseEntity<ChannelTokenResultDTO> resultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_SECRET + clientSecret), ChannelTokenResultDTO.class);
        ChannelTokenResultDTO channelTokenResultDTO = resultDTO.getBody();
        if (channelTokenResultDTO == null) return false;
        return clientId.equals(channelTokenResultDTO.getAppId());
    }

    @Override
    public boolean checkAccount(String account) {
        return false;
    }
}
