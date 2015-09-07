package com.ishare.mall.core.repository.permission;

import com.ishare.mall.core.model.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor {
    @Query("SELECT p FROM Permission p, RolePermission rp, MemberRole mr WHERE mr.member.id=?1 AND p.id = rp.permission.id AND rp.role.id = mr.role.id")
    List<Permission> queryByMemberId(Integer memberId);
}
