package com.ishare.mall.common.base.dto.member;

import java.io.Serializable;

/**
 * Created by YinLin on 2015/9/25.
 * Description : ¼ì²âºÍ´´½¨DTO
 * Version 1.0
 */
public class CheckAndCreateDTO implements Serializable {

    private String account;

    private String clientId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
