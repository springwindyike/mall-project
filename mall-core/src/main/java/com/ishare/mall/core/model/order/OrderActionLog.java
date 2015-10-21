package com.ishare.mall.core.model.order;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_ACTION_LOG_NAME;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Zhang Zhaoxin on 2015/9/25. 
 * Description : 订单修改日志 
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ORDER_ACTION_LOG_NAME)
public class OrderActionLog {
	@Id
	@GeneratedValue
	private Integer id;
	/* 所属订单 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "action_order_id")
	private Order order;
	/* 修改者ID */
	@Column(length = 50, name = "action_by_id")
	private String actionById;
	/* 修改者类型 */
	@Column(length = 50, name = "action_by_type")
	private String actionBytype;
	/* 修改者名称 */
	@Column(length = 50, name = "action_by_name")
	private String actionByname;
	/* 修改来源 */
	@Column(length = 50, name = "action_by_from")
	private String actionByfrom;
	/* 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "action_log_action_time", length = 20)
	private Date actionTime;
	/* 说明 */
	@Column(length = 100, name = "action_order_note")
	private String note;

	public Integer getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public String getActionById() {
		return actionById;
	}

	public String getActionBytype() {
		return actionBytype;
	}

	public String getActionByname() {
		return actionByname;
	}

	public String getActionByfrom() {
		return actionByfrom;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public String getNote() {
		return note;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setActionById(String actionById) {
		this.actionById = actionById;
	}

	public void setActionBytype(String actionBytype) {
		this.actionBytype = actionBytype;
	}

	public void setActionByname(String actionByname) {
		this.actionByname = actionByname;
	}

	public void setActionByfrom(String actionByfrom) {
		this.actionByfrom = actionByfrom;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public void setNote(String note) {
		this.note = note;
	}
}