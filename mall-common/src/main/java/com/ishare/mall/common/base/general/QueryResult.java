package com.ishare.mall.common.base.general;

import java.io.Serializable;

public class QueryResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean success = true;
	private String msg;
	private T datainfo;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getDatainfo() {
		return datainfo;
	}
	public void setDatainfo(T datainfo) {
		this.datainfo = datainfo;
	}
}
