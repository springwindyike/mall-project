package com.ishare.mall.core.model.manage;

import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.enumeration.MemberType;
import com.ishare.mall.common.base.enumeration.UserType;
import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.information.Channel;

import javax.persistence.*;

import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_MANAGE_USER_NAME;

/**
 * Created by YinLin on 2015/10/16.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_MANAGE_USER_NAME)
public class ManageUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**平台登录账号**/
    @Column(name = "user_username",length = 50)
    private String username;
    /**平台登录密码**/
    @Column(name = "user_password",length = 50)
    private String password;
    @Column(name = "user_salt", length = 32)
    private String salt;
    /**所属渠道**/
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;
    /**成员名称**/
    @Column(name = "user_name",length = 27)
    private String name = "";
    /**创建时间**/
    @Column(name = "user_create_time",length = 20)
    private Date createTime = new Date();
    /**更新时间**/
    @Column(name = "user_update_time",length = 20)
    private Date updateTime = new Date();
    /**创建者**/
    @Column(name = "user_create_by",length = 50)
    private String createBy = "";
    /**更新者**/
    @Column(name = "user_update_by",length = 50)
    private String updateBy = "";
    /**是否可用**/
    @Column(name = "user_use",length = 5)
    private boolean use = true;
    /**性别要求**/
    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private Gender sex = Gender.MAN;
    /**角色类型 管理员（创建者账号为管理员 管理员可以分配渠道管理外的全权限 其他为（成员 店员））**/
    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private UserType userType;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public boolean isUse() {
        return use;
    }

    public Gender getSex() {
        return sex;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
