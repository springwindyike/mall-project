package com.ishare.mall.core.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.member.PasswordHelper;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private PasswordHelper passwordHelper;

	@Override
	public Member findOne(Integer id) {
		return memberRepository.findOne(id);
	}

	@Override
	public Member checkAndCreateByAccount(String account, Integer channelId) {
		Member member = this.findByAccount(account);

		return null;
	}

	@Override
	public Member findByMobile(String mobile) {
		return null;
	}

	@Override
	public Member findByAccount(String account) {
		List<Member> members = memberRepository.findByAccount(account);
		return members != null && members.size() > 0 ? members.get(0) : null;
	}

	public Page<Member> findByChannelId(Integer channelId, PageRequest pageRequest) {
		Page<Member> page = memberRepository.findByChannelId(channelId, pageRequest);
		return page;
	}


	public Page<Member> findByRoleId(Integer rolId, PageRequest pageRequest) {
		Page<Member> page = memberRepository.findByRoleId(rolId, pageRequest);
		return page;
	}

	@Override
	public void saveMember(Member member) {
		channelRepository.save(member.getChannel());
		passwordHelper.encryptPassword(member);
		memberRepository.save(member);
	}

	@Override
	public Page<Member> findByAccountLikeOrNameLikeOrMobileLike(String account, String name, String mobile,PageRequest pageRequest) {
		return memberRepository.findByAccountLikeOrNameLikeOrMobileLike(account, name, mobile, pageRequest);
	}

	@Override
	public void update(Member member) {
		memberRepository.save(member);
	}

}
