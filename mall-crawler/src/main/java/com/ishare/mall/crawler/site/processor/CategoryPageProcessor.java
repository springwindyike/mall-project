package com.ishare.mall.crawler.site.processor;

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
public class CategoryPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(CategoryPageProcessor.class);

    private Site site = Site.me().setTimeOut(10000).setSleepTime(5).setUserAgent(FetchConstant.USER_AGENT);

    @Override
    public void process(Page page) {
        String url = page.getUrl().get();
        String root = "div";
        FetchSite fetchSite = null;
        if (url.contains("jd.com")) {
            root = "div#allsort.w";
            fetchSite = FetchSite.JD;
        } else if (url.contains("dangdang.com")) {
            root = "div#bd";
            fetchSite = FetchSite.DANG_DANG;
        } else if (url.contains("amazon.cn")) {
            root = "div#siteDirectory";
            fetchSite = FetchSite.AMAZON;
        }

        List<FetchUrl> fetchUrls = Lists.newArrayList();
        Document document = page.getHtml().getDocument();

        Elements links = document.select(root).select("a");
        for (Element _link : links) {
            String link = _link.attr("href");
            String name = _link.text();
            if (StringUtils.isBlank(link) || StringUtils.isBlank(name)) continue;
            log.debug("{}, {}", link, name);

            FetchUrl fetchUrl = new FetchUrl();
            fetchUrl.setName(name);
            fetchUrl.setLink(link);
            fetchUrl.setValid(Boolean.TRUE);
            fetchUrl.setFetchSite(fetchSite);
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
}
