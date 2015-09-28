package com.ishare.mall.api.service.member.impl;

import com.ishare.mall.api.service.BaseService;
import com.ishare.mall.api.service.member.MemberService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.CheckAndCreateDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by YinLin on 2015/9/25.
 * Description :
 * Version 1.0
 */
@Service
public class MemberServiceImpl extends BaseService implements MemberService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void checkAndCreateMember(String account, String clientId) throws ApiLogicException {
        CheckAndCreateDTO checkAndCreateDTO = new CheckAndCreateDTO();
        checkAndCreateDTO.setAccount(account);
        checkAndCreateDTO.setClientId(clientId);
        ResponseEntity<Response> responseEntity;
        try {
            responseEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING,
                                                        APPURIConstant.Member.REQUEST_MAPPING_CHECK_AND_CREATE),
                                                        checkAndCreateDTO, Response.class);
        } catch (Exception e) {
            throw new ApiLogicException("用户检测失败");
        }

        Response response = responseEntity.getBody();
        if (!response.isSuccess()) throw new ApiLogicException("用户检测失败");
    }
}
