package com.ishare.mall.common.base.dto.information;

/**
 * Created by YinLin on 2015/8/7.
 * Description:
 * Version 1.0
 */
public class BrandDetailDTO {

    private Integer id;
    /**品牌名字**/
    private String name;
    /**品牌logo地址**/
    private String logoUrl;
    //国家
    private String country;
    //省
    private String province;
    //市
    private String city;
    //县 区
    private String district;
    //详细街道
    private String detail;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
