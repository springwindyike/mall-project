package com.ishare.mall.common.base.dto.member;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.enumeration.Gender;

/**
 * Created by YinLin on 2015/10/9.
 * Description :
 * Version 1.0
 */
public class CurrentMemberDTO extends GenericDTO {

    private String name;

    private Integer id;

    private String account;

    private Integer channelId;

    private String channelName;

    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
