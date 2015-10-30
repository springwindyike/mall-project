package com.ishare.mall.biz.restful.manageuser;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.exception.manageuser.ManageUserServiceException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.service.manageuser.ManageUserService;
import com.ishare.mall.core.utils.page.PageUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
        manageUserDTO.setSex(manageUser.getSex());
        manageUserDTO.setUserType(manageUser.getUserType());
        response.setData(manageUserDTO);
        return response;
    }

    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_GET_MANAGE_USER_List,
                    method = RequestMethod.POST,
                    headers = "Accept=application/xml, application/json",
                    produces = {"application/json"},
                    consumes = {"application/json"})
    public Response<PageDTO<ManageUserDTO>> getManageUserList(@RequestBody ManageUserDTO manageUserDTO){
        int limit = manageUserDTO.getLimit();
        int offset = manageUserDTO.getOffset();
        Response<PageDTO<ManageUserDTO>> response = new Response<PageDTO<ManageUserDTO>>();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
        try {
            Page<ManageUser> page = manageUserService.getManageUserPage(pageRequest);
            PageDTO<ManageUserDTO> pageDTO = PageUtils.mapper(page,ManageUserDTO.class);
            response.setData(pageDTO);
        }catch (ManageUserServiceException e){
            log.error(e.getMessage());
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
    

}
