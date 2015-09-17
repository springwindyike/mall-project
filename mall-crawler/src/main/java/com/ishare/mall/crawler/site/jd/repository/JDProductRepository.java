package com.ishare.mall.crawler.site.jd.repository;

import com.ishare.mall.crawler.site.jd.model.JDProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(path = "products", rel = "products")
public interface JDProductRepository extends JpaRepository<JDProduct, Long>, JpaSpecificationExecutor {

    JDProduct findByCode(String code);

    List<JDProduct> findByLink(String link);

    Page<JDProduct> findByNameContainingAndPriceBetween(String name, double priceMin, double priceMax, Pageable pageable);
}
