package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dongqi on 15/7/12.
 *
 * 属性组
 */
@Entity
@Table(name = "es_attribute_group")
public class AttributeGroup extends AbstractEntity {

    @Column(name = "group_name")
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Attribute> attributes = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "AttributeGroup{" + getId() + ", " +
                "name='" + name + '\'' +
                '}';
    }
}
