package com.ishare.mall.core.model.information;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_BRAND_NAME;

/**
 * Created by YinLin on 2015/7/31.
 * Description:品牌实体
 * Version 1.0
 */
@Entity(name = TABLE_BRAND_NAME)
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**品牌名字**/
    @Column(name = "brand_name",length = 50)
    private String name;
    /**品牌logo地址**/
    @Column(name = "brand_logo_url",length = 200)
    private String logoUrl;
    //国家
    @Column(name = "brand_address_country",length = 10)
    private String country;
    //省
    @Column(name = "brand_address_province",length = 21)
    private String province;
    //市
    @Column(name = "brand_address_city",length = 15)
    private String city;
    //县 区
    @Column(name = "brand_address_district",length = 15)
    private String district;
    //详细街道
    @Column(name = "brand_address_detail",length = 50)
    private String detail;
    /**
     * 一对多所有的该品牌下所有的商品
     */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "brand")
    private Set<Product> products = Sets.newConcurrentHashSet();

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
