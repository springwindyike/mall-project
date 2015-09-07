package com.ishare.mall.crawler.jd;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class JDCategoryPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(JDCategoryPageProcessor.class);

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public static void main(String[] args) {

        Spider.create(new JDCategoryPageProcessor())
                .addUrl("http://www.jd.com/allSort.aspx")
                        //.addPipeline(new JsonFilePipeline("target"))
                .thread(5)
                .run();
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        if (page.getUrl().get().contains("list.jd.com")) {
            //log.debug("\n\n{}", page.getHtml().toString());

            Elements elements = page.getHtml().getDocument().select("div.p-name > a");
            for (Element element : elements) {
                JDProduct product = new JDProduct();
                product.setLink(element.attr("href"));
                product.setName(element.attr("title"));

                log.debug("{}", product.toString());
            }

            boolean notNext = page.getHtml().getDocument().select("a.pn-next").isEmpty();
            if (!notNext) {
                page.addTargetRequest(page.getHtml().getDocument().select("a.pn-next").attr("href"));
            }
        } else {
            List<String> requests = Lists.newArrayList();

            JDCategory root = new JDCategory();
            root.setName("京东商城");
            root.setLink(page.getUrl().get());

            log.debug("部分二：定义如何抽取页面信息，并保存下来");
            Html html = page.getHtml();
            Elements elements = html.getDocument().select("div#allsort.w > div > div.m");
            for (Element e1 : elements) {

                String name = e1.select("div.mt > h2 > a").text();
                JDCategory level1 = new JDCategory();
                level1.setName(name);
                root.getChildren().add(level1);
                //log.debug("1 {}", level1.toString());

                for (Element e2 : e1.select("div.mc")) {
                    JDCategory level2 = new JDCategory();
                    level2.setName(e2.select("dl > dt").text());
                    level1.getChildren().add(level2);
                    //log.debug("2 {}", level2.toString());
                    for (Element e3 : e2.select("dl > dd > em > a")) {
                        JDCategory level3 = new JDCategory();
                        level3.setName(e3.text());
                        level3.setLink(e3.attr("href"));

                        level2.getChildren().add(level3);

                        if (level3.getLink().equals("http://list.jd.com/9987-653-655.html"))
                            requests.add(level3.getLink());
                        //log.debug("3 {}", level3.toString());
                    }
                }
            }

            //page.getResultItems().put("category", requests);

            log.debug("部分三：从页面发现后续的url地址来抓取");
            page.addTargetRequests(requests);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }
}
