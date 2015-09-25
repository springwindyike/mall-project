package com.ishare.mall.api.service.member;

import com.ishare.mall.common.base.exception.web.api.ApiLogicException;

/**
 * Created by YinLin on 2015/9/25.
 * Description : Member api相关接口
 * Version 1.0
 */
public interface MemberService {
    void checkAndCreateMember(String account, String clientId) throws ApiLogicException;
}
