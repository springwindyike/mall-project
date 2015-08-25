package com.ishare.mall.test.repository;

import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.repository.product.ProductRepository;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YinLin on 2015/8/25.
 * Description :
 * Version 1.0
 */
public class ProductRepositoryTests extends RepositoryTestTemplate {
    @Autowired
    private ProductRepository repository;

    @Test
    public void testName() throws Exception {
    }

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Override
    public void testCreate() {

    }

    @Override
    public void testRetrieve() {
        Product product = repository.findOne(1);
        System.out.println(product.getBasePrice());
    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testDelete() {

    }
}
