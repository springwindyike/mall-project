package com.ishare.mall.center.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by YinLin on 2015/9/8.
 * Description : 给Request注入当前用户
 * Version 1.0
 */
public class SysMemberFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(SysMemberFilter.class);

    private Cache cache;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.debug("来咯·");
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        log.debug("account : " + account);
//        MemberDTO cacheMember = null;
//        if (cacheMember == null){
//            MemberDTO memberDTO = new MemberDTO();
//            memberDTO.setAccount(account);
//            ResponseEntity<Response<MemberDTO>> resultDTO = null;
//            RestTemplate restTemplate = new RestTemplate();
//            HttpEntity<MemberDTO> resquestDTO = new HttpEntity<MemberDTO>(memberDTO);
//            try{
//                resultDTO = restTemplate.exchange("/member/findByAccount",
//                        HttpMethod.POST, resquestDTO, new ParameterizedTypeReference<Response<MemberDTO>>(){});
//            }catch (Exception e){
//                log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT+"error");
//                throw new Exception(e.getMessage());
//            }
//            Response responseDTO = resultDTO.getBody();
//            if(responseDTO == null){
//                throw new Exception("get responseDTO error");
//            }
//            if (responseDTO.isSuccess()){
//                cacheMember = (MemberDTO)responseDTO.getData();
//                this.cache.put(account,cacheMember);
//            }else {
//                throw new Exception(responseDTO.getMessage());
//            }
//        }
//
//        request.setAttribute(CommonConstant.Common.CURRENT_MEMBER, cacheMember);
        return true;
    }

}
