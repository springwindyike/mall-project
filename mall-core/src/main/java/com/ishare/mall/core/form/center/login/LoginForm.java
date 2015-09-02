package com.ishare.mall.core.form.center.login;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
public class LoginForm {
    @NotEmpty(message = "账号不能为空")
    private String account;
    @NotEmpty(message = "密码不能为空")
    private String password;

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
}
