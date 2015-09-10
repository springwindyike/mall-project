package com.ishare.mall.core.service.product.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.repository.product.ProductTypeRepository;
import com.ishare.mall.core.service.product.ProductTypeService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;

/**
 * Created by ZhangZhaoxin on 2015/8/18.
 * Description:
 * Version 1.0
 */
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
    private final static Logger log = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
    @Autowired
    private ProductTypeRepository productTypeRepository;
    
    @SuppressWarnings("unchecked")
    @Override
    public Page<ProductType> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<ProductType> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), ProductType.class);
        Page<ProductType> page = productTypeRepository.findAll(spec, pageRequest);
        log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    @Override
    public ProductType findOne(Integer id) {
        return productTypeRepository.findOne(id);
    }
    
    @Override
    public List<ProductType> findByLevel(Integer id) {
	    List<ProductType> productType = productTypeRepository.findByLevel(id);
	    if (productType == null || productType.size() == 0) return null;
	    return productType;
    }
    
    public static Logger getLog() {
        return log;
    }

	@Override
	public List<ProductType> findByParentId(Integer pariendId) {
		// TODO Auto-generated method stub
		 List<ProductType> productType = productTypeRepository.findByParendId(pariendId);
		    if (productType == null || productType.size() == 0) return null;
		    return productType;
	}
}
