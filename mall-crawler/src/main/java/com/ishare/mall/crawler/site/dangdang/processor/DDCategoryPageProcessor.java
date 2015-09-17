package com.ishare.mall.crawler.site.dangdang.processor;

import com.google.common.collect.Lists;
import com.ishare.mall.crawler.model.FetchSite;
import com.ishare.mall.crawler.model.FetchUrl;
import com.ishare.mall.crawler.model.FetchUrlType;
import com.ishare.mall.crawler.site.FetchConstant;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Component
public class DDCategoryPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(DDCategoryPageProcessor.class);

    private Site site = Site.me();

    @Override
    public void process(Page page) {
        List<FetchUrl> fetchUrls = Lists.newArrayList();
        Document document = page.getHtml().getDocument();

        Elements links = document.select("div#bd").select("a");
        for (Element _link : links) {
            String link = _link.attr("href");
            String name = _link.text();
            if (StringUtils.isBlank(link) || StringUtils.isBlank(name)) continue;
            log.debug("{}, {}", link, name);

            FetchUrl fetchUrl = new FetchUrl();
            fetchUrl.setName(name);
            fetchUrl.setLink(link);
            fetchUrl.setValid(Boolean.TRUE);
            fetchUrl.setFetchSite(FetchSite.DANG_DANG);
            fetchUrl.setType(FetchUrlType.LIST);
            fetchUrls.add(fetchUrl);
        }

        page.getResultItems().put(FetchConstant.KEY_CLASS, FetchUrl.class);
        page.getResultItems().put(FetchConstant.KEY_ITEM, fetchUrls);
    }

    @Override
    public Site getSite() {
        return site;
    }

    /*
    public static void main(String[] args) {
        Spider.create(new DDCategoryPageProcessor()).addUrl("http://category.dangdang.com/").run();
    }
    */
}
