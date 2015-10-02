package com.ishare.mall.crawler.repository;

import com.ishare.mall.crawler.model.FetchSite;
import com.ishare.mall.crawler.model.FetchUrl;
import com.ishare.mall.crawler.model.FetchUrlType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FetchUrlRepository extends JpaRepository<FetchUrl, Long> {

    FetchUrl findByLinkIs(String link);

    List<FetchUrl> findByFetchSiteAndType(FetchSite fetchSite, FetchUrlType type);

    Page<FetchUrl> findByFetchSiteAndType(FetchSite fetchSite, FetchUrlType type, Pageable pageable);

    Page<FetchUrl> findByType(FetchUrlType type, Pageable pageable);
}
