package com.ishare.mall.crawler.repository;

import com.ishare.mall.crawler.model.BasePageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasePageDataRepository extends JpaRepository<BasePageData, Long> {

    BasePageData findByLinkIs(String link);
}
