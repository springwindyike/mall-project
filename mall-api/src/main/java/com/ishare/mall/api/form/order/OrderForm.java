package com.ishare.mall.api.form.order;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.enumeration.DeliverWay;
import com.ishare.mall.common.base.enumeration.PaymentWay;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by YinLin on 2015/9/16.
 * Description : 订单form
 * Version 1.0
 */
public class OrderForm {

    @NotEmpty(message = "token不能为空")
    private String access_token;

//    @NotNull(message = "商品ID不能为空")
//    private Integer[] productId;
    @NotNull(message = "商品ID不能为空")
    private List<OrderItemDetailDTO> orderItemDetailDTOList;
    private Long [] attributeIds;

    private Long styleId;

    /* 收货人姓名 */
    @NotEmpty(message = "收货人姓名不能为空")
    private String recipients;

    //国家
    @NotEmpty(message = "收货地址国家不能为空")
    private String country;

    //省
    @NotEmpty(message = "收货地址省份不能为空")
    private String province;

    //市
    @NotEmpty(message = "收货地址城市不能为空")
    private String city;

    //县 区
    @NotEmpty(message = "收货地址县区不能为空")
    private String district;

    //详细街道
    @NotEmpty(message = "收货详细地址不能为空")
    private String detail;

    /* 电子邮箱 */
    //@NotEmpty(message = "收货人电子邮件不能为空")
    private String email;

    /* 电话 */
    @NotEmpty(message = "收货人电话不能为空")
    private String tel;

    /* 手机 */
    @NotEmpty(message = "收货人手机不能为空")
    private String mobile;

    /**购买数量**/
    @NotNull(message = "购买数量不能为空")
    private Integer amount;

    //快递方式
    @NotNull(message = "快递方式不能为空")
    private DeliverWay deliverWay;

    //支付方式
    @NotNull(message = "支付方式不能为空")
    private PaymentWay paymentWay;


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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
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

    public PaymentWay getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }

//    public Integer[] getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Integer[] productId) {
//        this.productId = productId;
//    }


    public List<OrderItemDetailDTO> getOrderItemDetailDTOList() {
        return orderItemDetailDTOList;
    }

    public void setOrderItemDetailDTOList(List<OrderItemDetailDTO> orderItemDetailDTOList) {
        this.orderItemDetailDTOList = orderItemDetailDTOList;
    }
}
