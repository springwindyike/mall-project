package com.ishare.mall.test.repository;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Administrator on 2015/9/7.
 */
public class MemberRepositoryTests extends RepositoryTestTemplate {
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void setUp() {

	}

	@Override
	public void tearDown() {

	}

	@Override
	public void testCreate() {

	}

	@Override
	public void testRetrieve() {
		Page<Member> page = memberRepository.findByRoleId(1, new PageRequest(1, 15));
		Assert.assertTrue(!page.hasContent());
	}

	@Override
	public void testUpdate() {

	}

	@Override
	public void testDelete() {

	}
}
