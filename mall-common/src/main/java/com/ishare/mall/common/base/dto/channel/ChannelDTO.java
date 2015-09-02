package com.ishare.mall.common.base.dto.channel;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.object.BaseObject;
@XmlRootElement
@JsonAutoDetect
public class ChannelDTO implements BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer channelId;
	
	public float totalMoney;

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	

}
