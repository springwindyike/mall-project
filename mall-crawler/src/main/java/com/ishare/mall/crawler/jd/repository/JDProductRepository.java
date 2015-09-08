package com.ishare.mall.crawler.jd.repository;

import com.ishare.mall.crawler.jd.model.JDProduct;
import org.springframework.data.repository.CrudRepository;

public interface JDProductRepository extends CrudRepository<JDProduct, Long> {

    JDProduct findByCode(String code);
}
