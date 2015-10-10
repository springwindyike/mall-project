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
public class ListPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(ListPageProcessor.class);

    private Site site = Site.me().setUserAgent(FetchConstant.USER_AGENT).setTimeOut(FetchConstant.TIMEOUT).setSleepTime(FetchConstant.SLEEP_TIME);

    @Override
    public void process(Page page) {
        String url = page.getUrl().get();
        String root = "div";
        FetchSite fetchSite = null;
        if (url.contains("jd.com")) {
            root = "li.gl-item > div > div.p-name > a";
            fetchSite = FetchSite.JD;
        } else if (url.contains("dangdang.com")) {
            root = "div.inner > p.name > a";
            fetchSite = FetchSite.DANG_DANG;
        } else if (url.contains("amazon.cn")) {
            root = "a.a-link-normal.s-access-detail-page.a-text-normal";
            fetchSite = FetchSite.AMAZON;
        }

        List<FetchUrl> fetchUrls = Lists.newArrayList();
        Document document = page.getHtml().getDocument();

        Elements links = document.select(root);
        for (Element _link : links) {
            String link = _link.attr("href");
            String name = _link.text();

            if (StringUtils.isBlank(link) || StringUtils.isBlank(name)) continue;
            log.debug("{}, {}", link, name);
            FetchUrl fetchUrl = new FetchUrl();
            fetchUrl.setType(FetchUrlType.PAGE);
            fetchUrl.setFetchSite(fetchSite);
            fetchUrl.setValid(Boolean.TRUE);
            fetchUrl.setLink(link);
            fetchUrl.setName(name);

            fetchUrls.add(fetchUrl);
        }

        //分页
        String nextDiv = "";
        if (url.contains("amazon.cn")) {
            nextDiv = "a#pagnNextLink";
        } else if (url.contains("jd.com")) {
            nextDiv = "a.pn-next";
        } else if (url.contains("dangdang.com")) {
            nextDiv = "li.next > a";
        }
        boolean notNext = document.select(nextDiv).isEmpty();
        if (!notNext) {
            String nextLink = document.select(nextDiv).attr("href");
            page.addTargetRequest(nextLink);
        }

        page.getResultItems().put(FetchConstant.KEY_CLASS, FetchUrl.class);
        page.getResultItems().put(FetchConstant.KEY_ITEM, fetchUrls);
        log.info("fetch {} data {}", fetchSite, fetchUrls.size());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
