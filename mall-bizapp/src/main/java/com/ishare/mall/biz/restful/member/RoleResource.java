package com.ishare.mall.biz.restful.member;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberRoleDTO;
import com.ishare.mall.common.base.dto.permission.RoleDTO;
import com.ishare.mall.core.model.permission.Role;
import com.ishare.mall.core.service.permission.RoleService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Role.REQUEST_MAPPING)
public class RoleResource {

    private static final Logger log = LoggerFactory.getLogger(PermissionResource.class);

    @Autowired
    private RoleService roleService;

    /**
     * 通过用户账号获取所有的用户权限
     * @param account 用户账户
     * @return 返回 MemberPermissionDTO JSON
     */
    @RequestMapping(value       = "/{account}",
            method      = RequestMethod.GET,
            headers     = "Accept=application/xml, application/json",
            produces    = {"application/json", "application/xml"})
    public MemberRoleDTO getMemberAllRoles(@NotEmpty @PathVariable("account") String account) {
        log.debug(account);
        //通过登录账号查找所有权限
        List<Role> roles = roleService.findByAccount(account);
        MemberRoleDTO roleDTO = new MemberRoleDTO();
        roleDTO.setAccount(account);
        if (roles != null && roles.size() > 0) {
            //强制转换DTO
            roleDTO.setRoleDTOs((List<RoleDTO>) MapperUtils.mapAsList(roles, RoleDTO.class));
        }
        log.debug(roleDTO.toString());
        return roleDTO;
    }

    public static Logger getLog() {
        return log;
    }
}
