package com.ishare.mall.core.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.service.member.MemberService;

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

	@Override
	public Member findOne(String id) {
		// TODO Auto-generated method stub
		return memberRepository.findOne(id);
	}

}
