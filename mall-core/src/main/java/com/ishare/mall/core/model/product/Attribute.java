package com.ishare.mall.core.model.product;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ATTRIBUTE_NAME;

/**
 * Created by YinLin on 2015/9/6.
 * Description : 选择属性
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ATTRIBUTE_NAME)
public class Attribute {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //属性名称
    @Column(name = "attr_name")
    private String name;
    //属性描述
    @Column(name = "attr_desc")
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_group_id")
    private AttributeGroup attributeGroup;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
    
    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

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
