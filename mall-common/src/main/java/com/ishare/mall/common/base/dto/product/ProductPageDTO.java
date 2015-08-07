package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by YinLin on 2015/8/7.
 * Description: 商品列表API
 * Version 1.0
 */
public class ProductPageDTO implements BaseObject {
    private Integer id;
    private String name;
    private Float sellPrice;
    private String imageUrl;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
