package com.ishare.mall.core.model.cms;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ishare.mall.core.model.base.BaseEntity;
import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_AB_NAME;
@Entity
@Table(name=TABLE_AB_NAME)
public class AB extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name",length = 10)
	private String name;
	@Column(name = "password",length = 10)
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
