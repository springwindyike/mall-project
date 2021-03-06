package com.ishare.mall.core.model.information;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.product.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_CHANNEL_NAME;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 渠道 渠道包括供应商和销售商，可以只供货也可以只销售，也可以销售和供货
 *              渠道需要申请，平台审核，通过后申请管理账号，第一次申请的登录账号为该渠道的管理员账号。
 *              可以通过该账号管理渠道所有信息，包括店员管理
 * Version 1.0
 */
@Entity
@Table(name = TABLE_CHANNEL_NAME)
public class Channel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**供货商名字**/
    @Column(name = "channel_name",length = 30)
    private String name;

    /**应用ID**/
    @Column(name = "channel_app_id",length = 50)
    private String appId;
    /**应用秘钥**/
    @Column(name = "channel_app_secret",length = 50)
    private String appSecret;
    /**token**/
    @Column(name = "channel_token",length =50)
    private String token;
    //国家
    @Column(name = "channel_address_country",length = 10)
    private String country;
    //省
    @Column(name = "channel_address_province",length = 21)
    private String province;
    //市
    @Column(name = "channel_address_city",length = 15)
    private String city;
    //县 区
    @Column(name = "channel_address_district",length = 15)
    private String district;
    //详细街道
    @Column(name = "channel_address_detail",length = 50)
    private String detail;
    /**联系电话**/
    @Column(name = "channel_phone",length = 11)
    private String phone;
    /**邮政编码**/
    @Column(name = "channel_code",length = 6)
    private String code;
    /**联系人姓名**/
    @Column(name = "channel_link_name",length = 15)
    private String linkName;
    /**联系人电话**/
    @Column(name = "channel_link_phone",length = 11)
    private String linkPhone;
    /**公司营业规模**/
    @Column(name = "channel_business_scale",length = 7)
    private String businessScale;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "channel_create_time",length = 20)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "channel_update_time",length = 20)
    private Date updateTime;
    /**创建者**/
    @Column(name = "member_create_by",length = 15)
    private String createBy;
    /**更新者**/
    @Column(name = "member_update_by",length = 15)
    private String updateBy;
    /**经营类别**/
    @Column(name = "channel_industry",length = 51)
    private String industry;
    /**是否启用**/
    @Column(name = "channel_visible",length = 5)
    private Boolean visible = true;
    /**省份代码**/
    @Column(name = "channel_province_code",length = 6)
    private String provinceCode;
    /**市代码**/
    @Column(name = "channel_city_code",length = 6)
    private String cityCode;
    /**区县代码**/
    @Column(name = "channel_district_code",length = 6)
    private String districtCode;

    /**
     * 一对多该供应商下所有的成员
     */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<Member> members = Sets.newConcurrentHashSet();

    /**
     * 一对多该供应商下所有的产品
     */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<Product> products = Sets.newConcurrentHashSet();
    
    /* 拥有订单 */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "channel", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<Order>();
    
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
}
