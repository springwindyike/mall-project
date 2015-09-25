package com.ishare.mall.common.base.dto.oauth;

/**
 * Created by YinLin on 2015/8/24.
 * Description : 主要用与缓存
 * Version 1.0
 */
public class OAuthObject {
    //服务器返回token
    private String accessToken;
    //服务器认证code
    private String accessCode;
    //缓存clientId
    private String clientId;
    //缓存ClientName
    private String clientName;
    //缓存认证ID
    private String account;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "OAuthObject{" +
                "accessToken='" + accessToken + '\'' +
                ", accessCode='" + accessCode + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
