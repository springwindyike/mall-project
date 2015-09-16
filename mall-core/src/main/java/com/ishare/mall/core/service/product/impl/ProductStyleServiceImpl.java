package com.ishare.mall.core.service.product.impl;

import com.ishare.mall.core.model.product.ProductStyle;
import com.ishare.mall.core.repository.product.ProductStyleRepository;
import com.ishare.mall.core.service.product.ProductStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanghao on 2015/9/16.
 */
@Service
@Transactional
public class ProductStyleServiceImpl implements ProductStyleService{
    @Autowired
    ProductStyleRepository productStyleRepository;
    @Override
    public List<ProductStyle> findByProduct(Integer id) {
        return productStyleRepository.findByProduct(id);
    }
}
