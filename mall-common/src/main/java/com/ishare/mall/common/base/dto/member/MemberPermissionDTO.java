package com.ishare.mall.common.base.dto.member;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.dto.permission.PermissionDTO;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public class MemberPermissionDTO extends GenericDTO {
	List<PermissionDTO> permissionDTOs;
	private Integer id;
	private String account;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
