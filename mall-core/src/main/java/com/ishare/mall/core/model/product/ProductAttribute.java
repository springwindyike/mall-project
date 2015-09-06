package com.ishare.mall.core.model.product;

import java.math.BigDecimal;

/**
 * Created by YinLin on 2015/9/6.
 * Description : 商品属性
 * Version 1.0
 */
public class ProductAttribute {
    //ID
    private Integer id;
    //所属商品
    private Product product;
    //所属属性
    private Attribute attribute;
    //属性名字
    private String name;
    //属性值
    private String value;
    //附加价
    private BigDecimal addition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getAddition() {
        return addition;
    }

    public void setAddition(BigDecimal addition) {
        this.addition = addition;
    }
}
