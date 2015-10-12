package com.ishare.mall.crawler.repository;

import com.ishare.mall.crawler.model.FetchSite;
import com.ishare.mall.crawler.model.FetchUrl;
import com.ishare.mall.crawler.model.FetchUrlType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FetchUrlRepository extends JpaRepository<FetchUrl, Long> {

    FetchUrl findByLinkIs(String link);

    List<FetchUrl> findByFetchSiteAndType(FetchSite fetchSite, FetchUrlType type);

    @Query(nativeQuery = true, value = "select link from spider_fetch_url where fetch_site = ?1 and type = ?2 and is_valid = 'Y'")
    List<String> findByFetchSiteAndType(String site, String type);

    Page<FetchUrl> findByFetchSiteAndType(FetchSite fetchSite, FetchUrlType type, Pageable pageable);

    Page<FetchUrl> findByTypeAndValidIsTrue(FetchUrlType type, Pageable pageable);

    List<FetchUrl> findByIdIn(Collection<Long> ids);
}
