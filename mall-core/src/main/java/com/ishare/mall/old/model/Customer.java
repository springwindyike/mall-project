package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongqi on 15/7/12.
 *
 * 顾客
 */
@Entity
@Table(name = "es_customer")
public class Customer extends AbstractEntity {

    public static final byte MALE = 0;
    public static final byte FEMALE = 1;
    public static final byte UNKOWN = 2;

    private String account; // 账号名称，登录用
    @Column(name = "passwd")
    private String password; // 密码
    private int salt; // 加密用

    @Column(name = "username")
    private String realName; //实名
    private byte gender; // 性别，0-男，1-女
    private String email; //邮箱
    private String mobile; //电话
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date(); //注册时间
    @Type(type = "yes_no")
    @Column(name = "is_lock")
    private Boolean lock = Boolean.TRUE; // 是否锁定
    
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

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + salt +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime=" + createTime +
                ", lock=" + lock +
                '}';
    }
}
