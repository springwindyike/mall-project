package com.ishare.mall.crawler.jd.repository;

import com.ishare.mall.crawler.jd.model.JDProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "products", rel = "products")
public interface JDProductRepository extends JpaRepository<JDProduct, Long> {

    JDProduct findByCode(String code);
}
