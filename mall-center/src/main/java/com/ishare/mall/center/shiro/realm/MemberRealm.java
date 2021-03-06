package com.ishare.mall.center.shiro.realm;

import com.ishare.mall.center.service.channel.ChannelService;
import com.ishare.mall.center.service.member.MemberService;
import com.ishare.mall.center.shiro.exception.ChannelClosedException;
import com.ishare.mall.center.shiro.exception.AccountDeletedException;
import com.ishare.mall.center.shiro.exception.NoPermissionLoginException;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.common.base.dto.member.MemberDTO;
import com.ishare.mall.common.base.dto.member.MemberPermissionDTO;
import com.ishare.mall.common.base.dto.member.MemberRoleDTO;
import com.ishare.mall.common.base.dto.permission.PermissionDTO;
import com.ishare.mall.common.base.dto.permission.RoleDTO;
import com.ishare.mall.common.base.enumeration.MemberType;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YinLin on 2015/9/6.
 * Description : member认证相关
 * Version 1.0
 */
public class MemberRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MemberRealm.class);

    private String bizAppUrl;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ChannelService channelService;

    public MemberRealm() {

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        ResponseEntity<MemberRoleDTO> roleResultDTO = null;

        ResponseEntity<MemberPermissionDTO> permissionResultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        log.debug(this.buildBizAppURI(APPURIConstant.Permission.REQUEST_MAPPING, "/" + account));
        //获取所有权限
        permissionResultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.Permission.REQUEST_MAPPING, "/" + account), MemberPermissionDTO.class);
        MemberPermissionDTO memberPermissionDTO = permissionResultDTO.getBody();
        //获取所有角色
        roleResultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.Role.REQUEST_MAPPING, "/" + account), MemberRoleDTO.class);
        MemberRoleDTO memberRoleDTO = roleResultDTO.getBody();
        //设置角色
        authorizationInfo.setRoles(this.listRole2SetString(memberRoleDTO.getRoleDTOs()));
        //设置权限
        authorizationInfo.setStringPermissions(this.listPermission2SetString(memberPermissionDTO.getPermissionDTOs()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        String account = (String) token.getPrincipal();
        MemberDTO memberDTO = memberService.findByAccount(account);
        if (memberDTO == null) {
            log.debug("account : 用户不存在！");
            throw new UnknownAccountException();
        }
        if (!memberDTO.getUse()) {
            log.debug("use:用户被禁用！");
            throw new AccountDeletedException();
        }
        ChannelDTO channelDTO = channelService.findByChannelId(memberDTO.getChannelId());
        if (!channelDTO.getVisible()) {
            log.debug("channelId:用户渠道已关闭！");
            throw new ChannelClosedException();
        }
        MemberType memberType = memberDTO.getMemberType();
        log.debug("用户类型 : " + memberType.getName());
        if(!MemberType.ADMIN.equals(memberType)){
            log.debug("warnning : 没有权限登录！");
            throw new NoPermissionLoginException("没有权限登录");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                memberDTO.getAccount(),
                memberDTO.getPassword(),
                ByteSource.Util.bytes(memberDTO.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 解析用户角色
     * @param roleDTOs
     * @return
     */
    private Set<String> listRole2SetString(List<RoleDTO> roleDTOs) {
        Set<String> sets = new HashSet<>();
        if (roleDTOs != null && roleDTOs.size() > 0) {
            for (RoleDTO roleDTO : roleDTOs) {
                sets.add(roleDTO.getName());
            }
        }
        return sets;
    }

    /**
     * 解析用户权限
     * @param permissionDTOs
     * @return
     */
    private Set<String> listPermission2SetString(List<PermissionDTO> permissionDTOs) {
        Set<String> sets = new HashSet<>();
        if (permissionDTOs != null && permissionDTOs.size() > 0) {
            for (PermissionDTO permissionDTO : permissionDTOs) {
                sets.add(permissionDTO.getName());
            }
        }
        return sets;
    }

    /**
     * 基础的path和apiPath
     * @param moduleRequestMapping
     * @param apiRequestMapping
     * @return
     */
    protected String buildBizAppURI(String moduleRequestMapping, String apiRequestMapping) {
        return this.bizAppUrl + moduleRequestMapping + apiRequestMapping;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    public String getBizAppUrl() {
        return bizAppUrl;
    }

    public static Logger getLog() {
        return log;
    }

    public void setBizAppUrl(String bizAppUrl) {
        this.bizAppUrl = bizAppUrl;
    }
}
