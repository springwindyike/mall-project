package com.ishare.mall.core.service.permission.impl;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.permission.Role;
import com.ishare.mall.core.repository.permission.RoleRepository;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.permission.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findByAccount(String account) {
        Member member = memberService.findByAccount(account);
        if (member == null) return null;
        return roleRepository.queryByMemberId(member.getId());
    }

    @Override
    public Set<String> findAllRoleByAccount(String account) {
        List<Role> roles = this.findByAccount(account);
        Set<String> roSet = Sets.newConcurrentHashSet();
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                roSet.add(role.getName());
            }
        }
        return roSet;
    }
}
