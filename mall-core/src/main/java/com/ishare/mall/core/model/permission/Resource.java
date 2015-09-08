package com.ishare.mall.core.model.permission;

import com.ishare.mall.core.model.base.BaseEntity;

/**
 * Created by YinLin on 2015/9/8.
 * Description : 资源 菜单 OR 按钮 第二期需要上的功能
 * Version 1.0
 */
public class Resource extends BaseEntity {

    private Integer id;

    private String name;

    private String url;

    private String permission;

    private Resource resource;

    private String parentIds;

    private Boolean available = Boolean.FALSE;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
