package com.ishare.mall.old.model;

import javax.persistence.Embeddable;

/**
 * Created by dongqi on 15/7/20.
 * <p>
 * Ex-factory price
 * Unit price
 * Competitive products price
 * Limit the purchase price
 * Sale price
 *
 */
@Embeddable
public class Price {

    private double exFactoryPrice; //出厂价
    private double unitPrice;//单价
    private double competitiveProductsPrice;//竞品价
    private double limitPurchasePrice;//限购价
    private double salePrice;//促销价
    private double price;//市场价

    public Price() {
    }

    public Price(double price) {
        this.exFactoryPrice = price;
        this.unitPrice = price;
        this.competitiveProductsPrice = price;
        this.limitPurchasePrice = price;
        this.salePrice = price;
        this.price = price;
    }

    public double getExFactoryPrice() {
        return exFactoryPrice;
    }

    public void setExFactoryPrice(double exFactoryPrice) {
        this.exFactoryPrice = exFactoryPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getCompetitiveProductsPrice() {
        return competitiveProductsPrice;
    }

    public void setCompetitiveProductsPrice(double competitiveProductsPrice) {
        this.competitiveProductsPrice = competitiveProductsPrice;
    }

    public double getLimitPurchasePrice() {
        return limitPurchasePrice;
    }

    public void setLimitPurchasePrice(double limitPurchasePrice) {
        this.limitPurchasePrice = limitPurchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
