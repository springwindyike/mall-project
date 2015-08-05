package com.ishare.mall.core.model.order;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.status.Gender;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_CONTACT_INFO_NAME;

/**
 * 订购者联系信息
 *
 */
@Entity(name = TABLE_ORDER_CONTACT_INFO_NAME)
public class OrderContactInfo extends BaseEntity {
	/* 购买人姓名 */
	@Column(length = 25, nullable = false, name = "order_buyer_name")
	private String buyerName;
	//国家
	@Column(name = "order_buyer_address_country")
	private String country;
	//省
	@Column(name = "order_buyer_address_province")
	private String province;
	//市
	@Column(name = "order_buyer_address_city")
	private String city;
	//县 区
	@Column(name = "order_buyer_address_district")
	private String district;
	//详细街道
	@Column(name = "order_buyer_address_detail")
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
	@OneToOne(cascade = CascadeType.REFRESH, mappedBy = "orderContactInfo")
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
}
