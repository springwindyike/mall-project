package com.ishare.mall.common.base.general;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String msg;
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
