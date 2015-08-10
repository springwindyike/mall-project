package com.ishare.mall.core.service.product.impl;

import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.repository.ProductRepository;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.old.utils.DynamicSpecifications;
import com.ishare.mall.old.utils.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by YinLin on 2015/7/30.
 * Description:
 * Version 1.0
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Product> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Product> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Product.class);
        Page<Product> page = productRepository.findAll(spec, pageRequest);
        log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    @Override
    public Product findOne(Integer id) {
        return productRepository.findOne(id);
    }

    public static Logger getLog() {
        return log;
    }
}
