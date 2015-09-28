package com.ishare.mall.common.base.dto.order;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * Created by Zhang Zhaoxin on 2015/9/25.
 * Description : 订单更新
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class OrderUpdateLogDTO extends GenericDTO {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/* 所属订单 */
	private String order;
	/* 修改者 */
	private String updateBy;
	/* 修改时间 */
	private String updateTime;
	/* 说明 */
	private String note;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
