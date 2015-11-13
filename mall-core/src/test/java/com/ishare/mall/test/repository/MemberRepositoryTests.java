package com.ishare.mall.test.repository;

import com.alibaba.druid.util.StringUtils;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.OrderRefund;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.repository.order.OrderRefundRepository;
import com.ishare.mall.core.repository.product.ProductStyleRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Random;

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
	@Autowired
	private OrderRefundRepository refundRepository;
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
//		Member member = memberService.findByAccount("qazwc1");
//		member.setPassword("123456");
//		memberService.saveMember(member);
//		Member member1 = memberService.findByAccount("qazwc1");
//		System.out.println(member1.getPassword());
		OrderRefund orderRefund = new OrderRefund();
		orderRefund.setRefundId(String.valueOf(new Date().getTime()));
		orderRefund.setOrderId("20151010000005");
		orderRefund.setChannelId(8);
		orderRefund.setChannelName("渠道组用户");
		orderRefund.setBuyerId("test");
		orderRefund.setBuyerName("test");
		orderRefund.setBuyerDate(new Date());
		orderRefund.setBuyerMessage("test O(∩_∩)O哈哈~");
		orderRefund.setProductId(66);
		orderRefund.setProductName("test 呵呵");
		orderRefund.setRefundAmount(10011.12F);
		orderRefund.setCenterId(1);
		orderRefund.setManageId(1);
		orderRefund.setCenterState(2);
		orderRefund.setRefundState(2);
		orderRefund.setRefundType(2);
		refundRepository.save(orderRefund);

	}

	@Override
	public void testUpdate() {

	}

	@Override
	public void testDelete() {

	}
}
