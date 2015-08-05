package com.ishare.mall.manage.form;

/**
 * Created by dongqi on 15/7/30.
 */
public class CustomerForm {

    private Long id;
    private String account = null; // 账号名称，登录用
    private String password = null; // 密码

    private String realName = null; //实名
    private byte gender; // 性别，0-男，1-女
    private String email = null; //邮箱
    private String mobile = null; //电话
    private Boolean lock; // 是否锁定

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return "CustomerForm{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", lock=" + lock +
                '}';
    }
}
