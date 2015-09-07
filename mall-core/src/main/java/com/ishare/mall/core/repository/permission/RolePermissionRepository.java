package com.ishare.mall.core.repository.permission;

import com.ishare.mall.core.model.permission.Role;
import com.ishare.mall.core.model.permission.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer>, JpaSpecificationExecutor {
    Set<RolePermission> findByRole(Role role);
}
