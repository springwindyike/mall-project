package com.ishare.mall.biz.restful.member;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.*;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.status.MemberType;
import com.ishare.mall.core.utils.UuidUtils;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private ChannelService channelService;

    public static Logger getLog() {
        return log;
    }

    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_LOGIN,
                    method = RequestMethod.POST, headers = "Accept=application/xml, application/json",
                    produces = {"application/json", "application/xml"},
                    consumes = {"application/json", "application/xml"})
    public MemberLoginResultDTO login(@RequestBody MemberLoginDTO memberLoginDTO) {
        log.debug(memberLoginDTO.toString());
        Member member = memberService.findByAccount(memberLoginDTO.getAccount());
        MemberLoginResultDTO memberLoginResultDTO = new MemberLoginResultDTO();
        if(null != member){
                    if (memberLoginDTO.getPassword().equals(member.getPassword())) {
                        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
                        memberLoginResultDTO.setCode(200);
			        memberLoginResultDTO.setSuccess(true);
                        memberLoginResultDTO.setMemberLoginDTO(memberLoginDTO);
                    } else {
                        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
			        memberLoginResultDTO.setCode(200);
			        memberLoginResultDTO.setSuccess(false);
			        memberLoginResultDTO.setMessage("密码错误。");
                        memberLoginResultDTO.setMemberLoginDTO(memberLoginDTO);
                    }
        }else {
					MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
        memberLoginResultDTO.setCode(200);
        memberLoginResultDTO.setSuccess(false);
        memberLoginResultDTO.setMessage("账号不存在。");
                    memberLoginResultDTO.setMemberLoginDTO(memberLoginDTO);

        }
    	
//        log.debug(memberDTO.toString());
//        MemberLoginResultDTO memberLoginResultDTO = new MemberLoginResultDTO();
//        memberLoginResultDTO.setCode(200);
//        memberLoginResultDTO.setSuccess(true);
//        memberLoginResultDTO.setMemberDTO(memberDTO);
        return memberLoginResultDTO;
    }

    /**
     * 获取当前角色下单所有member
     *
     * @return Page<MemberDetailDTO>
     */
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ROL_ID,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberDTO findMemberByRolId(@RequestBody MemberDTO memberDTO) {
        List<MemberDetailDTO> listMemberList = new ArrayList<MemberDetailDTO>();
        int offset = memberDTO.getOffset();
        int limit = memberDTO.getLimit();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "account");
        Integer rolId = memberDTO.getRoleId();
        Page<Member> result = memberService.findByRoleId(rolId, pageRequest);
        PageDTO pageDTO = new PageDTO();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Member> listMember = result.getContent();
            for (Member member:listMember){
                MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
                BeanUtils.copyProperties(member, memberDetailDTO);
                memberDetailDTO.setChannelId(member.getChannel().getId());
                memberDetailDTO.setSex(member.getSex().getName());
                memberDetailDTO.setMemberType(member.getMemberType().getName());
                listMemberList.add(memberDetailDTO);
            }
            pageDTO.setContent(listMemberList);
            pageDTO.setTotalPages(result.getTotalPages());
            memberDTO.setPageDTO(pageDTO);
        }
        return memberDTO;
    }

    /**
     * 获取当前渠道下所有的member
     *
     * @return Page<MemberDetailDTO>
     */
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberDTO findByChannelId(@RequestBody MemberDTO memberDTO) {
        List<MemberDetailDTO> listMemberList = new ArrayList<MemberDetailDTO>();
        int offset = memberDTO.getOffset();
        int limit = memberDTO.getLimit();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "account");
        Integer channelId = memberDTO.getChannelId();
        Page<Member> result = memberService.findByChannelId(channelId, pageRequest);
        PageDTO pageDTO = new PageDTO();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Member> listMember = result.getContent();
            for (Member member:listMember){
                MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
                BeanUtils.copyProperties(member, memberDetailDTO);
                memberDetailDTO.setChannelId(member.getChannel().getId());
                memberDetailDTO.setSex(member.getSex().getName());
                memberDetailDTO.setMemberType(member.getMemberType().getName());
                listMemberList.add(memberDetailDTO);
            }
            pageDTO.setContent(listMemberList);
            pageDTO.setTotalPages(result.getTotalPages());
            pageDTO.setITotalDisplayRecords(result.getTotalElements());
            pageDTO.setITotalRecords(result.getTotalElements());
            memberDTO.setPageDTO(pageDTO);
        }
        return memberDTO;
    }

    /**
     * 通过account查询出memeber信息
     *
     * @param memberDTO
     * @return Member 返回的数据对象
     */
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberDTO findByAccount(@RequestBody MemberDTO memberDTO) {
        Member member = memberService.findByAccount(memberDTO.getAccount());
        MemberDetailDTO memberDetailDTO = (MemberDetailDTO) MapperUtils.map(member, MemberDetailDTO.class);
        memberDTO.setMemberDetailDTO(memberDetailDTO);
        return memberDTO;
    }
    /**
     * 通过account查询出memeber是否存在
     * @return Member 返回的数据对象
     */
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_FIND_VALID_BY_ACCOUNT, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ValidformRespDTO findValidByAccount(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        Member member = memberService.findByAccount(memberRegisterDTO.getAccount());
        ValidformRespDTO validformRespDTO = new ValidformRespDTO();
        if(null != member){
		        validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_FAIL_INFO);
		        validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_FAIL_STATUS);
	    			}else{
		        validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_SUCCESS_INFO);
		        validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_SUCCESS_STATUS);
        				}
         return validformRespDTO;
    	}

    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_SAVE_MEMBER, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberDTO saveMeber(@RequestBody MemberDTO memberDTO){
        List<MemberDetailDTO> listMemberList = new ArrayList<MemberDetailDTO>();
        Member member = new Member();
        BeanUtils.copyProperties(memberDTO, member);
        member.setSex("M".equals(memberDTO.getSex()) ? Gender.MAN : Gender.WOMEN);
        member.setCreateBy(memberDTO.getAccount());
        member.setMemberType(MemberType.MEMBER);
        Channel channel = channelService.findOne(8);
        member.setChannel(channel);
        memberService.saveMember(member);
        PageRequest pageRequest = new PageRequest(1,15,Sort.Direction.DESC,"createTime");
        Integer channelId = memberDTO.getChannelId();
        Page<Member> result = memberService.findByChannelId(channelId, pageRequest);
        PageDTO pageDTO = new PageDTO();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Member> listMember = result.getContent();
            for (Member memberPage:listMember){
                MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
                BeanUtils.copyProperties(memberPage, memberDetailDTO);
                memberDetailDTO.setChannelId(memberPage.getChannel().getId());
                memberDetailDTO.setSex(memberPage.getSex().getName());
                memberDetailDTO.setMemberType(memberPage.getMemberType().getName());
                listMemberList.add(memberDetailDTO);
            }
            pageDTO.setContent(listMemberList);
            pageDTO.setTotalPages(result.getTotalPages());
            memberDTO.setPageDTO(pageDTO);
        }
        return memberDTO;
    }

    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CONDITION, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberDTO findBySearchCondition(@RequestBody MemberDTO memberDTO){
        List<MemberDetailDTO> listMemberList = new ArrayList<MemberDetailDTO>();
        String account = memberDTO.getAccount();
        String name = memberDTO.getName();
        String mobile = memberDTO.getMobile();
        int offset = memberDTO.getOffset();
        int limit = memberDTO.getLimit();
        PageRequest pageRequest = new PageRequest(0, 1, Sort.Direction.DESC, "account");
        Page<Member> result = memberService.findByAccountLikeOrNameLikeOrMobileLike(account, name, mobile, pageRequest);
        PageDTO pageDTO = new PageDTO();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Member> listMember = result.getContent();
            for (Member member:listMember){
                MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
                BeanUtils.copyProperties(member, memberDetailDTO);
                memberDetailDTO.setChannelId(member.getChannel().getId());
                memberDetailDTO.setSex(member.getSex().getName());
                memberDetailDTO.setMemberType(member.getMemberType().getName());
                listMemberList.add(memberDetailDTO);
            }
            pageDTO.setContent(listMemberList);
            pageDTO.setTotalPages(result.getTotalPages());
            pageDTO.setITotalDisplayRecords(result.getTotalElements());
            pageDTO.setITotalRecords(result.getTotalElements());
            memberDTO.setPageDTO(pageDTO);
        }
        return memberDTO;
    }

    /**
     * 通过账号获取用户信息，用于登录
     * @param account
     * @return
     */
    @RequestMapping(value       = "/{account}",
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json", "application/xml"})
    public MemberDTO queryByAccount(@NotEmpty @PathVariable("account") String account) {
        Member member = memberService.findByAccount(account);
        if (member == null) return null;
        return (MemberDTO) MapperUtils.map(member, MemberDTO.class);
    }
    
    /**
     * 账号注册
     * @param memberRegisterDTO
     * @return
     */
    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_REGISTER_MEMBER, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberRegisterResultDTO registerMember(@RequestBody MemberRegisterDTO memberRegisterDTO){
        MemberRegisterResultDTO memberRegisterResultDTO = new MemberRegisterResultDTO();
        Member memberValid = memberService.findByAccount(memberRegisterDTO.getAccount());
        if(null != memberValid){
        	memberRegisterResultDTO.setMessage("账户已存在！");
						memberRegisterResultDTO.setSuccess(false);
						return memberRegisterResultDTO;
        			}
        String[] area = memberRegisterDTO.getCity().split(",");

        Member member = new Member();
        Channel channel = new Channel();
        BeanUtils.copyProperties(memberRegisterDTO, member);
        Date date = new Date();
        String dateStr = String.valueOf(date.getTime());
        member.setSex("1".equals(memberRegisterDTO.getSex()) ? Gender.MAN : Gender.WOMEN);
        member.setCreateBy(memberRegisterDTO.getAccount());
        member.setMemberType(MemberType.ADMIN);
        member.setCreateTime(date);
        member.setUse(true);//这里将账户账户设置为可用
        
        channel.setUpdateBy(memberRegisterDTO.getAccount());
        channel.setName(memberRegisterDTO.getChannel());
        channel.setCountry("中国");
        channel.setProvince(area[0]);
        channel.setCreateTime(date);
        channel.setCreateBy(memberRegisterDTO.getAccount());
        UuidUtils uu = new UuidUtils();
        String appId = uu.App_Id();
        channel.setAppId(appId);
        channel.setAppSecret(uu.App_screct(dateStr, appId));
        if(area.length > 1){
        	channel.setCity(area[1]);
        			 }
        if(area.length > 2){
        	channel.setDistrict(area[2]);
        			 }
        
        member.setChannel(channel);
        try {
						memberService.saveMember(member);
					} catch (Exception e) {
						e.printStackTrace();
						memberRegisterResultDTO.setMessage("数据库出错！");
						memberRegisterResultDTO.setSuccess(false);
						return memberRegisterResultDTO;
					}
        memberRegisterResultDTO.setSuccess(true);
        return memberRegisterResultDTO;
    }
    
    public static void main(String[] args) {
    	String str = ",2";
    	String[] area = str.split(",");

    	System.out.println(area[0]);
    	System.out.println(area[1]);
//    	System.out.println(area[2]);
	}
}
