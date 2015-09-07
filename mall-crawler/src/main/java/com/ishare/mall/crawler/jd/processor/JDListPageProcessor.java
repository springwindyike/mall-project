package com.ishare.mall.crawler.jd.processor;

import com.google.common.collect.Sets;
import com.ishare.mall.crawler.jd.JDProduct;
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

        Elements elements = page.getHtml().getDocument().select("div.p-name > a");
        for (Element element : elements) {
            JDProduct product = new JDProduct();
            product.setLink(element.attr("href"));
            if (element.hasAttr("title") && !element.attr("title").isEmpty()) {
                product.setName(element.attr("title").trim());
            } else {
                product.setName(element.text().trim());
            }

            //log.debug("{}", product.toString());
            products.add(product);
        }

        boolean notNext = page.getHtml().getDocument().select("a.pn-next").isEmpty();
        if (!notNext) {
            page.addTargetRequest(page.getHtml().getDocument().select("a.pn-next").attr("href"));
        }

        page.putField("product", products);
        log.debug("抓取数据 {} 条", products.size());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
