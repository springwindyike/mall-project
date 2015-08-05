package com.ishare.mall.old.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by dongqi on 15/7/20.
 */
@Embeddable
public class OrderPrice {

    @Column(name = "price_total")
    private double total;//总金额
    @Column(name = "price_need_pay")
    private double needPay;//应付金额
    @Column(name = "price_discount")
    private double discount;//优惠金额
    @Column(name = "price_shipment")
    private double shipment;//运费

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getNeedPay() {
        return needPay;
    }

    public void setNeedPay(double needPay) {
        this.needPay = needPay;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShipment() {
        return shipment;
    }

    public void setShipment(double shipment) {
        this.shipment = shipment;
    }
}
