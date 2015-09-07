package com.ishare.mall.common.base.dto.member;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * Created by Zhangzhaoxin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class MemberLoginDTO extends GenericDTO {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
