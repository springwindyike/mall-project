package com.ishare.mall.common.base.dto.order;

import com.ishare.mall.common.base.enumeration.DeliverWay;
import com.ishare.mall.common.base.enumeration.PaymentWay;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/16.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ExchangeDTO {

    private String clientId;

    private String account;

    private Integer productId;

    private Long [] attributeIds;

    private Long styleId;
    private String recipients;
    private String country;
    private String province;
    private String city;
    private String district;
    private String detail;
    private String email;
    private String tel;

    private String mobile;
    /**购买数量**/
    private Integer amount;

    private String requirement;

    //快递方式
    private DeliverWay deliverWay;

    //支付方式
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public DeliverWay getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(DeliverWay deliverWay) {
        this.deliverWay = deliverWay;
    }

    public PaymentWay getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }
}
