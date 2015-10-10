package com.ishare.mall.core.service.information;

import com.ishare.mall.core.model.product.Attribute;

import java.util.List;

/**
 * Created by YinLin on 2015/10/10.
 * Description :
 * Version 1.0
 */
public interface AttributeService {
    Attribute findOne(Integer id);
    Attribute findByName(String name);
    Attribute save(Attribute attribute);
    List<Attribute> save(List<Attribute> attributes);
}
