package com.ishare.mall.api.form.order;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.enumeration.DeliverWay;
import com.ishare.mall.common.base.enumeration.PaymentWay;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

/**
 * Created by YinLin on 2015/9/16.
 * Description : 订单form
 * Version 1.0
 */
public class OrderForm {

    @NotEmpty
    private String token;

    @NotEmpty
    private Integer productId;

    private Long [] attributeIds;
    @NotEmpty
    private Long styleId;

    /* 收货人姓名 */
    @NotEmpty
    private String recipients;

    //国家
    @NotEmpty
    private String country;

    //省
    @NotEmpty
    private String province;

    //市
    @NotEmpty
    private String city;

    //县 区
    @NotEmpty
    private String district;

    //详细街道
    @NotEmpty
    private String detail;

    /* 电子邮箱 */
    @NotEmpty
    private String email;

    /* 电话 */
    @NotEmpty
    private String tel;

    /* 手机 */
    @NotEmpty
    private String mobile;

    /**购买数量**/
    @NotEmpty
    private Integer amount;

    //快递方式
    @NotEmpty
    private DeliverWay deliverWay;

    //支付方式
    @NotEmpty
    private PaymentWay paymentWay;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public DeliverWay getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(DeliverWay deliverWay) {
        this.deliverWay = deliverWay;
    }
}
