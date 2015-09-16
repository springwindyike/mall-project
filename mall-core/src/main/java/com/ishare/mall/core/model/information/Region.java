package com.ishare.mall.core.model.information;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_REGION_NAME;

/**
 * Created by YinLin on 2015/9/16.
 * Description : 地区信息
 * Version 1.0
 */
@Entity
@Table(name = TABLE_REGION_NAME)
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "region_name")
    private String name;

    @Column(name = "region_type")
    private Integer type;

    @ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE}, optional=false)
    @JoinColumn(name = "parent_id")
    private Region parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Region getParent() {
        return parent;
    }

    public void setParent(Region parent) {
        this.parent = parent;
    }
}
