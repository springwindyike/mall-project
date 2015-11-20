package com.ishare.mall.manage.service.member;


import com.ishare.mall.common.base.dto.member.MemberDTO;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public interface MemberService {
    /**
     * 当前会员数量
     */
    public abstract List<MemberDTO> findAll();

//    List<MemberDTO> findThisWeek();
}
