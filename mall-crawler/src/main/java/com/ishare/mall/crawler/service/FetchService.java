package com.ishare.mall.crawler.service;

import com.ishare.mall.crawler.model.FetchSite;
import com.ishare.mall.crawler.site.DatabasePipeline;
import com.ishare.mall.crawler.site.amazon.processor.AmazonPageProcessor;
import com.ishare.mall.crawler.site.dangdang.processor.DangDangPageProcessor;
import com.ishare.mall.crawler.site.jd.processor.JDPageProcessor;
import com.ishare.mall.crawler.site.processor.CategoryPageProcessor;
import com.ishare.mall.crawler.site.processor.ListPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
public class FetchService {

    private static final Logger log = LoggerFactory.getLogger(FetchService.class);

    @Autowired
    CategoryPageProcessor categoryPageProcessor;

    @Autowired
    ListPageProcessor listPageProcessor;

    @Autowired
    JDPageProcessor jdPageProcessor;

    @Autowired
    AmazonPageProcessor amazonPageProcessor;

    @Autowired
    DangDangPageProcessor dangDangPageProcessor;

    @Autowired
    DatabasePipeline databasePipeline;

    public void fetchCategoryUrl(String url, boolean store) {
        Spider spider = Spider.create(categoryPageProcessor).addUrl(url);
        if (store) {
            spider.addPipeline(databasePipeline);
        }

        spider.thread(Runtime.getRuntime().availableProcessors()).run();

        log.debug("{}, {}", spider.getSite(), spider.getStatus());
    }

    public void fetchListUrl(boolean store, String... urls) {
        Spider spider = Spider.create(listPageProcessor).addUrl(urls);
        if (store) {
            spider.addPipeline(databasePipeline);
        }

        spider.thread(Runtime.getRuntime().availableProcessors()).run();

        log.debug("{}, {}", spider.getSite(), spider.getStatus());
    }

    public void fetchPageUrl(FetchSite fetchSite, boolean store, String... urls) {

        PageProcessor pageProcessor = null;

        if (FetchSite.JD.equals(fetchSite)) {
            pageProcessor = jdPageProcessor;
        } else if (FetchSite.AMAZON.equals(fetchSite)) {
            pageProcessor = amazonPageProcessor;
        } else if (FetchSite.DANG_DANG.equals(fetchSite)) {
            pageProcessor = dangDangPageProcessor;
        }

        Spider spider = Spider.create(pageProcessor).addUrl(urls);
        if (store) {
            spider.addPipeline(databasePipeline);
        }

        spider.thread(Runtime.getRuntime().availableProcessors()).run();

        log.info("{}, {}, {}, {}", fetchSite, spider.getSite(), spider.getStatus(), urls.length);
    }
}
