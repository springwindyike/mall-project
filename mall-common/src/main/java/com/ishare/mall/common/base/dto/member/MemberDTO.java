package com.ishare.mall.common.base.dto.member;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * Created by YinLin on 2015/9/1. Description : Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class MemberDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;

	private String account;

	private String password;

	private Integer channelId;

	private PageRequest pageRequest;

	private Integer roleId;

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

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public void setPage(Page<MemberDetailDTO> mapper) {
		// TODO Auto-generated method stub
		
	}

	public void setMemberDetailDTO(MemberDetailDTO memberDetailDTO) {
		// TODO Auto-generated method stub
		
	}

}
