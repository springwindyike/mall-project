package com.ishare.mall.center.form.register;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ZhangZhaoxin on 2015/9/8.
 * Description :
 * Version 1.0
 */
public class RegisterForm {

	@NotEmpty(message = "账号不能为空")
	private String account;
	@NotEmpty(message = "密码不能为空")
	private String password;
	@NotEmpty(message = "确认密码不能为空")
	private String repassword;
	@NotEmpty(message = "邮箱不能为空")
	private String email;
	@NotEmpty(message = "性别不能为空")
	private String sex;
	@NotEmpty(message = "公司名称不能为空")
	private String channel;
	private String city;
	private String note;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
