package com.ishare.mall.core.service.information;

import java.util.List;

import com.ishare.mall.core.model.product.Attribute;
import com.ishare.mall.core.model.product.AttributeGroup;

/**
 * Created by YinLin on 2015/10/10.
 * Description :
 * Version 1.0
 */
public interface AttributeGroupService {
    AttributeGroup findOne(Integer id);
    AttributeGroup findByName(String name);
    AttributeGroup save(AttributeGroup attribute);
}
