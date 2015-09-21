package com.ishare.mall.center.form.member;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wanghao on 2015/9/21.
 */
public class MemberUpdatePasswordForm {
    private String password;
    private String repassword;
    private String account;

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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
