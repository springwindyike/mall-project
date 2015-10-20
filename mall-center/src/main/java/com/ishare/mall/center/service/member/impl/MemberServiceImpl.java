package com.ishare.mall.center.service.member.impl;

import com.ishare.mall.center.service.base.BaseService;
import com.ishare.mall.center.service.member.MemberService;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by YinLin on 2015/10/9.
 * Description :
 * Version 1.0
 */
@Service
public class MemberServiceImpl extends BaseService implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private Cache<String, CurrentMemberDTO> cache;

    @Autowired
    public MemberServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("currentMemberCache");
    }

    @Override
    public CurrentMemberDTO getCurrentMember(String account) {
        CurrentMemberDTO currentMemberDTO = cache.get(account);
        if (currentMemberDTO == null) {
            currentMemberDTO = new CurrentMemberDTO();
            MemberDTO memberDTO = this.findByAccount(account);
            currentMemberDTO.setAccount(memberDTO.getAccount());
            currentMemberDTO.setChannelId(memberDTO.getChannelId());
            currentMemberDTO.setName(memberDTO.getName());
            currentMemberDTO.setId(memberDTO.getId());
            currentMemberDTO.setGender(memberDTO.getGender());
            this.cache.put(account, currentMemberDTO);
        }
        return currentMemberDTO;
    }

    @Override
    public MemberDTO findByAccount(String account) {
        ResponseEntity<Response<MemberDTO>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_GET_BY_ACCOUNT),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Response<MemberDTO>>() {
                    }, account);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("用户未找到", HttpStatus.NOT_FOUND);
        }
        Response<MemberDTO> response = responseEntity.getBody();
        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("用户未找到", HttpStatus.NOT_FOUND);
        }
        return response.getData();
    }
}
