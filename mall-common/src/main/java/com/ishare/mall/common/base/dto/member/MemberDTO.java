package com.ishare.mall.common.base.dto.member;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.enumeration.MemberType;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class MemberDTO extends GenericDTO {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String account;
    private String password;

    private String salt;

    private Integer channelId;
    private Integer roleId;
    private MemberDetailDTO memberDetailDTO;
    private int offset;
    private int limit;
    private String sex;
    private String mobile;
    private String name;
    private MemberType memberType;
    private PageDTO pageDTO;
    private Gender gender;
    private String channelName;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public MemberDetailDTO getMemberDetailDTO() {
        return memberDetailDTO;
    }

    public void setMemberDetailDTO(MemberDetailDTO memberDetailDTO) {
        this.memberDetailDTO = memberDetailDTO;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PageDTO getPageDTO() {
        return pageDTO;
    }

    public void setPageDTO(PageDTO pageDTO) {
        this.pageDTO = pageDTO;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return account + salt;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Integer getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
