package com.ishare.mall.core.service.manageuser.impl;

import com.ishare.mall.common.base.exception.manageuser.ManageUserServiceException;
import com.ishare.mall.common.base.exception.member.MemberServiceException;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.repository.manageuser.ManageUserRepository;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.manageuser.ManageUserService;
import com.ishare.mall.core.service.member.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class ManageUserServiceImpl implements ManageUserService {

	private static final Logger log = LoggerFactory.getLogger(ManageUserServiceImpl.class);

	@Autowired
	private ManageUserRepository manageUserRepository;


	@Override
	public ManageUser findByUsername(String username) throws ManageUserServiceException {
		List<ManageUser> manageUsers = manageUserRepository.findByUsername(username);
		return manageUsers != null && manageUsers.size() > 0 ? manageUsers.get(0) : null;
	}

}
