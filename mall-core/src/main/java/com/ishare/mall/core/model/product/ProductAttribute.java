package com.ishare.mall.core.model.product;

import com.ishare.mall.core.status.ValueType;

import javax.persistence.*;
import java.math.BigDecimal;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_ATTRIBUTE_NAME;

/**
 * Created by YinLin on 2015/9/6.
 * Description : 商品属性
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PRODUCT_ATTRIBUTE_NAME)
public class ProductAttribute {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //所属商品
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
    //所属属性
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="attr_id")
    private Attribute attribute;
    //属性名字
    @Column(name = "attr_name", length = 26)
    private String name;
    //属性值
    @Column(name = "attr_value",length = 26)
    private String value;
    //属性值类型
    /**性别要求**/
    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private ValueType type;
    //附加价
    @Column(name = "attr_add_fee")
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
