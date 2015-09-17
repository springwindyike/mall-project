package com.ishare.mall.crawler.site.jd.processor;

import com.ishare.mall.crawler.site.jd.model.JDCategory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class JDCategoryPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(JDCategoryPageProcessor.class);

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(30).setSleepTime(10000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        JDCategory root = new JDCategory();
        root.setName("JDRoot");
        root.setLink(page.getUrl().get());

        Html html = page.getHtml();
        Elements elements = html.getDocument().select("div#allsort.w > div > div.m");
        for (Element e1 : elements) {

            String name = e1.select("div.mt > h2 > a").text();
            JDCategory level1 = new JDCategory();
            level1.setName(name);
            level1.setParent(root);
            root.getChildren().add(level1);
            //log.debug("1 {}", level1.toString());

            for (Element e2 : e1.select("div.mc")) {
                JDCategory level2 = new JDCategory();
                level2.setName(e2.select("dl > dt").text());
                level2.setParent(level1);
                level1.getChildren().add(level2);
                //log.debug("2 {}", level2.toString());
                for (Element e3 : e2.select("dl > dd > em > a")) {
                    JDCategory level3 = new JDCategory();
                    level3.setName(e3.text());
                    level3.setLink(e3.attr("href"));

                    level3.setParent(level2);
                    level2.getChildren().add(level3);

                    //log.debug("3 {}", level3.toString());
                }
            }
        }

        page.putField("category", root);

    }

    @Override
    public Site getSite() {
        return site;
    }
}
