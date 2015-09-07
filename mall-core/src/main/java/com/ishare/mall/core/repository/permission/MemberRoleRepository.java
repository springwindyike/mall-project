package com.ishare.mall.core.repository.permission;

import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.permission.MemberRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description : 中间表查找
 * Version 1.0
 */
public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer>, JpaSpecificationExecutor {
    Page<MemberRole> findByRoleId(Integer roleId, Pageable pageable);

    List<MemberRole> findByMember(Member member);
}
