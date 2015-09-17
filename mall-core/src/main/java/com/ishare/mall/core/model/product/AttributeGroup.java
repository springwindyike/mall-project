package com.ishare.mall.core.model.product;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ATTRIBUTE_GROUT_NAME;

/**
 * Created by YinLin on 2015/9/6.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ATTRIBUTE_GROUT_NAME)
public class AttributeGroup {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
