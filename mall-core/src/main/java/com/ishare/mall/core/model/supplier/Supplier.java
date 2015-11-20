package com.ishare.mall.core.model.supplier;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_SUPPLIER_NAME;

/**
 * Created by YinLin on 2015/11/18.
 * Description : 供应商表 主要用于供应商模块
 * Version 1.0
 */
@Entity
@Table(name = TABLE_SUPPLIER_NAME)
public class Supplier extends BaseEntity {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_operator_name", length = 32, nullable = false)
    private String operatorName;//运营者姓名

    @Column(name = "supplier_operator_tel", length = 11, nullable = false)
    private String tel;//运营者手机

    @Column(name = "supplier_company_name", length = 64, nullable = false)
    private String companyName;//公司名字

    //省
    @Column(name = "supplier_address_province", length = 21)
    private String province;

    //市
    @Column(name = "supplier_address_city", length = 15)
    private String city;

    //县 区
    @Column(name = "supplier_address_district", length = 15)
    private String district;
    //详细街道
    @Column(name = "supplier_address_detail", length = 50)
    private String detail;

    @Column(name = "supplier_phone", length = 11, nullable = false)
    private String phone;//电话

    @Column(name = "supplier_licence", length = 64, nullable = false)
    private String licence;//营业执照号码

    @Column(name = "supplier_licence_path", length = 256, nullable = false)
    private String licencePath;//电子版

    @Column(name = "supplier_organization", length = 64, nullable = false)
    private String organization;//组织机构代码

    @Column(name = "supplier_organization_path", length = 256, nullable = false)
    private String organizationPath;//组织机构代码电子版

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "supplier_create_time",length = 20)
    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLicencePath() {
        return licencePath;
    }

    public void setLicencePath(String licencePath) {
        this.licencePath = licencePath;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationPath() {
        return organizationPath;
    }

    public void setOrganizationPath(String organizationPath) {
        this.organizationPath = organizationPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
