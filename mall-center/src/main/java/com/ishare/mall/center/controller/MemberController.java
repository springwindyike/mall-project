package com.ishare.mall.center.controller;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

import javax.servlet.http.HttpServletRequest;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.form.member.MemberUpdatePasswordForm;
import com.ishare.mall.common.base.dto.member.MemberRegisterDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;

import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.common.base.general.Response;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.center.form.member.MemberForm;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberDetailDTO;

/**
 * Created by Wang Hao on 2015/9/6.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(APPURIConstant.Member.REQUEST_MAPPING)
public class MemberController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 获取当前渠道下所有的member
	 *
	 * @return Page<MemberDetailDTO>
	 */
	@RequestMapping(value = "/findByChannelId", method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findByChannelId(@CurrentMember MemberDTO memberDTO,HttpServletRequest request, Model model) throws Exception{
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		memberDTO.setLimit(displayLength);
		memberDTO.setOffset(currentPage);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CHANNEL_ID),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CHANNEL_ID+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response.isSuccess()){
			MemberDTO memberDTOResult = (MemberDTO)response.getData();
			model.addAttribute("pageDTO", memberDTOResult.getPageDTO());
			return memberDTOResult.getPageDTO();
		}else {
			throw new Exception(response.getMessage());
		}
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
		return null;
	}

	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/findByAccount/{account}", method = RequestMethod.GET)
	public String findByAccount(@NotEmpty @PathVariable("account") String account,Model model) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAccount(account);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MemberDTO> resquestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT),
					HttpMethod.POST, resquestDTO, new ParameterizedTypeReference<Response<MemberDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if (response.isSuccess()){
			MemberDTO memberResultDTO = (MemberDTO)response.getData();
			model.addAttribute("memberDetailDTO",memberResultDTO.getMemberDetailDTO());
		}else {
			throw new Exception(response.getMessage());
		}
		return CenterViewConstant.Member.MEMBER_VIEW;
	}

	/**
	 * 保存新member
	 * @param memberForm
	 * @return member list 页面
	 */
	@ResponseBody
	@RequestMapping(value = "/saveMember")
	public String saveMember(MemberForm memberForm) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		BeanUtils.copyProperties(memberForm, memberDTO);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_SAVE_MEMBER),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_SAVE_MEMBER+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if (!response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return CenterViewConstant.Member.MEMBER_ADD_SUCCESS;
	}

	/**
	 *跳转到add页面
	 * @return to add member page
	 */
	@RequestMapping(value = "/addMemberPage")
	public String forwardTOAddMember(){
		return CenterViewConstant.Member.MEMBER_ADD;
	}

	@RequestMapping(value = "/findBySearchCondition/{searchCondition}")
	@ResponseBody
	public PageDTO findBySearchCondition(@PathVariable("searchCondition") String searchCondition,Model model,HttpServletRequest request,@CurrentMember MemberDTO memberDTO) throws Exception{
		memberDTO.setMobile(searchCondition);
		memberDTO.setAccount(searchCondition);
		memberDTO.setName(searchCondition);
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		System.out.println(request.getParameter("draw"));
		System.out.println(request.getParameter("length"));

		int currentPage = displayStart/displayLength+1;
		memberDTO.setLimit(displayLength);
		memberDTO.setOffset(currentPage);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING,APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CONDITION),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app " + APPURIConstant.Member.REQUEST_MAPPING + APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_CONDITION + "error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response != null) {
			if(response.isSuccess()){
				memberDTO = (MemberDTO)response.getData();
				model.addAttribute("pageDTO",memberDTO.getPageDTO());
				return memberDTO.getPageDTO();
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}

	/**
	 * 跳转到list 页面
	 * @return
	 */
	@RequestMapping(value = "forwardTOMemberList")
	public String forwardTOMemberList(){
		return CenterViewConstant.Member.MEMBER_LIST;
	}
    /**
     * 访问找回密码页面
     * @return
     */
    @RequestMapping(value = CenterURIConstant.Member.Password.FIND, method = RequestMethod.GET)
    public String findPassword() {
        return CenterViewConstant.Member.Password.FIND_PASSWORD;
    }

	/**
	 * 根据account查询member view
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/memberView/account/{account}",method = RequestMethod.GET)
	public String memberView(@NotEmpty @PathVariable("account") String account,Model model) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAccount(account);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
//		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {
					});
		} catch (Exception e) {
			log.error("call bizp app " + APPURIConstant.Member.REQUEST_MAPPING + APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT + "error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if (response != null) {
			if (response.isSuccess()) {
				memberDTO = (MemberDTO) response.getData();
				model.addAttribute("memberDetailDTO", memberDTO.getMemberDetailDTO());
			} else {
				throw new Exception(response.getMessage());
			}
		}
		return CenterViewConstant.Member.MEMBER_VIEW;
	}
	@ResponseBody
	@RequestMapping(value = "/changePassword")
	public String changePassword(MemberUpdatePasswordForm memberUpdateForm) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		BeanUtils.copyProperties(memberUpdateForm, memberDTO);
		ResponseEntity<Response<MemberDTO>> resultEntity = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING,APPURIConstant.Member.REQUEST_MAPPING_CHANGE_PASSWORD),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>(){});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_CHANGE_PASSWORD+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultEntity.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return CenterViewConstant.Member.MEMBER_UPDATE_SUCCESS;
	}

	@RequestMapping(value = "/forward2ChangePassword/account/{account}",method = RequestMethod.GET)
	public String forward2ChangePassword(@NotEmpty @PathVariable("account") String account,Model model) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAccount(account);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response != null){
			if(response.isSuccess()){
				MemberDTO memberDTOResult = (MemberDTO)response.getData();
				model.addAttribute("memberDetailDTO", memberDTOResult.getMemberDetailDTO());
			}else {
				throw new Exception(response.getMessage());
			}
		}else {
			throw new Exception("get response error");
		}
		return CenterViewConstant.Member.MEMBER_CHANGE_PASSWORD;
	}
	@ResponseBody
	@RequestMapping(value = "/delete/account/{account}")
	public String delete(@NotEmpty @PathVariable("account") String account) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAccount(account);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_DELETE),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_DELETE+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if (response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return CenterViewConstant.Member.MEMBER_UPDATE_SUCCESS;
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(MemberForm memberForm) throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		BeanUtils.copyProperties(memberForm, memberDTO);
		ResponseEntity<Response<MemberDTO>> resultEntity = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try{
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_UPDATE),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_UPDATE+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultEntity.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return CenterViewConstant.Member.MEMBER_UPDATE_SUCCESS;
	}

	/**
	 * 跳转到update 页面
	 * @return
	 */
	@RequestMapping(value = "/forward2UpdatePage/account/{account}")
	public String forward2UpdatePage(@NotEmpty @PathVariable("account") String account,Model model) throws  Exception{
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setAccount(account);
		ResponseEntity<Response<MemberDTO>> resultDTO = null;
		HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<MemberDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Member.REQUEST_MAPPING+APPURIConstant.Member.REQUEST_MAPPING_FIND_BY_ACCOUNT+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response != null){
			if(response.isSuccess()){
				MemberDTO memberDTOResult = (MemberDTO)response.getData();
				model.addAttribute("memberDetailDTO", memberDTOResult.getMemberDetailDTO());
			}else {
				throw new Exception(response.getMessage());
			}
		}else {
			throw new Exception("get response error");
		}
		return CenterViewConstant.Member.MEMBER_UPDATE;
	}

	/**
	 * 注册账号验证
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = CenterURIConstant.Index.ACCOUNT_VALID, method = RequestMethod.POST)
	public ValidformRespDTO accountValid(@RequestParam("name") String name, @RequestParam("param") String param) {
		MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
		memberRegisterDTO.setAccount(param);
		ResponseEntity<ValidformRespDTO> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, APPURIConstant.Member.REQUEST_MAPPING_FIND_VALID_BY_ACCOUNT), memberRegisterDTO, ValidformRespDTO.class);
		ValidformRespDTO validformRespDTO = resultDTO.getBody();
		return validformRespDTO;
	}
}
