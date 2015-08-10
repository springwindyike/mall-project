package com.ishare.mall.core.service.information.impl;

import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.repository.information.BrandRepository;
import com.ishare.mall.core.service.information.BrandService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
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
 * Created by YinLin on 2015/8/10.
 * Description: 商品类别业务逻辑表
 * Version 1.0
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand findOne(Integer id) {
        return brandRepository.findOne(id);
    }

    @Override
    public Page<Brand> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Product> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Product.class);
        Page<Brand> page = brandRepository.findAll(spec, pageRequest);
        log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    public Logger getLog() {
        return log;
    }

}
