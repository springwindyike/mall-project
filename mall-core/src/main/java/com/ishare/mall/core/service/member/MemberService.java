package com.ishare.mall.core.service.member;

import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface MemberService {
	/**
	 * 通过账号ID查找
	 * @param id
	 * @return
	 */
	Member findOne(Integer id);

	/**
	 * 检测账号是否存在，并创建
	 * @param account
	 * @return
	 */
	Member checkAndCreateByAccount(String account, Integer channelId);

	/**
	 * @param mobile
	 * @return
	 */
	Member findByMobile(String mobile);

	/**
	 *
	 * 根据account查询
	 *
	 * @param account
	 * @return
	 */
	Member findByAccount(String account);

	/**
	 * 根据channel id查询所有的member
	 *
	 * @param channelId
	 * @return
	 */
	Page<Member> findByChannelId(Integer channelId, PageRequest pageRequest);

	/**
	 * 根据rol id 查询所有的member
	 *
	 * @param rolId
	 * @return
	 */
	Page<Member> findByRoleId(Integer rolId, PageRequest pageRequest);

	/**
	 * 保存新的member
	 * @param member
	 */
	void saveMember(Member member);

	Page<Member> findByAccountLikeOrNameLikeOrMobileLike(String account,String name,String mobile,PageRequest pageRequest);

	/**
	 * 更新用户信息
	 * @param member
	 */
	void update(Member member);
}
