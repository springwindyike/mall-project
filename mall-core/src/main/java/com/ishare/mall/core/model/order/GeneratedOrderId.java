package com.ishare.mall.core.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_ID_NAME;

/**
 * create by YinLin
 * 订单ID生成表
 */
@Entity(name = TABLE_ORDER_ID_NAME)
public class GeneratedOrderId {
	@Id
	@Column(length = 5)
	private String id;
	@Column(nullable = false, name = "order_id")
	private Integer orderId = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
