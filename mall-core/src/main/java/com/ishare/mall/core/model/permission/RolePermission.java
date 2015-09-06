package com.ishare.mall.core.model.permission;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ROLE_PERMISSION;

/**
 * Created by YinLin on 2015/9/6.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ROLE_PERMISSION)
public class RolePermission extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
