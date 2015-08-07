package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by YinLin on 2015/8/7.
 * Description: 商品列表API DTO 只要是查询或者List全用这个DTO
 * Version 1.0
 */
public class ProductListDTO implements BaseObject {
    //商品ID
    private Integer id;
    //商品名字
    private String name;
    //商品价格
    private Float sellPrice;
    //默认样式图片
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
