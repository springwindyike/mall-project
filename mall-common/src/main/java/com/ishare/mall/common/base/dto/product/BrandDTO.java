package com.ishare.mall.common.base.dto.product;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

/**
 * Created by liaochenglei on 2015/10/26.
 * Description: productDTO
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class BrandDTO extends GenericDTO {
   
	private static final long serialVersionUID = 1L;

	//品牌的id
	 private Integer id;
	 //品牌的名称
	  private String name;
	  //品牌的logo
	  private String logoUrl;
	  //品牌的国家
	  private String country;
	  //品牌的省份
	  private String province;
	  //品牌的城市
	  private String city;
	  //品牌的区县
	  private String district;
	  //品牌的详细信息
	  private String detail;
	  
	  private int offset;
	    
	    private int limit;
	    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
