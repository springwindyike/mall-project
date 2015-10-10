package com.ishare.mall.core.service.information.impl;

import com.ishare.mall.core.model.product.Attribute;
import com.ishare.mall.core.repository.information.AttributeRepository;
import com.ishare.mall.core.service.information.AttributeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YinLin on 2015/10/10.
 * Description : 属性表
 * Version 1.0
 */
@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    private AttributeRepository attributeRepository;

    @Override
    public Attribute findOne(Integer id) {
        return attributeRepository.findOne(id);
    }

    @Override
    public Attribute findByName(String name) {
        List<Attribute> attributes = attributeRepository.findByName(name);
        return attributes.size() > 0 ? attributes.get(0) : null;
    }

    @Override
    public Attribute save(Attribute attribute) {
        if (attribute != null) {
            attributeRepository.save(attribute);
        }
        return attribute;
    }

    @Override
    public List<Attribute> save(List<Attribute> attributes) {
        if (attributes != null && attributes.size() > 0) {
            attributeRepository.save(attributes);
        }
        return attributes;
    }
}
