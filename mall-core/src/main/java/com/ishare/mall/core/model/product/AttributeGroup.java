package com.ishare.mall.core.model.product;

import javax.persistence.*;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ATTRIBUTE_GROUT_NAME;

/**
 * Created by YinLin on 2015/9/6.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ATTRIBUTE_GROUT_NAME)
public class AttributeGroup {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    //属性名称
    @Column(name = "attr_group_name")
    private String attriGroupName;
    
    public String getAttriGroupName() {
		return attriGroupName;
	}

	public void setAttriGroupName(String attriGroupName) {
		this.attriGroupName = attriGroupName;
	}

	

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
