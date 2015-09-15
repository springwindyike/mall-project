package com.ishare.mall.common.base.dto.validform;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
@XmlRootElement
@JsonAutoDetect
public class ValidformRespDTO  extends GenericDTO{

	private static final long serialVersionUID = 1L;
	
	private String info;
	private String status;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
