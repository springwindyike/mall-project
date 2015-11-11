package com.ishare.mall.manage.form;


/**
 * Created by liaochenglei on 2015/11/9.
 */
public class ProductSearchForm {
	
	private String name;
	
	private Integer id;
	
	private Boolean visible;
	
	private String brandName;
	
	private String productTypeName;

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

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	
}
