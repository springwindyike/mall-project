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
 * Description: 商品信息
 * Version 1.0
 */
@Table(name = TABLE_PRODUCT_REVIEW_COVER)
@Entity
public class ProductReviewCover extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //一个产品对应图片公共的id
    @Column(name = "product_common_id",length = 100)
    private String commonId;
    //一个产品对应每个图片的url
    @Column(name = "product_single_url",length = 100)
    private String url;
    
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_cover_id")
    private Product product;
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getCommonId() {
		return commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    
}
