package com.ishare.mall.common.base.dto.order;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.dto.page.PageRequestDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by YinLin on 2015/10/13.
 * Description :
 * Version 1.0
 */
@JsonAutoDetect
public class OrderRequestDTO extends GenericDTO{

    private String account;

    private String clientId;

    private PageRequestDTO pageRequest;

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

    public PageRequestDTO getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequestDTO pageRequest) {
        this.pageRequest = pageRequest;
    }
}
