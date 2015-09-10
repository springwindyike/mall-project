package com.ishare.mall.center.controller;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberDetailDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

/**
 * Created by Wang Hao on 2015/9/6.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(APPURIConstant.Member.REQUEST_MAPPING)
public class MemberController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	/**
	 * 获取当前渠道下所有的member
	 *
	 * @return Page<MemberDetailDTO>
	 */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
	public String findByChannelId(@NotEmpty @PathVariable(OFFSET) Integer offset,
								  @NotEmpty @PathVariable(LIMIT) Integer limit, HttpServletRequest request) {
//		MemberDTO memberDTO = new MemberDTO();
//		memberDTO.setLimit(limit);
//		memberDTO.setOffset(offset);
		//PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "account");
		//memberDTO.setChannelId(channelId);
		//memberDTO.setPageRequest(pageRequest);
//		ResponseEntity<MemberDTO> resultDTO = null;
//		RestTemplate restTemplate = new RestTemplate();
//		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CHANNEL_ID), memberDTO, MemberDTO.class);
//		MemberDTO memberDTOResult = resultDTO.getBody();
		//log.debug(MemberDetailDTO.toString());
		System.out.print("test1111111");
		return CenterViewConstant.Member.MEMBER_LIST;
	}

	@RequestMapping(value = "/offset/{offset}/limit/{limit}/roleId/{roleId}", method = RequestMethod.POST)
	public Page<MemberDetailDTO> findByRoleId(@NotEmpty @PathVariable(OFFSET) Integer offset, @NotEmpty @PathVariable(LIMIT) Integer limit, @NotEmpty @PathVariable("roleId") Integer roleId) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setLimit(limit);
		memberDTO.setOffset(offset);
		//PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "account");
		memberDTO.setRoleId(roleId);
		//memberDTO.setPageRequest(pageRequest);
		ResponseEntity<MemberDTO> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ROL_ID), memberDTO, MemberDTO.class);
		MemberDTO memberDTOResult = resultDTO.getBody();
		//log.debug(MemberDetailDTO.toString());
		return memberDTOResult.getPage();
	}

	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/findByAccount/{account}", method = RequestMethod.GET)
	public MemberDetailDTO findByAccount(@NotEmpty @PathVariable("account") String account) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAccount(account);
		ResponseEntity<MemberDTO> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT), memberDTO, MemberDTO.class);
		MemberDTO memberDTOResult = resultDTO.getBody();
		return memberDTOResult.getMemberDetailDTO();
	}
}