package com.ishare.mall.crawler.repository;

import com.ishare.mall.crawler.model.BasePageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasePageDataRepository extends JpaRepository<BasePageData, Long> {

    BasePageData findByLinkIs(String link);

    @Query(nativeQuery = true, value = "select fetch_url_id from spider_base_page_data where link = ?1 and code = ?2")
    Long findByLinkAndCode(String link, String code);
}
