package com.ishare.mall.center.form.product;

import org.hibernate.validator.constraints.NotEmpty;


public class AddProductForm {
	
	@NotEmpty(message = "产品标题不能为空")
    //产品名称 
    private String productName;
    //物品类型 便于search 10010010001
	@NotEmpty(message = "产品类型code不能为空")
    private String typeCode;
    //商品进价
@NotEmpty(message = "产品进价不能为空")
    private Float basePrice;
    //市场价格
	@NotEmpty(message = "市场价格不能为空")
    private Float marketPrice;
  
    //描述
	/*@NotEmpty(message = "产品描述不能为空")*/
    private String description;
 
    //购买须知
	/*@NotEmpty(message = "产品购买须知不能为空")*/
    private String buyExplain;
	
	/*@NotEmpty(message = "库存不能为空")*/
    private Integer inventory;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}

	public Float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuyExplain() {
		return buyExplain;
	}

	public void setBuyExplain(String buyExplain) {
		this.buyExplain = buyExplain;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	
}