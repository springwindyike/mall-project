package com.ishare.mall.core.service.member;

import com.ishare.mall.core.model.member.Member;

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
	 * \
	 * 根据account查询
	 *
	 * @param account
	 * @return
	 */
	Member findByAccount(String account);

}
