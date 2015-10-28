package com.ishare.mall.core.model.information;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_TYPE_BRAND_NAME;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.product.ProductType;

/**
 * Created by liaochenglei on 2015/10/28.
 * Description:品牌分类
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PRODUCT_TYPE_BRAND_NAME)
public class ProductTypeBrand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productType_id")
    private ProductType productType;


    public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
