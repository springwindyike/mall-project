package com.ishare.mall.core.service.permission.impl;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.permission.Permission;
import com.ishare.mall.core.repository.permission.MemberRoleRepository;
import com.ishare.mall.core.repository.permission.PermissionRepository;
import com.ishare.mall.core.repository.permission.RolePermissionRepository;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.permission.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private MemberRoleRepository memberRoleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public List<Permission> findByAccount(String account) {
        Member member = memberService.findByAccount(account);
        if (member == null) return null;
        return permissionRepository.queryByMemberId(member.getId());
    }

    @Override
    public Set<String> findAllPermissionByAccount(String account) {
        List<Permission> permissions = this.findByAccount(account);
        Set<String> perSet = Sets.newConcurrentHashSet();
        if (permissions != null && permissions.size() > 0) {
            for (Permission permission : permissions) {
                perSet.add(permission.getName());
            }
        }
        return perSet;
    }

    public static Logger getLog() {
        return log;
    }
}
