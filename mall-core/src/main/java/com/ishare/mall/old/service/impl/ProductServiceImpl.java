package com.ishare.mall.old.service.impl;

import com.ishare.mall.old.model.Product;
import com.ishare.mall.old.repository.ProductRepository;
import com.ishare.mall.old.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dongqi on 15/7/14.
 */
@Service
@Transactional
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
    	Product findOne = productRepository.findOne(id);
    	return findOne;
    }
}
