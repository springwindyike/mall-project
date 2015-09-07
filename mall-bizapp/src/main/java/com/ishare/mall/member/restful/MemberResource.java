package com.ishare.mall.member.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberDetailDTO;
import com.ishare.mall.common.base.dto.member.MemberLoginResultDTO;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.utils.mapper.MapperUtils;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Member.REQUEST_MAPPING)
public class MemberResource {
	
    @Autowired
    private MemberService memberService;
	
    private static final Logger log = LoggerFactory.getLogger(MemberResource.class);
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_LOGIN,
                    method = RequestMethod.POST, headers = "Accept=application/xml, application/json",
                    produces = {"application/json", "application/xml"},
                    consumes = {"application/json", "application/xml"})
    public MemberLoginResultDTO login(@RequestBody MemberDTO memberDTO) {
    	
    			Member member = memberService.findByAccount(memberDTO.getAccount());
    			MemberLoginResultDTO memberLoginResultDTO = new MemberLoginResultDTO();
    			if(null != member){
    					if(memberDTO.getPassword().equals(member.getPassword())){
    						MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
			        memberLoginResultDTO.setCode(200);
			        memberLoginResultDTO.setSuccess(true);
			        memberLoginResultDTO.setMemberDTO(memberDTO);
    					}else {
    						MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
			        memberLoginResultDTO.setCode(200);
			        memberLoginResultDTO.setSuccess(false);
			        memberLoginResultDTO.setMessage("密码错误。");
			        memberLoginResultDTO.setMemberDTO(memberDTO);
						}
    			}else {
					MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
        memberLoginResultDTO.setCode(200);
        memberLoginResultDTO.setSuccess(false);
        memberLoginResultDTO.setMessage("账号不存在。");
        memberLoginResultDTO.setMemberDTO(memberDTO);
    					
					}
    	
//        log.debug(memberDTO.toString());
//        MemberLoginResultDTO memberLoginResultDTO = new MemberLoginResultDTO();
//        memberLoginResultDTO.setCode(200);
//        memberLoginResultDTO.setSuccess(true);
//        memberLoginResultDTO.setMemberDTO(memberDTO);
        return memberLoginResultDTO;
    }
    public static Logger getLog() {
        return log;
    }
}
