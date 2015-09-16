package com.ishare.mall.api.restful;

import com.ishare.mall.common.base.dto.member.MemberDetailDTO;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.oauth.OAuthService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by YinLin on 2015/8/5.
 * Description: 用户接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/members")
public class MemberResource {
	
	@Autowired
	private OAuthService oAuthService;
	
	@Autowired
	private MemberService memberService;
	
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create() {
        return null;
    }
    
    /**
     * 通过accessToken获取到ID查询出memeber信息
  * @param accessToken
  * @return Member 返回的数据对象
	 */
	@RequestMapping(value = "/accessToken/{accessToken}", method = RequestMethod.GET) 
	@ResponseBody
    public Object detail(@NotEmpty @PathVariable("accessToken") String accessToken) throws OAuthSystemException {
		if (!oAuthService.checkAccessToken(accessToken)) {  
			// 如果不存在/过期了，返回未验证错误，需重新验证  
			OAuthResponse response = OAuthRSResponse  
			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)  
			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)  
			        .buildHeaderMessage();

			return response;
		}
		String account = oAuthService.getAccountByAccessToken(accessToken);
		Member member = memberService.findByAccount(account);
		return MapperUtils.map(member, MemberDetailDTO.class);
	}

    public Member update() {
        return null;
    }


}