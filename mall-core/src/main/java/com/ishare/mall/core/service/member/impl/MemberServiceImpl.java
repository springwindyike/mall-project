package com.ishare.mall.core.service.member.impl;

import com.ishare.mall.common.base.exception.member.MemberServiceException;
import com.ishare.mall.common.base.utils.DateUntil;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.member.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);


	private DateUntil dateUntil = new DateUntil();
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private PasswordHelper passwordHelper;

	@Override
	public Member findOne(Integer id) {
		return memberRepository.findOne(id);
	}

	@Override
	public Member checkAndCreateByAccount(String account, String appId) {
		Member member = this.findByAccount(account);
		if (member == null) {
			member = new Member();
			Channel channel = channelService.findByAppId(appId);
			member.setAccount(account);
			member.setChannel(channel);
			member.setPassword(Default.DEFAULT_PASSWORD);
			member.setSex(Default.DEFAULT_SEX);
			member.setMemberType(Default.DEFAULT_TYPE);
			member.setMobile(account);
			try {
				this.saveMember(member);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new MemberServiceException("创建用户失败");
			}
		}
		return member;
	}

	@Override
	public Member findByMobile(String mobile) {
		return null;
	}

	@Override
	public Member findByAccount(String account) throws MemberServiceException{
		List<Member> members = memberRepository.findByAccount(account);
		return members != null && members.size() > 0 ? members.get(0) : null;
	}

	public Page<Member> findByChannelId(Integer channelId, PageRequest pageRequest) throws MemberServiceException{
		Page<Member> page = memberRepository.findByChannelId(channelId, pageRequest);
		return page;
	}


	public Page<Member> findByRoleId(Integer rolId, PageRequest pageRequest) {
		Page<Member> page = memberRepository.findByRoleId(rolId, pageRequest);
		return page;
	}

	@Override
	public void saveMember(Member member) throws MemberServiceException{
		if (member.getChannel() != null) {
			channelRepository.save(member.getChannel());
		}
		passwordHelper.encryptPassword(member);
		memberRepository.save(member);
	}

	@Override
	public Page<Member> findBycondition(String account, String name, String mobile,Integer channelId,PageRequest pageRequest) throws MemberServiceException{
		return memberRepository.findBycondition(account, name, mobile, channelId, pageRequest);
	}

	@Override
	public void update(Member member) throws MemberServiceException {
		memberRepository.save(member);
	}

	public static Logger getLog() {
		return log;
	}
	/**
	 * 查询所有实体
	 * @return
	 */
	public List<Member> findAll(){
		List<Member> members = memberRepository.findAll();
		System.out.println(members);
		return  members;
	};

	/**
	 * 查询所有实体(分页)
	 * @return
	 */
	public Page<Member> findAll(PageRequest pageRequest){
		Page<Member> page = memberRepository.findAll(pageRequest);
		return  page;
	};
	/**
	 * 查询本周新增的会员
	 */
	public Page<Member> findAllThisWeek(PageRequest pageRequest) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		Page<Member> page = memberRepository.findThisWeek(cal.getTime(), pageRequest);
		return page;

	}
	/**
	 * 查询会员数量
	 */
	public Long findCount(){
		return memberRepository.findCount();
	}
	/**
	 * 查询会员本周新增数量
	 */
	public Long findThisWeekCount(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return memberRepository.findThisWeekCount(cal.getTime());
	}

}
