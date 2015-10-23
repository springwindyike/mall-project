package com.ishare.mall.core.service.manageuser;

import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.enumeration.MemberType;
import com.ishare.mall.common.base.exception.manageuser.ManageUserServiceException;
import com.ishare.mall.common.base.exception.member.MemberServiceException;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface ManageUserService {

	interface Default {
		//默认创建
		String DEFAULT_PASSWORD 	= "default";
		Gender DEFAULT_SEX			= Gender.MAN;
		MemberType DEFAULT_TYPE		= MemberType.CLERK;
	}

	/**
	 *
	 * 根据account查询
	 *
	 * @param username
	 * @return
	 */
	ManageUser findByUsername(String username)throws ManageUserServiceException;

	/**
	 * 通过账号ID查找
	 * @param id
	 * @return
	 */
	ManageUser findOne(Integer id);
	/**
	 * 保存新的manageuser
	 * @param manageUser
	 */
	void saveManageUser(ManageUser manageUser)throws ManageUserServiceException;

	/**
	 * 更新用户信息
	 * @param manageUser
	 */
	void update(ManageUser manageUser)throws ManageUserServiceException;
}
