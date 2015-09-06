package com.ishare.mall.core.model.product;

/**
 * Created by YinLin on 2015/9/6.
 * Description : 选择属性
 * Version 1.0
 */
public class Attribute {
    //主键ID
    private Integer id;
    //属性名称
    private String name;
    //属性描述
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
