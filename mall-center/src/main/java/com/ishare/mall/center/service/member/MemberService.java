package com.ishare.mall.center.service.member;

import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.member.MemberDTO;

/**
 * Created by YinLin on 2015/10/9.
 * Description :
 * Version 1.0
 */
public interface MemberService {
    /**
     * 获取当前登录用户
     * @param account
     * @return
     */
    CurrentMemberDTO getCurrentMember(String account);

    MemberDTO findByAccount(String account);
}
