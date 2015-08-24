package com.ishare.mall.core.model.order;


import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_NAME;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.status.OrderState;
import com.ishare.mall.core.status.PaymentWay;

/**
 * Created by YinLin on 2015/7/31.
 * Description: 订单类 包括订单项，收货地址，收货人联系方式，订单处理人员，快递单号，快递名称，快递类型
 * Version 1.0
 */
@Entity(name = TABLE_ORDER_NAME)
public class Order  {
    /* 订单号 年份+月+日+当天订单总数*/
    @Id @Column(length = 14, name = "order_id")
    private String orderId;
    /* 创建订单者 */
    
    @Column(name = "order_create_by")
    private String createBy;
    /**更新订单者**/
    
    @Column(name = "order_update_by")
    private String updateBy;
    /* 订单创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, name = "order_create_time")
    private Date createTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, name = "order_update_time")
    private Date updateTime = new Date();
    /* 订单状态 */
    @Enumerated(EnumType.STRING)
    @Column(length=16, nullable = false, name = "order_state")
    private OrderState state;
    /* 商品总金额 */
    @Column(nullable = false, name = "order_product_total_price")
    private Float productTotalPrice = 0f;
    /* 配送费 */
    @Column(nullable = false, name = "order_deliver_fee")
    private Float deliverFee = 0f;
    /* 订单总金额 */
    @Column(nullable = false, name = "order_total_price")
    private Float totalPrice= 0f;
    /* 应付款(实际需要支付的费用) */
    @Column(nullable=false, name = "order_payable_fee")
    private Float payableFee = 0f;
    /* 顾客附言 */
    @Column(length = 100, name = "order_note")
    private String note;
    /* 支付方式 */
    @Enumerated(EnumType.STRING)
    @Column(length=20, nullable=false, name = "order_payment_way")
    private PaymentWay paymentWay;
    /* 支付状态 */
    @Column(nullable=false, name = "order_payment_state")
    private Boolean paymentState = false;
    /* 订单配送信息 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliver_id")
    private OrderDeliverInfo orderDeliverInfo;
    /* 订单购买者联系信息 */
    @OneToOne(cascade = CascadeType.ALL, optional=true)
    @JoinColumn(name="contact_id")
    private OrderContactInfo orderContactInfo;
    /* 订单项 */
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<OrderItem>();
    /* 对订单进行加锁的用户,如果值为null,代表订单未被加锁,否则,订单被加锁 */
    @Column(length = 20, name = "order_lock_member")
    private String lockMember;
    /**客服留言**/
    @OneToMany(mappedBy = "order", cascade=CascadeType.REMOVE)
    private Set<OrderMessage> orderMessages = new HashSet<OrderMessage>();
    /**各个订单对应的渠道**/
    @ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE}, optional=false)
    @JoinColumn(name="channel_id")
    private Channel channel;
  
    @Column(name = "express_order",nullable=false)
    private String expressOrder;//快递单号
    
    @Column(name = "express_id", nullable=false,length=12)
    private String expressId;//快递代号
    
    public Order(){}

    public String getOrderId() {
				return orderId;
			}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderState getState() {
        return state;
    }
    public void setState(OrderState state) {
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
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public PaymentWay getPaymentWay() {
        return paymentWay;
    }
    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }
    public OrderDeliverInfo getOrderDeliverInfo() {
        return orderDeliverInfo;
    }
    public void setOrderDeliverInfo(OrderDeliverInfo orderDeliverInfo) {
        this.orderDeliverInfo = orderDeliverInfo;
    }
    public OrderContactInfo getOrderContactInfo() {
        return orderContactInfo;
    }
    public void setOrderContactInfo(OrderContactInfo orderContactInfo) {
        this.orderContactInfo = orderContactInfo;
    }
    public Set<OrderItem> getItems() {
        return items;
    }
    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
    public void addOrderItem(OrderItem item){
        this.items.add(item);
        item.setOrder(this);
    }
    
    public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (orderId == null) {
            if (other.orderId != null)
                return false;
        } else if (!orderId.equals(other.orderId))
            return false;
        return true;
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

    public Float getPayableFee() {
        return payableFee;
    }

    public void setPayableFee(Float payableFee) {
        this.payableFee = payableFee;
    }

    public Boolean getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Boolean paymentState) {
        this.paymentState = paymentState;
    }

    public String getLockMember() {
        return lockMember;
    }

    public void setLockMember(String lockMember) {
        this.lockMember = lockMember;
    }

    public Set<OrderMessage> getOrderMessages() {
        return orderMessages;
    }

    public void setOrderMessages(Set<OrderMessage> orderMessages) {
        this.orderMessages = orderMessages;
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
