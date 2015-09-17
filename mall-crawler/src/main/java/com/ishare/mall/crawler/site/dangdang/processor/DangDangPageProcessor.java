package com.ishare.mall.crawler.site.dangdang.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class DangDangPageProcessor implements PageProcessor {

    private Site site = Site.me();

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }
}
