package com.ishare.mall.test.repository;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.repository.product.ProductStyleRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
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
	@Autowired
	private ProductStyleRepository productStyleRepository;
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
//		Member member = new Member();
//		member.setSex(Gender.MAN);
//		member.setAccount("123456");
//		member.setCreateBy("123456");
//
//		Channel channel = channelService.findOne(8);
//		member.setChannel(channel);
//		member.setMobile("18011111111");
//		member.setMemberType(MemberType.MEMBER);
//		PageRequest pageRequest = new PageRequest(1,15, Sort.Direction.DESC, "account");
//		Page<Member> memberPage = null; //memberRepository.findByAccountLikeOrNameLikeOrMobileLike("123456", "123456", "123456", pageRequest);
//		List<Member> list = memberPage.getContent();
//
//		System.out.println("totalElements = "+memberPage.getTotalElements()+"totalPage"+memberPage.getTotalPages() +
//				"content" + memberPage.getContent().size()+"size"+memberPage.getSize());
//		System.out.println("element = "+list.size()+"test"+list.get(0).getAccount());
//
//		Member member = new Member();
//		member.setSex(Gender.MAN);
//		member.setAccount("15928972087");
//		member.setCreateBy("15928972087");
//		member.setPassword("123456");
//		Channel channel = channelService.findOne(1);
//		member.setChannel(channel);
//		member.setMobile("18011111111");
//		member.setMemberType(MemberType.MEMBER);
//		memberService.saveMember(member);
		//Assert.assertTrue(!page.hasContent());
		//productStyleRepository.findByProductStyle(1);
		Member member = memberService.findByAccount("15928972087");
		member.setPassword("123456");
		memberService.saveMember(member);
	}

	@Override
	public void testUpdate() {

	}

	@Override
	public void testDelete() {

	}
}
