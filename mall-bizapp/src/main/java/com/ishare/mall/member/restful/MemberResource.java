package com.ishare.mall.member.restful;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberDetailDTO;
import com.ishare.mall.common.base.dto.member.MemberLoginResultDTO;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.oauth.OAuthService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import com.ishare.mall.core.utils.page.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private MemberService memberService;
    @Autowired
    private OAuthService oAuthService;

    public static Logger getLog() {
        return log;
    }

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

    /**
     * 获取当前角色下单所有member
     *
     * @return Page<MemberDetailDTO>
     */
    @RequestMapping(value = "findMemberByRolId", method = RequestMethod.GET)
    public MemberDTO findMemberByRolId(@RequestBody MemberDTO memberDTO) {

        PageRequest pageRequest = memberDTO.getPageRequest();
        Integer rolId = memberDTO.getRoleId();
        Page<Member> result = memberService.findByRoleId(rolId, pageRequest);
        memberDTO.setPage(PageUtils.mapper(result, pageRequest, MemberDetailDTO.class));
        return memberDTO;
    }

    /**
     * 获取当前渠道下所有的member
     *
     * @return Page<MemberDetailDTO>
     */
    @RequestMapping(value = "findByChannelId", method = RequestMethod.GET)
    public MemberDTO findByChannelId(@RequestBody MemberDTO memberDTO) {
        PageRequest pageRequest = memberDTO.getPageRequest();
        Integer channelId = memberDTO.getChannelId();
        Page<Member> result = memberService.findByChannelId(channelId, pageRequest);
        memberDTO.setPage(PageUtils.mapper(result, pageRequest, MemberDetailDTO.class));
        return memberDTO;
    }

    /**
     * 通过account查询出memeber信息
     *
     * @param memberDTO
     * @return Member 返回的数据对象
     */
    @RequestMapping(value = "findByAccount", method = RequestMethod.GET)
    public MemberDTO findByAccount(@RequestBody MemberDTO memberDTO) {
        Member member = memberService.findByAccount(memberDTO.getAccount());
        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
        memberDTO.setMemberDetailDTO(memberDetailDTO);
        return memberDTO;
    }
}
