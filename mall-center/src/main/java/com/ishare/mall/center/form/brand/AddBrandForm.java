package com.ishare.mall.center.form.brand;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by liaochenglei on 2015/10/27.
 */
public class AddBrandForm {
	
	  @NotEmpty(message = "名称不能为空")
	  private String name;
	  @NotEmpty(message = "国家不能为空")
	  private String country;
	  @NotEmpty(message = "省份不能为空")
	  private String province;
	  @NotEmpty(message = "城市不能为空")
	  private String city;
	  @NotEmpty(message = "区县不能为空")
	  private String district;
	  @NotEmpty(message = "详细信息不能为空")
	  private String detail;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
