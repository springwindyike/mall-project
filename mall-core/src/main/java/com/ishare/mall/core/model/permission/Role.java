package com.ishare.mall.core.model.permission;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.member.Member;

import javax.persistence.*;
import java.util.Set;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ROLE_NAME;
import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_MEMBER_ROLE_NAME;


/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ROLE_NAME)
public class Role extends BaseEntity {
    /**角色Id**/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**角色名称**/
    @Column(name = "role_name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
