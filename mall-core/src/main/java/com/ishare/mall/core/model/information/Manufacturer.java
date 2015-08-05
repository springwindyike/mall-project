package com.ishare.mall.core.model.information;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_MANUFACTURER_NAME;

/**
 * Created by YinLin on 2015/8/3.
 * Description:厂商
 * Version 1.0
 */
@Entity(name=TABLE_MANUFACTURER_NAME)
public class Manufacturer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**厂商名字**/
    @Column(name = "fact_name")
    private String name;
    //国家
    @Column(name = "fact_address_country")
    private String country;
    //省
    @Column(name = "fact_address_province")
    private String province;
    //市
    @Column(name = "fact_address_city")
    private String city;
    //县 区
    @Column(name = "fact_address_district")
    private String district;
    //详细街道
    @Column(name = "fact_address_detail")
    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
