package com.ishare.mall.common.base.dto.order;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Created by YinLin on 2015/9/16.
 * Description :
 * Version 1.0
 */
@JsonAutoDetect
public class ExchangeDTO {

    private String clientId;

    private String account;

    private Integer productId;

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
    /* 座机 */
    private String tel;
    /* 手机 */
    private String mobile;
    /**购买数量**/
    private Integer num;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
