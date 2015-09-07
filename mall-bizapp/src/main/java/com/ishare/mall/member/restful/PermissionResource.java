package com.ishare.mall.member.restful;

import com.google.common.collect.Lists;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberPermissionDTO;
import com.ishare.mall.common.base.dto.permission.PermissionDTO;
import com.ishare.mall.core.model.permission.Permission;
import com.ishare.mall.core.service.permission.PermissionService;
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
@RequestMapping(APPURIConstant.Permission.REQUEST_MAPPING)
public class PermissionResource {
    private static final Logger log = LoggerFactory.getLogger(PermissionResource.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * 通过用户账号获取所有的用户权限
     * @param account
     * @return
     */
    @RequestMapping(value       = "/{account}",
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json", "application/xml"})
    public MemberPermissionDTO getMemberAllPermissions(@NotEmpty @PathVariable("account") String account) {
        log.debug(account);
        //通过登录账号查找所有权限
        List<Permission> permissions = permissionService.findByAccount(account);
        MemberPermissionDTO memberPermissionDTO = new MemberPermissionDTO();
        memberPermissionDTO.setAccount(account);
        if (permissions != null && permissions.size() > 0) {
            List<PermissionDTO> permissionDTOs = Lists.newArrayList();
            //强制转换DTO
            permissionDTOs = (List<PermissionDTO>) MapperUtils.mapAsList(permissions, PermissionDTO.class);
            memberPermissionDTO.setPermissionDTOs(permissionDTOs);
        }
        log.debug(memberPermissionDTO.toString());
        return memberPermissionDTO;
    }

    public static Logger getLog() {
        return log;
    }
}
