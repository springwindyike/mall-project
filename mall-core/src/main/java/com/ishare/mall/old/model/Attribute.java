package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;

import javax.persistence.*;

/**
 * Created by dongqi on 15/7/12.
 *
 * 属性
 */
@Entity
@Table(name = "es_attribute")
public class Attribute extends AbstractEntity {

    @Column(name = "attr_name")
    private String name;
    @Column(name = "attr_value")
    private String value;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private AttributeGroup group;

    public Attribute() {
    }

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeGroup getGroup() {
        return group;
    }

    public void setGroup(AttributeGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", group=" + group +
                '}';
    }
}
