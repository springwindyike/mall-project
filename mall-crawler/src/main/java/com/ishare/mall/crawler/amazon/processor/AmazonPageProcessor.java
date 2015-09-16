package com.ishare.mall.crawler.amazon.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class AmazonPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(AmazonPageProcessor.class);

    /*
    public static void main(String[] args) {
        String u1 = "http://www.amazon.cn/ochirly-%E6%AC%A7%E6%97%B6%E5%8A%9B-%E5%A5%B3%E5%BC%8F-%E6%8B%BC%E6%8E%A5%E7%A2%8E%E8%8A%B1%E8%8D%B7%E5%8F%B6%E8%BE%B9%E9%95%BF%E8%A2%96%E6%89%93%E5%BA%95%E8%BF%9E%E8%A1%A3%E8%A3%99-1115082200530/dp/B00L25CC76/ref=pd_sim_193_1?ie=UTF8&refRID=1S533WTAFN0SH9P70MBK";
        String u2 = "http://www.amazon.cn/TOSHIBA-%E4%B8%9C%E8%8A%9D-65L5450C-65%E8%8B%B1%E5%AF%B8-65L5450C%E5%AE%89%E5%8D%93%E6%99%BA%E8%83%BD%E7%94%B5%E8%A7%86-%E5%85%A8%E9%AB%98%E6%B8%85LED%E6%B6%B2%E6%99%B6%E7%94%B5%E8%A7%86/dp/B00XC4D5WQ/ref=sr_1_3?s=audio-video&ie=UTF8&qid=1442381299&sr=1-3";
        Spider.create(new AmazonPageProcessor()).addUrl(u1, u2).run();
    }
    */

    @Override
    public void process(Page page) {
        Document doc = page.getHtml().getDocument();
        //TODO 亚马逊商品页面抓取未完成
        String tag = doc.select("div#wayfinding-breadcrumbs_feature_div").text();
        log.debug("商品tag {}", tag);

        String name = doc.select("span#productTitle").text();
        log.debug("商品名称: {}", name);

        String price = doc.select("span#priceblock_ourprice").text();
        log.debug("商品价格: {}", price);

        boolean thirdParty = doc.select("div#soldByThirdParty").isEmpty();
        log.debug("第三方: {}", !thirdParty);
        String stock = doc.select("span#ddmAvailabilityMessage").text();
        log.debug("商品库存: {}", stock);

        Elements elements = doc.select("div#detail_bullets_id > table > tbody > tr > td > div.content > ul > li");
        for (Element element : elements) {
            String key = element.select("b").text();
            String value = element.ownText();
            if (key.contains("用户评分") || key.contains("排名")) continue;
            log.debug("{} = {}", key, value);
        }
    }

    @Override
    public Site getSite() {
        return Site.me();
    }
}
