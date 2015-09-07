package com.ishare.mall.core.repository.permission;

import com.ishare.mall.core.model.permission.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description : 角色资源
 * Version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor {
    @Query("SELECT r FROM Role r, MemberRole mr WHERE mr.member.id = ?1 AND r.id = mr.role.id")
    List<Role> queryByMemberId(Integer memberId);
}
