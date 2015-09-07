package com.ishare.mall.core.model.permission;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_PERMISSION_NAME;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_PERMISSION_NAME)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permission_name")
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
