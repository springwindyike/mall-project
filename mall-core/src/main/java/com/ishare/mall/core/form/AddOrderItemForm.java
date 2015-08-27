package com.ishare.mall.core.form;

import com.ishare.mall.core.status.OrderItemState;

public class AddOrderItemForm {
    private Integer id;
    /* 产品名称 */
    private String productName;
    /* 产品id */
    private Integer productId;
    /* 产品销售价 */
    private Float productPrice = 0f;
    /* 购买数量 */
    private Integer amount = 1;
    /* 产品样式 */
    private String styleName;
    /* 产品样式ID */
    private Integer styleId;
    /* 产品图片地址 */
    private String imageUrl;
    /* 产品状态 */
    private OrderItemState state;
    /* 购买者*/
    private String createBy;
    /*退换货标记*/
    private String exchangeOrBack;
    /* 所属订单 */
    private String orderId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	public OrderItemState getState() {
		return state;
	}
	public void setState(OrderItemState state) {
		this.state = state;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
