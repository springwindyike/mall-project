package com.ishare.mall.common.base.dto.member;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by liaochenglei on 2015/8/21.
 * Description: productDTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class MemberDetailDTO extends GenericDTO {
	
	private String account;//用户账号
	
	 private Integer channelId;//渠道id
	 
	 private String name;//账号名称
	 
	 private String memberType; //用户类型
	 
	 private String sex;//用户性别
	 
	 private Date createTime;//创建时间
	 
	 private Date updateTime;//更新时间
	 
	 private String createBy;//创建者
	 
	 private String updateBy;//更新者
	 
	 private boolean use;//是否可用

	private String password;

	private String mobile;

	private String createTimeStr;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}
