package com.ishare.mall.common.base.dto.order;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by Zhangzhaoxin on 2015/8/27.
 * Description: productDTO
 * Version 1.0
 */
@JsonAutoDetect
public class OrderDetailDTO implements BaseObject {
    private String orderId;
    /* 创建订单者 */
    private String createBy;
    /**更新订单者**/
    private String updateBy;
    /* 订单创建时间 */
    private Date createTime = new Date();
    private Date updateTime = new Date();
    /* 订单状态 */
    private String state;
    /* 商品总金额 */
    private Float productTotalPrice = 0f;
    /* 配送费 */
    private Float deliverFee = 0f;
    /* 订单总金额 */
    private Float totalPrice= 0f;
    /* 应付款(实际需要支付的费用) */
    private Float payableFee = 0f;
    /* 顾客附言 */
    private String note;
    /* 支付方式 */
    private String paymentWay;
    /* 支付状态 */
    private Boolean paymentState = false;
    /* 订单配送信息 */
    private Integer orderDeliverInfoId;
    /* 订单购买者联系信息 */
    private Integer orderContactInfoId;
    /* 对订单进行加锁的用户,如果值为null,代表订单未被加锁,否则,订单被加锁 */
    private String lockMember;
    /**各个订单对应的渠道**/
    private Integer channelId;
    private String expressOrder;//快递单号
    private String expressId;//快递代号
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Float getProductTotalPrice() {
		return productTotalPrice;
	}
	public void setProductTotalPrice(Float productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	public Float getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(Float deliverFee) {
		this.deliverFee = deliverFee;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Float getPayableFee() {
		return payableFee;
	}
	public void setPayableFee(Float payableFee) {
		this.payableFee = payableFee;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	public Boolean getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(Boolean paymentState) {
		this.paymentState = paymentState;
	}
	public Integer getOrderDeliverInfoId() {
		return orderDeliverInfoId;
	}
	public void setOrderDeliverInfoId(Integer orderDeliverInfoId) {
		this.orderDeliverInfoId = orderDeliverInfoId;
	}
	public Integer getOrderContactInfoId() {
		return orderContactInfoId;
	}
	public void setOrderContactInfoId(Integer orderContactInfoId) {
		this.orderContactInfoId = orderContactInfoId;
	}
	public String getLockMember() {
		return lockMember;
	}
	public void setLockMember(String lockMember) {
		this.lockMember = lockMember;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getExpressOrder() {
		return expressOrder;
	}
	public void setExpressOrder(String expressOrder) {
		this.expressOrder = expressOrder;
	}
	public String getExpressId() {
		return expressId;
	}
	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}
    
}
