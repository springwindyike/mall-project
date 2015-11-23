package com.ishare.mall.manage.service.member.impl;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.service.base.BaseService;
import com.ishare.mall.manage.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
@Service
public class MemberServiceImpl extends BaseService implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 当前会员数量
     */
    public List<MemberDTO> findAll(){
        log.debug("findAll start");
        ResponseEntity<Response<List<MemberDTO>>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI("/member", "/member"),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<List<MemberDTO>>>() {
                });
        Response<List<MemberDTO>> response = responseEntity.getBody();
        return response.getData();
    };

    /**
     * 本周新增会员的数量
     *
     */

    public Long findThisWeekCount(){
        log.debug("findAll start");
        ResponseEntity<Response<Long>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_THISWEEK_COUNT),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
                });
        Response<Long> response = responseEntity.getBody();
        return response.getData();
    }
    /**
     * 所有会员数量
     * @return
     */
    public Long findCount(){
        log.debug("findAll start");
        ResponseEntity<Response<Long>> responseEntity;
        responseEntity = restTemplate.exchange(
                this.buildBizAppURI("/member", "/count"),
                HttpMethod.GET, null, new ParameterizedTypeReference<Response<Long>>() {
                });
        Response<Long> response = responseEntity.getBody();
        return response.getData();
    }

}

