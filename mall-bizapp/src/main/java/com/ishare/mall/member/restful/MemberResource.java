package com.ishare.mall.member.restful;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberLoginResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Member.REQUEST_MAPPING)
public class MemberResource {
    private static final Logger log = LoggerFactory.getLogger(MemberResource.class);
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_LOGIN,
                    method = RequestMethod.POST, headers = "Accept=application/xml, application/json",
                    produces = {"application/json", "application/xml"},
                    consumes = {"application/json", "application/xml"})
    public MemberLoginResultDTO login(@RequestBody MemberDTO memberDTO) {
        log.debug(memberDTO.toString());
        MemberLoginResultDTO memberLoginResultDTO = new MemberLoginResultDTO();
        memberLoginResultDTO.setCode(200);
        memberLoginResultDTO.setSuccess(true);
        memberLoginResultDTO.setMemberDTO(memberDTO);
        return memberLoginResultDTO;
    }
    public static Logger getLog() {
        return log;
    }
}
