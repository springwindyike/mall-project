package com.ishare.mall.core.model.product;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_REVIEW_COVER;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ishare.mall.core.model.base.BaseEntity;

/**
 * Created by liaochenglei on 2015/11/12.
 * Description: 用户对应的商品相册
 * Version 1.0
 */
@Table(name = TABLE_PRODUCT_REVIEW_COVER)
@Entity
public class ProductReviewCover extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    //一个产品对应每个图片的url
    @Column(name = "products_url",length = 100)
    private String imageurl;
    
    //产品颜色
    @Column(name = "color_id",length = 100)
    private String colorId;
    
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "common_id")
    private ProductCommon productCOmmon;
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public ProductCommon getProductCOmmon() {
		return productCOmmon;
	}

	public void setProductCOmmon(ProductCommon productCOmmon) {
		this.productCOmmon = productCOmmon;
	}
    
}
