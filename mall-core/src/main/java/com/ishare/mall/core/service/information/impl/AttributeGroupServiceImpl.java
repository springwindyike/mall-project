package com.ishare.mall.core.service.information.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.product.AttributeGroup;
import com.ishare.mall.core.repository.information.AttributeGroupRepository;
import com.ishare.mall.core.service.information.AttributeGroupService;

/**
 * Created by YinLin on 2015/10/10.
 * Description : 属性表
 * Version 1.0
 */
@Service
@Transactional
public class AttributeGroupServiceImpl implements AttributeGroupService {
	 @Autowired
	    private AttributeGroupRepository attributeGroupRepository;
	@Override
	public AttributeGroup findOne(Integer id) {
		return attributeGroupRepository.findOne(id);}

	@Override
	public AttributeGroup findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeGroup save(AttributeGroup attributeGroup) {
        if (attributeGroup != null) {
        	attributeGroupRepository.save(attributeGroup);
        }
        return attributeGroup;
    }

}
