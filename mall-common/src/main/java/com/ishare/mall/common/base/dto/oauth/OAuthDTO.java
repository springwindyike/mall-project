package com.ishare.mall.common.base.dto.oauth;

/**
 * Created by YinLin on 2015/9/24.
 * Description :
 * Version 1.0
 */
public class OAuthDTO {

    private String access_token;

    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
