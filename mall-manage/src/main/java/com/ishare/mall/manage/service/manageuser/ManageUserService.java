package com.ishare.mall.manage.service.manageuser;

import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;

/**
 * Created by Zhangzhaoxin on 2015/10/19.
 * Description :
 * Version 1.0
 */
public interface ManageUserService {
    /**
     * 获取当前登录用户
     * @param username
     * @return
     */
    CurrentManageUserDTO getCurrentManageUser(String username);

    ManageUserDTO findByUsername(String username);
}
