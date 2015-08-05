package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;

import javax.persistence.*;

/**
 * Created by dongqi on 15/7/12.
 * <p>
 * 图片
 */
@Entity
@Table(name = "es_image")
public class Image extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "img_name")
    private String name;//图片名字
    private String url;//图片地址

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
