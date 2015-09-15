package com.ishare.mall.crawler.jd.repository;

import com.ishare.mall.crawler.jd.model.JDProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "products", rel = "products")
public interface JDProductRepository extends JpaRepository<JDProduct, Long>, JpaSpecificationExecutor {

    JDProduct findByCode(String code);

    //@Query("select u from JDProduct u where u.name like %?1%")
    Page<JDProduct> findByNameContainingAndPriceBetween(String name, double priceMin, double priceMax, Pageable pageable);
}
