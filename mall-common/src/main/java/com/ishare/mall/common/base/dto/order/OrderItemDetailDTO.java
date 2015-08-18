package com.ishare.mall.common.base.dto.order;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.object.BaseObject;

/**
 * Created by liaochenglei on 2015/8/18.
 * Description: productDTO
 * Version 1.0
 */
@JsonAutoDetect
public class OrderItemDetailDTO implements BaseObject {
	
	/* 产品ID */
	private Integer productId;
   
	/* 产品名称 */
    private String productName;
    
    /* 产品销售价 */
    private Float productPrice;
    
    /* 购买数量 */
    private Integer amount;
    
    /* 产品样式 */
    private String styleName;
    
    /* 产品样式ID */
    private Integer styleId;
    
    /* 产品图片地址 */
    private String imageUrl;
    
    /* 产品状态 */
    private String state;
    
    /*退换货标记*/
    private String exchangeOrBack;
    
    /* 所属订单 */
    private String orderId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExchangeOrBack() {
		return exchangeOrBack;
	}

	public void setExchangeOrBack(String exchangeOrBack) {
		this.exchangeOrBack = exchangeOrBack;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
}
