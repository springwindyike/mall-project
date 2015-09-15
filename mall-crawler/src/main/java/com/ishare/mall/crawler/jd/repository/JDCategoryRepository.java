package com.ishare.mall.crawler.jd.repository;

import com.ishare.mall.crawler.jd.model.JDCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(path = "categories", rel = "categories")
public interface JDCategoryRepository extends JpaRepository<JDCategory, Long> {

    @Query("select u from JDCategory u where u.name like %?1%")
    Page<JDCategory> findByNameLike(String name, Pageable pageable);

    List<JDCategory> findByLinkNotNullAndParentNotNull();

    Page<JDCategory> findByLinkNotNullAndParentNotNull(Pageable pageable);
}
