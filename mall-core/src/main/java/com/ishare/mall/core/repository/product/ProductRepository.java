package com.ishare.mall.core.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.product.Product;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor {
    @Query("SELECT p FROM t_product p WHERE p.code=?1")
    List<Product> findByCode(String code);
}
