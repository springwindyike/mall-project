package com.ishare.mall.common.base.dto.information;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by YinLin on 2015/8/7.
 * Description:品牌列表DTO
 * Version 1.0
 */
public class BrandListDTO implements BaseObject {
    //ID
    private Integer id;
    //名字
    private String name;
    //logo图片地址
    private String logoUrl;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
