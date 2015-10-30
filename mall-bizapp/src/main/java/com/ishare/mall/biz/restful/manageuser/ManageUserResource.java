package com.ishare.mall.biz.restful.manageuser;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.exception.manageuser.ManageUserServiceException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.service.manageuser.ManageUserService;
import com.ishare.mall.core.utils.page.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    /**
     * 设置manag user 是否可用
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_CHANGE_STATUS,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response changeStatus(@RequestBody ManageUserDTO manageUserDTO){
        Response response = new Response();
        ManageUser manageUser = manageUserService.findOne(manageUserDTO.getId());
        if(manageUser == null){
            response.setSuccess(false);
            response.setMessage("manage user is null.");
            return response;
        }
        manageUser.setUse(manageUserDTO.isUse());
        try{
            manageUserService.update(manageUser);
        }catch (ManageUserServiceException e){
            log.error(e.getMessage());
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_FIND_BY_ID,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response<ManageUserDTO> findById(@RequestBody ManageUserDTO manageUserDTO){
        Response<ManageUserDTO> response = new Response<ManageUserDTO>();
        ManageUser manageUser= manageUserService.findOne(manageUserDTO.getId());
        if(manageUser == null){
            response.setSuccess(false);
            response.setMessage("manage user is null.");
        }
        BeanUtils.copyProperties(manageUser,manageUserDTO);
        response.setData(manageUserDTO);
        return response;
    }

    /**
     * 重置密码
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_RESET_PASSWORD,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response resetPassword(@RequestBody ManageUserDTO manageUserDTO){
        Response response = new Response();
        ManageUser manageUser = manageUserService.findOne(manageUserDTO.getId());
        if(manageUser == null){
            response.setSuccess(false);
            response.setMessage("manage user is null.");
            return response;
        }
        manageUser.setPassword("123456");
        try{
            manageUserService.saveManageUser(manageUser);
        }catch (ManageUserServiceException e){
            log.error(e.getMessage());
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * 添加manage user
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_SAVE,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response save(@RequestBody ManageUserDTO manageUserDTO){
        ManageUser manageUser = new ManageUser();
        BeanUtils.copyProperties(manageUserDTO,manageUser);
        manageUser.setCreateTime(new Date());
        manageUser.setSex(Gender.MAN);
        manageUserService.saveManageUser(manageUser);
        return new Response();
    }

    /**
     * 跟新manage user
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_UPDATE,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response update(@RequestBody ManageUserDTO manageUserDTO){
        ManageUser manageUser = manageUserService.findByUsername(manageUserDTO.getUsername());
        manageUser.setUserType(manageUserDTO.getUserType());
        manageUser.setName(manageUserDTO.getName());
        manageUser.setUpdateBy(manageUserDTO.getUpdateBy());
        manageUser.setUpdateTime(manageUserDTO.getUpdateTime());
        manageUser.setUpdateTime(new Date());
        manageUserService.update(manageUser);
        return new Response();
    }

    /**
     * 检查添加账号是否被用
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_CHECK_NAME,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response<ValidformRespDTO> checkLoginAccount(@RequestBody ManageUserDTO manageUserDTO){
        Response<ValidformRespDTO> response = new Response<>();
        ManageUser manageUser = manageUserService.findByUsername(manageUserDTO.getUsername());
        ValidformRespDTO validformRespDTO = new ValidformRespDTO();
        if(manageUser == null){
            validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_SUCCESS_INFO);
            validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_SUCCESS_STATUS);
        }else{
            validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_FAIL_INFO);
            validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_FAIL_STATUS);
            response.setSuccess(false);
        }
        response.setData(validformRespDTO);
        return response;
    }

    /**
     * 修改密码
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_CHANGE_PASSWORD,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response changePassword(@RequestBody ManageUserDTO manageUserDTO){
        Response response = new Response();
        ManageUser manageUser = manageUserService.findByUsername(manageUserDTO.getUsername());
        String[] str = manageUserDTO.getPassword().split(",");
        String oldPassword = new Md5Hash(str[0],manageUser.getUsername()+manageUser.getSalt(),2).toHex();
        if(oldPassword.equals(manageUser.getPassword())){
            manageUser.setPassword(str[1]);
            manageUserService.saveManageUser(manageUser);
        }else {
            response.setSuccess(false);
        }
        return response;
    }

    /**
     * 条件查询
     * @param manageUserDTO
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_FIND_BY_SEARCH_CONDITION,
            method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response<PageDTO<ManageUserDTO>> findBySeachCondition(@RequestBody ManageUserDTO manageUserDTO){
        String userName = "%" + manageUserDTO.getUsername() + "%";
        String name = "%" + manageUserDTO.getName() + "%";
        int offset = manageUserDTO.getOffset();
        int limit = manageUserDTO.getLimit();
        Response<PageDTO<ManageUserDTO>> response = new Response<PageDTO<ManageUserDTO>>();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
        Page<ManageUser> page = null;
        try{
            if (StringUtils.isEmpty(manageUserDTO.getName())){
                page = manageUserService.getManageUserPage(pageRequest);
            }else {
                page = manageUserService.getManageUserPage(pageRequest,userName,name);
            }
        }catch (ManageUserServiceException e){
            log.error(e.getMessage());
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        PageDTO<ManageUserDTO> pageDTO = PageUtils.mapper(page,ManageUserDTO.class);
        response.setData(pageDTO);
        return response;
    }
}
