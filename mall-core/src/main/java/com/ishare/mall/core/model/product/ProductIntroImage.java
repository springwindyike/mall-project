package com.ishare.mall.core.model.product;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_INTRO_IMAGE_NAME;

/**
 * Created by YinLin on 2015/10/10.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PRODUCT_INTRO_IMAGE_NAME)
public class ProductIntroImage extends BaseEntity {
    @Id @GeneratedValue
    private Integer id;
    @Column(name = "product_intro_img_url", length = 512)
    private String url;
    private Product product;
    /**所属产品**/
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
