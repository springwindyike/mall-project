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
	@NotEmpty(message = "产品描述不能为空")
    private String description;
	
	 //描述
		@NotEmpty(message = "分类名称不能为空")
	    private String typeName;
	
	 public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		//分类id
		@NotEmpty(message = "产品分类id")
	    private Integer typeId;
 //品牌的id
		
		@NotEmpty(message = "品牌的id")
	    private Integer brandId;
		
/*    //购买须知
	@NotEmpty(message = "产品购买须知不能为空")
    private String buyExplain;*/
	
	@NotEmpty(message = "库存不能为空")
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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
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

	
/*	public String getBuyExplain() {
		return buyExplain;
	}

	public void setBuyExplain(String buyExplain) {
		this.buyExplain = buyExplain;
	}*/

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
}
