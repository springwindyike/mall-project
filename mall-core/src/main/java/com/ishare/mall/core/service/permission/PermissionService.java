package com.ishare.mall.core.service.permission;

import com.ishare.mall.core.model.permission.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public interface PermissionService {
    /**
     * 通过用户账户获取用户所有的权限
     * @param account
     * @return
     */
    List<Permission> findByAccount(String account);

    /**
     * 通过账户获取用户所有权限字符串
     * @param account
     * @return
     */
    Set<String> findAllPermissionByAccount(String account);

}
