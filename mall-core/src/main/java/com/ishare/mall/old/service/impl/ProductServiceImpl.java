package com.ishare.mall.old.service.impl;

import com.ishare.mall.core.repository.ProductRepository;
import com.ishare.mall.old.model.Product;
import com.ishare.mall.old.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dongqi on 15/7/14.
 */
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return null;
    }
    
    /**
     * 根据Id查询商品
     * 
     */
    public Product findProductById(Long id){
        return null;
    }
}
