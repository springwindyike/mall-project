package com.ishare.mall.crawler.jd.repository;

import com.ishare.mall.crawler.jd.model.JDCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dongqi on 15/9/7.
 */
public interface JDCategoryRepository extends JpaRepository<JDCategory, Long> {

    List<JDCategory> findByLinkNotNullAndParentNotNull();
}
