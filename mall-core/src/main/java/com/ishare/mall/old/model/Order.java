package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import com.ishare.mall.old.model.status.DeliveryStatus;
import com.ishare.mall.old.model.status.OrderStatus;
import com.ishare.mall.old.model.status.PaymentStatus;
import com.ishare.mall.old.model.status.PaymentType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by dongqi on 15/7/12.
 * <p>
 * 订单
 */
@Entity
@Table(name = "es_order")
public class Order extends AbstractEntity {

    private String code;//订单号

    private OrderPrice price;
    private OrderTime orderTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> details;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATE;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddress address;
    @Temporal(TemporalType.TIMESTAMP)
    private Date userDeliveryTime;//用户填写的配送时间
    private String remark;//用户备注

    private String expressName;//快递名称
    private String expressCode;//快递单号
    private String expressType;//快递类型

    @Transient
    private Object handler;//TODO 预留的订单处理专员

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderPrice getPrice() {
        return price;
    }

    public void setPrice(OrderPrice price) {
        this.price = price;
    }

    public OrderTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(OrderTime orderTime) {
        this.orderTime = orderTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderItem> getDetails() {
        return details;
    }

    public void setDetails(Set<OrderItem> details) {
        this.details = details;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public DeliveryAddress getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddress address) {
        this.address = address;
    }

    public Date getUserDeliveryTime() {
        return userDeliveryTime;
    }

    public void setUserDeliveryTime(Date userDeliveryTime) {
        this.userDeliveryTime = userDeliveryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }
}
