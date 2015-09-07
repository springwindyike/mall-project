package com.ishare.mall.crawler.jd;

import com.google.common.collect.Lists;
import com.ishare.mall.crawler.jd.processor.JDCategoryPageProcessor;
import com.ishare.mall.crawler.jd.processor.JDListPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

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
    JDCategoryRepository jdCategoryRepository;
    @Autowired
    JDPipeline jdPipeline;
    @Autowired
    JDSpiderListener listener;
    private Spider spider;

    public JDCrawler() {
        log.info("初始化");
    }

    //@PostConstruct
    public void start() {

        log.info("开始");

//        spider = Spider.create(jdCategoryPageProcessor);
//        spider.addUrl("http://www.jd.com/allSort.aspx");
//        spider.addPipeline(jdPipeline);
//        spider.thread(5);
//        spider.run();

        List<SpiderListener> listeners = Lists.newArrayList();
        listeners.add(listener);

        List<JDCategory> categoryList = jdCategoryRepository.findByLinkNotNullAndParentNotNull();
        log.debug("{}", categoryList.size());
        spider = Spider.create(jdListPageProcessor);
        spider.addPipeline(jdPipeline);
        spider.addUrl("http://list.jd.com/670-677-683.html");
        spider.setSpiderListeners(listeners);
        spider.thread(5);
        spider.run();
    }

    @PreDestroy
    public void stop() {
        log.info("结束");

        spider.stop();
    }
}
