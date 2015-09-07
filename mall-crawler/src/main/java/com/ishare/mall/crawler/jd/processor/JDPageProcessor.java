package com.ishare.mall.crawler.jd.processor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;

@Component
public class JDPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(JDPageProcessor.class);

    private Site site = Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36").setRetryTimes(30).setSleepTime(10000);

    public static void main(String[] args) {
        Spider.create(new JDPageProcessor())
                .addUrl("http://item.jd.com/985288.html")
                .addUrl("http://item.jd.com/1041474.html")
                .setDownloader(new SeleniumDownloader("/Users/dongqi/Dev/chromedriver"))
                .thread(1).run();
    }

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        Elements tag = document.select("div.breadcrumb");

        String name = document.select("div#name > h1").text();
        String price = document.select("div#summary-price > div.dt").text();

        log.debug("TAG {}", tag.text());
        log.debug("[{}], [{}]", name, price);

        Map<String, String> attributes = Maps.newHashMap();
        Elements lis = document.select("ul#parameter2.p-parameter-list > li");
        for (Element li : lis) {
            String val = li.attr("title").trim();
            String key = li.text().trim();
            key = key.replaceAll(val, "").trim();

            attributes.put(key, val);
            log.debug("商品介绍：{} = {}", key, val);
        }

        List<String> introImgs = Lists.newArrayList();
        for (Element img : document.select("div.detail-content-item >  > img")) {
//            String imgSrc = img.attr("src").trim();
//            introImgs.add(imgSrc);
            log.debug("商品介绍图片 {}", img);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
