package com.ishare.mall.crawler.amazon.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class AmazonCategoryPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(AmazonCategoryPageProcessor.class);

    private Site site = Site.me().setRetryTimes(30).setSleepTime(10000);

    public static void main(String[] args) {
        final String url = "http://www.amazon.cn/gp/site-directory/ref=nav_shopall_btn";
        Spider.create(new AmazonCategoryPageProcessor()).addUrl(url).run();
    }

    @Override
    public void process(Page page) {
        log.debug("抓取亚马逊中国分类页面");

        Document doc = page.getHtml().getDocument();
        Elements elements = doc.select("div#siteDirectory > div.a-row > div");
        for (Element row : elements) {
            String name = row.select("div.a-row.a-spacing-extra-large.a-spacing-top-small > span > a").text();
            log.debug("Level 1 {}", name);

            Elements elementsLevel2 = row.select("div.a-spacing-top-mini > div.a-column.a-span3 > div.a-column.a-span12 > div.a-row.a-spacing-small");
            for (Element rowLevel2 : elementsLevel2) {
                String nameLevel2 = rowLevel2.select("span.a-text-bold > a.nav_a").text();
                log.debug("Level 2 \t{}", nameLevel2);
                for (Element rowLevel3 : rowLevel2.select("div.a-row > ul > li > span > span > a")) {
                    String nameLevel3 = rowLevel3.text();
                    String linkLevel3 = rowLevel3.attr("href");
                    log.debug("Level 3 \t\t{}", nameLevel3);
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
