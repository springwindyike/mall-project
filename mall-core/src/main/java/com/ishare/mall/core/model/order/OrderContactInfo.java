package com.ishare.mall.core.model.order;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.status.Gender;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_CONTACT_INFO_NAME;

/**
 * 订购者联系信息
 *
 */
@Entity
@Table(name = TABLE_ORDER_CONTACT_INFO_NAME)
public class OrderContactInfo extends BaseEntity {
	@Id @GeneratedValue
	private Integer id;
	/* 购买人姓名 */
	@Column(length = 25, nullable = false, name = "order_buyer_name")
	private String buyerName;
	//国家
	@Column(name = "order_buyer_address_country",length = 10)
	private String country;
	//省
	@Column(name = "order_buyer_address_province",length = 21)
	private String province;
	//市
	@Column(name = "order_buyer_address_city",length = 15)
	private String city;
	//县 区
	@Column(name = "order_buyer_address_district",length = 15)
	private String district;
	//详细街道
	@Column(name = "order_buyer_address_detail",length = 50)
	private String detail;
	/* 电子邮箱 */
	@Column(length = 40, name = "order_buyer_email")
	private String email;
	/* 邮编 */
	@Column(length = 6, name = "order_buyer_postal_code")
	private String postalCode;
	/* 座机 */
	@Column(length = 18, name = "order_buyer_tel")
	private String tel;
	/* 手机 */
	@Column(length = 11, name = "order_buyer_mobile")
	private String mobile;
	/* 性别 */
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.MAN;
	/**所属订单**/
	@OneToOne(cascade = CascadeType.REFRESH, mappedBy = "orderContactInfo", fetch = FetchType.LAZY)
	private Order order;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
