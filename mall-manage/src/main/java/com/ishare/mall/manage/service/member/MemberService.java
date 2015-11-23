package com.ishare.mall.manage.service.member;


import com.ishare.mall.common.base.dto.member.MemberDTO;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public interface MemberService {
    /**
     * 获取当前所有会员
     */
    List<MemberDTO> findAll();

//    List<MemberDTO> findThisWeek();
    Long findCount();
    /**
     * 获取本周新增会员数量
     */
    Long findThisWeekCount();
}
