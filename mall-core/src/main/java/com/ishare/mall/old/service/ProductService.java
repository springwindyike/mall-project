package com.ishare.mall.old.service;

import com.ishare.mall.old.model.Product;

/**
 * Created by dongqi on 15/7/14.
 */
public interface ProductService {

    Product addProduct(Product product);
    public Product findProductById(Long id);

}
