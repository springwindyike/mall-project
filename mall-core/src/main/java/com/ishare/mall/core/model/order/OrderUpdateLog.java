package com.ishare.mall.core.model.order;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_UPDATE_LOG_NAME;

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

import com.ishare.mall.core.model.member.Member;

/**
 * Created by Zhang Zhaoxin on 2015/9/25. 
 * Description : 订单修改日志 
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ORDER_UPDATE_LOG_NAME)
public class OrderUpdateLog {
	@Id
	@GeneratedValue
	private Integer id;
	/* 所属订单 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "update_order_id")
	private Order order;
	/* 修改者 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "update_order_by")
	private Member updateBy;
	/* 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "update_log_update_time", length = 20)
	private Date updateTime;
	/* 说明 */
	@Column(length = 100, name = "update_order_note")
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Member getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Member updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
