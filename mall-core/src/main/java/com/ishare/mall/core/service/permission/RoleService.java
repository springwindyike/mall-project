package com.ishare.mall.core.service.permission;

import com.ishare.mall.core.model.permission.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
public interface RoleService {
    /**
     * 通过用户账户得到用户所有的角色
     * @param account
     * @return
     */
    List<Role> findByAccount(String account);

    /**
     * 通过用户账户获取用户所有的角色
     * @param account
     * @return
     */
    Set<String> findAllRoleByAccount(String account);
}
