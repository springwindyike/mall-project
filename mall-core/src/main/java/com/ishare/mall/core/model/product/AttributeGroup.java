package com.ishare.mall.core.model.product;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by YinLin on 2015/9/6.
 * Description :
 * Version 1.0
 */
public class AttributeGroup {
    private Integer id;
    private ProductType productType;
    private Set<Attribute> attributes = Sets.newConcurrentHashSet();

}
