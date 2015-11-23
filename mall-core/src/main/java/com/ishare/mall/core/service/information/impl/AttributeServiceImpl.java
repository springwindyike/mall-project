package com.ishare.mall.core.service.information.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.exception.BrandServiceException;
import com.ishare.mall.core.model.product.Attribute;
import com.ishare.mall.core.repository.information.AttributeRepository;
import com.ishare.mall.core.service.information.AttributeService;

/**
 * Created by YinLin on 2015/10/10.
 * Description : 属性表
 * Version 1.0
 */
@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {
	  private static final Logger log = LoggerFactory.getLogger(AttributeServiceImpl.class);
    @Autowired
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
		try {
			Attribute returnAttribute = attributeRepository.save(attribute);
			return returnAttribute;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BrandServiceException("属性保存失败");
		}
	}

    @Override
    public List<Attribute> save(List<Attribute> attributes) {
        if (attributes != null && attributes.size() > 0) {
            attributeRepository.save(attributes);
        }
        return attributes;
    }
}
