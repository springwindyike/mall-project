package com.ishare.mall.core.model.order;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.status.DeliverWay;
import com.ishare.mall.core.status.Gender;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_DELIVER_INFO_NAME;

/**
 * 收货人信息
 * @author yinlin
 *
 */
@Entity(name = TABLE_ORDER_DELIVER_INFO_NAME)
public class OrderDeliverInfo extends BaseEntity {
	@Id @GeneratedValue
	private Integer id;
	/* 收货人姓名 */
	@Column(length = 25, nullable = false, name = "deliver_recipients")
	private String recipients;
	//国家
	@Column(name = "deliver_address_country")
	private String country;
	//省
	@Column(name = "deliver_address_province")
	private String province;
	//市
	@Column(name = "deliver_address_city")
	private String city;
	//县 区
	@Column(name = "deliver_address_district")
	private String district;
	//详细街道
	@Column(name = "deliver_address_detail")
	private String detail;
	/* 电子邮箱 */
	@Column(length = 20, name = "deliver_email")
	private String email;
	/* 邮编 */
	@Column(length=6, name = "deliver_postal_code")
	private String postalCode;
	/* 座机 */
	@Column(length = 18, name = "deliver_tel")
	private String tel;
	/* 手机 */
	@Column(length = 11, name = "deliver_mobile")
	private String mobile;
	/* 性别 */
	@Enumerated(EnumType.STRING)
	@Column(length = 5, nullable = false)
	private Gender gender = Gender.MAN;
	/**配送方式**/
	@Enumerated(EnumType.STRING)
	@Column(length = 23, nullable = false)
	private DeliverWay deliverWay;
	/**时间要求**/
	@Column(length = 30)
	private String requirement;
	/**所属订单**/
	@OneToOne(mappedBy = "orderDeliverInfo", cascade = CascadeType.REFRESH)
	private Order order;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public DeliverWay getDeliverWay() {
		return deliverWay;
	}
	public void setDeliverWay(DeliverWay deliverWay) {
		this.deliverWay = deliverWay;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDeliverInfo other = (OrderDeliverInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
