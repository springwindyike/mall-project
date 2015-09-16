package com.ishare.mall.api.service.oauth;

import com.ishare.mall.common.base.dto.oauth.OAuthObject;

/**
 * Created by YinLin on 2015/8/12.
 * Description : OAuthService 认证相关
 * Version 1.0
 */
public interface OAuthService {
    /**
     * 添加 auth code
     * @param authCode
     * @param account
     */
    void addAuthCode(String authCode, String account, String clientId);

    /**
     * 添加 accessToken
     * @param accessToken
     * @param account
     */
    void addAccessToken(String accessToken, String account, String clientId);

    /**
     * 检测authCode
     * @param authCode
     * @return
     */
    boolean checkAuthCode(String authCode, String clientId);

    /**
     * 检测AccessToken
     * @param accessToken
     * @return
     */
    boolean checkAccessToken(String accessToken);

    /**
     * 通过authCode得到account
     * @param authCode
     * @return
     */
    String getAccountByAuthCode(String authCode);

    /**
     * 通过accessToken得到account
     * @param accessToken
     * @return
     */
    String getAccountByAccessToken(String accessToken);

    /**
     * 通过accessToken得到对象
     * @param accessToken
     * @return
     */
    OAuthObject getAuthObjectByAccessToken(String accessToken);

    /**
     * 获得过期时间
     * @return
     */
    long getExpireIn();

    /**
     * 检测clientId
     * @param clientId
     * @return
     */
    boolean checkClientId(String clientId);

    /**
     * 检测
     * @param clientSecret
     * @return
     */
    boolean checkClientSecret(String clientId, String clientSecret);

    /**
     * 请求调用
     * @param account
     */
    boolean checkAccount(String account);
}
