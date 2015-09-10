package com.ishare.mall.test.repository;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.status.MemberType;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/9/7.
 */
public class MemberRepositoryTests extends RepositoryTestTemplate {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private MemberService memberService;
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
		Member member = new Member();
		member.setSex(Gender.MAN);
		member.setAccount("15928972087");
		member.setCreateBy("15928972087");
		member.setPassword("123456");
		Channel channel = channelService.findOne(1);
		member.setChannel(channel);
		member.setMobile("18011111111");
		member.setMemberType(MemberType.MEMBER);
		memberService.saveMember(member);
		//Assert.assertTrue(!page.hasContent());
	}

	@Override
	public void testUpdate() {

	}

	@Override
	public void testDelete() {

	}
}
