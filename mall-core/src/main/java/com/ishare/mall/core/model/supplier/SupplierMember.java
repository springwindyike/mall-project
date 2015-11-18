package com.ishare.mall.core.model.supplier;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_SUPPLIER_MEMBER_NAME;

/**
 * Created by YinLin on 2015/11/18.
 * Description : 供应商操作人员表 主要用于供应商操作
 * Version 1.0
 */
@Entity
@Table(name = TABLE_SUPPLIER_MEMBER_NAME)
public class SupplierMember extends BaseEntity {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//自增ID

    @Column(name = "supplier_member_name", length = 128, nullable = false)
    private String name;//操作人员名字

    @Column(name = "supplier_member_id", length = 128, nullable = false)
    private Integer memberId;//对应的Member

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time", length = 20, nullable = false)
    private Date lastLoginTime;//最后登录时间

    @Column(name = "last_login_ip", length = 64, nullable = false)
    private String lastLoginIp;//最后登录IP

    @Column(name = "supplier_member_is_admin", length = 128, nullable = false)
    private Integer isAdmin;//是否是管理员 0 不是 1 是

    @Column(name = "supplier_member_quick_link", length = 1024, nullable = false)
    private String quickLink;//快速通道

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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getQuickLink() {
        return quickLink;
    }

    public void setQuickLink(String quickLink) {
        this.quickLink = quickLink;
    }
}
