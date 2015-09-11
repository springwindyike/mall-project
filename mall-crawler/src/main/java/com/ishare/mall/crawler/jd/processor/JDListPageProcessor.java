package com.ishare.mall.crawler.jd.processor;

import com.google.common.collect.Sets;
import com.ishare.mall.crawler.jd.model.JDProduct;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Set;

@Component
public class JDListPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(JDListPageProcessor.class);

    private Site site = Site.me().setRetryTimes(30).setSleepTime(10000);

    @Override
    public void process(Page page) {
        Set<JDProduct> products = Sets.newHashSet();

        Document document = page.getHtml().getDocument();

        Elements elements = document.select("li.gl-item > div");
        for (Element element : elements) {
            JDProduct product = new JDProduct();

            Element pName = element.select("div.p-name > a").first();

            product.setLink(pName.attr("href"));
            if (pName.hasAttr("title") && !pName.attr("title").isEmpty()) {
                product.setName(pName.attr("title").trim());
            } else {
                product.setName(pName.text().trim());
            }

            String url = product.getLink();
            product.setCode(url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".html")));
            //列表页上的京东自营不准确，产品详情页的京东自营较为准确

            products.add(product);
        }

        boolean notNext = document.select("a.pn-next").isEmpty();
        if (!notNext) {
            page.addTargetRequest(document.select("a.pn-next").attr("href"));
        }

        page.putField("product", products);
        log.debug("抓取数据 {} 条", products.size());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
