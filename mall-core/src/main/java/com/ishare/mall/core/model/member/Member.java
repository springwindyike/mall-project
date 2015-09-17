package com.ishare.mall.core.model.member;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.status.MemberType;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_MEMBER_NAME;

/**
 * Created by YinLin on 2015/8/3.
 * Description:成员
 * Version 1.0
 */
@Entity
@Table(name = TABLE_MEMBER_NAME)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**平台登录账号**/
    @Column(name = "member_account",length = 50)
    private String account;
    /**第三方手机号**/
    @Column(name = "member_mobile", length = 11)
    private String mobile;
    /**平台登录密码**/
    @Column(name = "member_password",length = 50)
    private String password;
    @Column(name = "member_salt", length = 32)
    private String salt;

    /**所属渠道**/
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;
    /**成员名称**/
    @Column(name = "member_name",length = 27)
    private String name;
    /**创建时间**/
    @Column(name = "member_create_time",length = 20)
    private Date createTime;
    /**更新时间**/
    @Column(name = "member_update_time",length = 20)
    private Date updateTime;
    /**创建者**/
    @Column(name = "member_create_by",length = 50)
    private String createBy;
    /**更新者**/
    @Column(name = "member_update_by",length = 50)
    private String updateBy;
    /**是否可用**/
    @Column(name = "member_use",length = 5)
    private boolean use;
    /**性别要求**/
    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private Gender sex = Gender.MAN;
    /**角色类型 管理员（创建者账号为管理员 管理员可以分配渠道管理外的全权限 其他为（成员 店员））**/
    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private MemberType memberType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return account + salt;
    }
}
