package com.ishare.mall.biz.restful.manageuser;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.service.manageuser.ManageUserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.ManageUser.REQUEST_MAPPING)
public class ManageUserResource {
	
    private static final Logger log = LoggerFactory.getLogger(ManageUserResource.class);
    @Autowired
    private ManageUserService manageUserService;

    public static Logger getLog() {
        return log;
    }

    /**
     * 通过账号获取用户信息，用于登录
     * @param username
     * @return
     */
    @RequestMapping(value       = APPURIConstant.ManageUser.REQUEST_MAPPING_GET_BY_USERNAME,
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json"})
    public Response<ManageUserDTO> queryByUsername(@NotEmpty @PathVariable("username") String username) {
        ManageUser manageUser = manageUserService.findByUsername(username);
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        Response response = new Response();
        if (manageUser == null){
            response.setSuccess(Response.Status.FAILURE);
            return response;
        }
        BeanUtils.copyProperties(manageUser, manageUserDTO);
        manageUserDTO.setUserType(manageUser.getUserType());
        response.setData(manageUserDTO);
        return response;
    }
    

}
