package com.ishare.mall.crawler.site.dangdang.processor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.crawler.site.FetchConstant;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DangDangPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(DangDangPageProcessor.class);

    private Site site = Site.me().setSleepTime(FetchConstant.SLEEP_TIME).setUserAgent(FetchConstant.USER_AGENT).setTimeOut(FetchConstant.TIMEOUT);

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        //log.debug("\n\n\t{}\n\n", document.html());
        String url = page.getUrl().get();

        String tag = document.select("div#main_bd > div.breadcrumb").text();
        String name = document.select("div.head").text();
        String priceText = document.select("span#salePriceTag").text();
        String stock = fetchStock(page);//document.select("a#part_buy_button.btn.btn_buy.disabled").text();
        String thirdPartyShopName = document.select("div#shop_outer_div").select("a.name").text();
        boolean self = thirdPartyShopName.contains("当当网");

        Map<String, String> attributes = Maps.newHashMap();
        Elements elements = document.select("div#detail_all > div.mall_goods_foursort_style > div.mall_goods_foursort_style_frame");
        for (Element element : elements) {
            String[] text = element.text().split("：");
            attributes.put(text[0], text[1]);
        }

        List<String> photos = Lists.newArrayList();
        Elements photoElements = document.select("div#mainimg_pic").select("li > a");
        for (Element a : photoElements) {
            String photo = a.attr("id");
            photos.add(photo);
        }

        List<String> introImages = Lists.newArrayList();
        Elements introImagesElements = document.select("div#detail_all > div.right_content").select("p > img");
        for (Element img : introImagesElements) {
            String src = img.attr("src");
            introImages.add(src);
        }

        log.debug("\n\turl {}\n\ttag {}\n\tname {}\n\tprice {}\n\tstock {}\n\tshop {}\n\tself {}\n\tattr {}\n\tphoto {}\n\tintro {}", url, tag, name, priceText, stock, thirdPartyShopName, self, attributes, photos, introImages);
    }

    @Override
    public Site getSite() {
        return site;
    }

    String fetchStock(Page page) {
        String stock = "";
        Elements span = page.getHtml().getDocument().select("span#stock_span");
        String pid = span.attr("prd_id");
        String shopId = span.attr("shop_id");
        String pageType = span.attr("page_type");
        String tid = span.attr("template_id");
        String iscatalog = span.attr("iscatalog");
        if (StringUtils.isNotBlank(iscatalog)) {
            iscatalog = "&iscatalogflag=true";
        }
        String t = String.valueOf(Math.random());
        String url = "http://product.dangdang.com/pricestock/callback.php?type=stockv2&product_id=" + pid + "&shop_id=" + shopId + "&page_type=" + pageType + "&t=" + t + "&template_id=" + tid + iscatalog;

        //log.debug("{}, {}, {}, {}, {}", pid, shopId, pageType, tid, iscatalog);

        try {
            String html = Jsoup.connect(url).userAgent(FetchConstant.USER_AGENT).timeout(10000).ignoreContentType(true).execute().body();

            JSONObject jsonObject = JSONObject.parseObject(html);
            html = jsonObject.getString("html");
            //log.debug("{}", html);
            String stockStatus = Jsoup.parse(html).select("input#stock_status").attr("true_value");

            switch (stockStatus) {
                case "0":
                    stock = "有货";
                    break;
                case "2":
                    stock = "无货";
                    break;
            }
            //log.debug("stock {}", stock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stock;
    }

    String search(final String regex, final String text) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        String result = "";
        if (m.find()) result = m.group();
        return result;
    }
}
