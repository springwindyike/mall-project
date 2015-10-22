package com.ishare.mall.common.base.dto.channel;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.dto.member.MemberDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.object.BaseObject;

import java.util.Date;
import java.util.List;

@XmlRootElement
@JsonAutoDetect
public class ChannelDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer channelId;
	
	public float totalMoney;

	private Integer id;
	private String name;
	private String appId;
	private String appSecret;
	private String token;
	private String country;
	private String province;
	private String city;
	private String district;
	private String detail;
	private String phone;
	private String code;
	private String linkName;
	private String linkPhone;
	private String businessScale;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm",timezone="GMT+8")
	private Date createTime;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm",timezone="GMT+8")
	private Date updateTime;
	private String createBy;
	private String updateBy;
	private String industry;
	private List<MemberDetailDTO> memberDetailDTOList;
	private List<ProductDetailDTO> productDetailDTOList;
	private List<OrderDetailDTO> orderDetailDTOList;
	private int offset;
	private int limit;

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getBusinessScale() {
		return businessScale;
	}

	public void setBusinessScale(String businessScale) {
		this.businessScale = businessScale;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public List<MemberDetailDTO> getMemberDetailDTOList() {
		return memberDetailDTOList;
	}

	public void setMemberDetailDTOList(List<MemberDetailDTO> memberDetailDTOList) {
		this.memberDetailDTOList = memberDetailDTOList;
	}

	public List<ProductDetailDTO> getProductDetailDTOList() {
		return productDetailDTOList;
	}

	public void setProductDetailDTOList(List<ProductDetailDTO> productDetailDTOList) {
		this.productDetailDTOList = productDetailDTOList;
	}

	public List<OrderDetailDTO> getOrderDetailDTOList() {
		return orderDetailDTOList;
	}

	public void setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
		this.orderDetailDTOList = orderDetailDTOList;
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
