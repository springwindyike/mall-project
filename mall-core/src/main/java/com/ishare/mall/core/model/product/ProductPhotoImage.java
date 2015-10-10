package com.ishare.mall.core.model.product;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PRODUCT_PHOTO_IMAGE_NAME;

/**
 * Created by YinLin on 2015/10/10.
 * Description : 商品相册图片
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PRODUCT_PHOTO_IMAGE_NAME)
public class ProductPhotoImage {

    @Id @GeneratedValue
    private Integer id;

    @Column(name = "product_photo_img_url", length = 512)
    private String url;

    /**所属产品**/
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

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
