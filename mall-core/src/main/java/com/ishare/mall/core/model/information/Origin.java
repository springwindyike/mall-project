package com.ishare.mall.core.model.information;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORIGIN_NAME;

/**
 * Created by YinLin on 2015/9/19.
 * Description: 采集第三方信息
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ORIGIN_NAME)
public class Origin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "origin_name")
    private String name;
    @Column(name = "origin_description")
    private String description;
    @Column(name = "origin_link")
    private String link;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
