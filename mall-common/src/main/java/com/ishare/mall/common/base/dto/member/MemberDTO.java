package com.ishare.mall.common.base.dto.member;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    private String account;

    private String password;

    private Page<MemberDetailDTO> page;

    private PageRequest pageRequest;

    private Integer channelId;

    private Integer roleId;

    private MemberDetailDTO memberDetailDTO;

    private int offset;
    private int limit;

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

    public Page<MemberDetailDTO> getPage() {
        return page;
    }

    public void setPage(Page<MemberDetailDTO> page) {
        this.page = page;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
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
}
