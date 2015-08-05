package com.ishare.mall.core.model.order;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_ITEM_NAME;

/**
 * Created by YinLin on 2015/7/31.
 * Description:
 * Version 1.0
 */
@Entity(name = TABLE_ORDER_ITEM_NAME)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /* 产品名称 */
    @Column(length = 50,nullable = false, name = "item_product_name")
    private String productName;
    /* 产品id */
    @Column(nullable = false, name = "item_product_id")
    private Integer productId;
    /* 产品销售价 */
    @Column(nullable = false, name = "item_product_price")
    private Float productPrice = 0f;
    /* 购买数量 */
    @Column(nullable = false, name = "item_product_amount")
    private Integer amount = 1;
    /* 产品样式 */
    @Column(length = 30, nullable = false, name = "item_product_style_name")
    private String styleName;
    /* 产品样式ID */
    @Column(nullable = false, name = "item_product_style_id")
    private Integer styleId;
    /** 产品图片地址 */
    @Column(nullable = false, name = "item_product_image_url")
    private String imageUrl;
    /* 所属订单 */
    @ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE}, optional=false)
    @JoinColumn(name="order_id")
    private Order order;
    public OrderItem(){}
    public OrderItem(String productName, Integer productId, Float productPrice,
                     Integer amount, String styleName, Integer styleId) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.amount = amount;
        this.styleName = styleName;
        this.styleId = styleId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Float getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getStyleName() {
        return styleName;
    }
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
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
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
