package com.ishare.mall.api.form;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import org.springframework.beans.BeanUtils;

/**
 * Created by YinLin on 2015/9/16.
 * Description : 订单form
 * Version 1.0
 */
public class OrderForm {

    private String token;

    private Long productId;

    private Long [] attributeIds;

    private Long styleId;
    /* 收货人姓名 */
    private String recipients;
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
    /* 电子邮箱 */
    private String email;
    /* 电话 */
    private String tel;
    /* 手机 */
    private String mobile;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long[] getAttributeIds() {
        return attributeIds;
    }

    public void setAttributeIds(Long[] attributeIds) {
        this.attributeIds = attributeIds;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ExchangeDTO toExchangeDTO() {
        ExchangeDTO exchangeDTO = new ExchangeDTO();
        BeanUtils.copyProperties(this, exchangeDTO);
       return exchangeDTO;
    }

}
