package com.ishare.mall.center.form.login;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Zhangzhaoxin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public class LoginForm {
	
    @NotEmpty(message = "账号不能为空")
    private String account;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;
    
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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
    
}
