package com.ishare.mall.crawler.jd;

import com.ishare.mall.crawler.jd.model.JDProduct;
import com.ishare.mall.crawler.jd.processor.JDCategoryPageProcessor;
import com.ishare.mall.crawler.jd.processor.JDListPageProcessor;
import com.ishare.mall.crawler.jd.processor.JDPageProcessor;
import com.ishare.mall.crawler.jd.repository.JDCategoryRepository;
import com.ishare.mall.crawler.jd.repository.JDProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by dongqi on 15/9/7.
 */
@Component
public class JDCrawler {

    private static final Logger log = LoggerFactory.getLogger(JDCrawler.class);
    @Autowired
    JDCategoryPageProcessor jdCategoryPageProcessor;
    @Autowired
    JDListPageProcessor jdListPageProcessor;
    @Autowired
    JDPageProcessor jdPageProcessor;
    @Autowired
    JDCategoryRepository jdCategoryRepository;
    @Autowired
    JDProductRepository jdProductRepository;
    @Autowired
    JDPipeline jdPipeline;
    @Autowired
    JDSpiderListener listener;

    private Spider spider;

    public JDCrawler() {
        log.info("初始化 spider {}", spider);
    }

    public Spider init() {
        spider = Spider.create(jdCategoryPageProcessor);
        spider.addUrl("http://www.jd.com/allSort.aspx");
        spider.addPipeline(jdPipeline);
        spider.thread(5);
        spider.run();

        return spider;
    }

    public void start(PageProcessor processor, String... url) {

        log.info("开始抓取{}的内容", url);
        spider = Spider.create(processor);
        log.debug("spider {} status {}", spider, spider.getStatus());
        spider.addUrl(url);
        spider.addPipeline(jdPipeline);
        spider.thread(5);
        spider.run();

    }

    public List<JDProduct> fetchPage(String url) {
        spider = Spider.create(jdPageProcessor).addUrl(url).addPipeline(jdPipeline).thread(1);

        spider.run();

        return jdProductRepository.findByLink(url);
    }

    @PreDestroy
    public void stop() {
        log.info("结束");
        if (spider != null) {
            spider.stop();
        }
    }
}
