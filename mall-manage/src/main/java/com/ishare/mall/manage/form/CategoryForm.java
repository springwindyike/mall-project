package com.ishare.mall.manage.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by liaochenglei on 15/10/23.
 */
public class CategoryForm {
	
	  @NotEmpty(message = "parentId不能为空")
	  private Integer parentId;
	
	  @NotEmpty(message = "名称不能为空")
	  private String name;

	  @NotEmpty(message = "code不能为空")
	  private String code;

	  @NotEmpty(message = "level不能为空")
	  private Integer level;

	  @NotEmpty(message = "说明不能为空")
	  private String note;
	
public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
