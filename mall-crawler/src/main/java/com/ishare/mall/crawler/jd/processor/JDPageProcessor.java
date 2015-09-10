package com.ishare.mall.crawler.jd.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.crawler.jd.JDPageConfig;
import com.ishare.mall.crawler.jd.model.JDProduct;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JDPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(JDPageProcessor.class);

    private Site site = Site.me().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36").setRetryTimes(30).setSleepTime(10000);

    /*
    public static void main(String[] args) {
        Spider.create(new JDPageProcessor())
                .addUrl("http://item.jd.com/544027.html")
                .addUrl("http://item.jd.com/1041474.html")
                .addUrl("http://item.jd.com/675971.html")
                .addUrl("http://item.jd.com/1507907129.html")
                .thread(1).run();
    }*/

    @Override
    public void process(Page page) {
        JDProduct product = new JDProduct();
        product.setLink(page.getUrl().get());
        product.setUpdateTime(new Date());

        Document document = page.getHtml().getDocument();
        JDPageConfig pageConfig = fetchPageConfig(document);

        String tag = document.select("div.breadcrumb").text().trim();
        boolean isSelf = document.html().contains("京东自营");

        String name = document.select("div#name > h1").text();

        Map<String, String> attributes = Maps.newHashMap();
        Elements lis = document.select("ul#parameter2.p-parameter-list > li");
        for (Element li : lis) {
            String val = li.attr("title").trim();
            String key = li.text().trim();
            key = key.replaceAll(val, StringUtils.EMPTY).trim();

            attributes.put(key, val);
            //log.debug("商品介绍：{} = {}", key, val);
        }

        String price = fetchPrice(pageConfig);
        String stock = fetchStock(pageConfig);
        List<String> photo = fetchPhoto(pageConfig);
        List<String> introImgs = fetchIntroImgs(pageConfig);

        /*
        log.debug("标签:{}", tag);
        log.debug("名称:{}, 价格:{}, 库存:{}, 自营:{}", name, price, stock, isSelf);
        log.debug("相册:{}", photo);
        log.debug("图文:{}", introImgs);
        */
        product.setName(name);
        product.setCode(pageConfig.getSkuid());
        product.setSelf(isSelf);
        product.setAttributes(attributes);
        product.setIntroImgs(introImgs);
        product.setPrice(price);
        product.setStock(stock);
        product.setTag(tag);
        product.setPhoto(photo);

        page.putField("product.update", product);
    }

    @Override
    public Site getSite() {
        return site;
    }

    String search(final String regex, final String text) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        String result = "";
        if (m.find()) result = m.group();
        return result;
    }

    JDPageConfig fetchPageConfig(Document document) {
        JDPageConfig pageConfig = new JDPageConfig();
        for (Element e : document.select("script")) {
            String html = e.html();
            boolean bln = html.contains("skuid") && html.contains("skuidkey");
            if (bln) {

                String skuid = search("skuid:.+\\d+", html).replace("skuid:", StringUtils.EMPTY);
                skuid = StringUtils.strip(skuid);
                pageConfig.setSkuid(skuid);

                String skuidkey = search("[A-Z0-9]{32}", html);
                pageConfig.setSkuidkey(skuidkey);

                String cat = search("cat:.+\\]", html).replace("cat:", StringUtils.EMPTY).replaceAll("\\[|\\]", StringUtils.EMPTY);
                cat = StringUtils.strip(cat);
                pageConfig.setCat(cat);

                String brand = search("brand:.+\\d+", html).replace("brand:", StringUtils.EMPTY);
                brand = StringUtils.strip(brand);
                pageConfig.setBrand(brand);

                String venderId = search("venderId:.+\\d+", html).replace("venderId:", StringUtils.EMPTY);
                venderId = StringUtils.strip(venderId);
                pageConfig.setVenderId(venderId);

                String shopId = search("shopId:.+\\d+", html).replace("shopId:", StringUtils.EMPTY).replace("'", StringUtils.EMPTY);
                shopId = StringUtils.strip(shopId);
                pageConfig.setShopId(shopId);

                String desc = search("desc:.+\\d+',", html).replace("desc:", StringUtils.EMPTY).replaceAll("'|,", StringUtils.EMPTY);
                desc = StringUtils.strip(desc);
                pageConfig.setDescUrl(desc);

                //log.debug("\nskuid-|{}|\nskuidkey-|{}|\ncat-|{}|\nbrand-|{}|\nvenderId-|{}|\nshopId-|{}|\ndesc-|{}|", skuid, skuidkey, cat, brand, venderId, shopId, desc);
            }
        }
        return pageConfig;
    }

    String fetchPrice(JDPageConfig pageConfig) {
        String price = "";
        try {
            //log.debug("config.priceUrl {}", pageConfig.getPriceUrl());
            String body = Jsoup.connect(pageConfig.getPriceUrl()).ignoreContentType(true).execute().body();
            JSONArray array = JSONObject.parseArray(body);
            Object object = array.get(0);
            JSONObject json = (JSONObject) object;
            price = json.getString("p");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return price;
    }

    String fetchStock(JDPageConfig pageConfig) {
        String stock = "";

        try {
            String body = Jsoup.connect(pageConfig.getStockUrl()).ignoreContentType(true).execute().body();

            JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
            JSONObject stockJson = (JSONObject) jsonObject.get("stock");

            int stockState = stockJson.getIntValue("StockState");
            //log.debug("库存 \n|{}|, \n{}", stockState, body);

            stock = stockState == 33 ? "有货" : "无货";
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return stock;
    }

    List<String> fetchIntroImgs(JDPageConfig pageConfig) {
        List<String> list = Lists.newArrayList();
        try {
            Element body = Jsoup.connect(pageConfig.getDescUrl()).ignoreContentType(true).get().body();

            for (Element img : body.select("img")) {
                String src = img.attr("data-lazyload").replaceAll("\\\\\"", StringUtils.EMPTY);
                //log.debug("图文介绍 {}", src);
                list.add(src);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

    List<String> fetchPhoto(JDPageConfig pageConfig) {
        List<String> photo = Lists.newArrayList();

        try {

            Element body = Jsoup.connect(pageConfig.getPhotoUrl()).ignoreContentType(true).get().body();
            for (Element script : body.select("script")) {
                String html = script.html();
                if (!html.contains("newOutputAllImages")) continue;

                String json = search("\\[.+\\]", html);
                //log.debug("\nphoto\n{}\n{}", html, json);

                JSONArray array = (JSONArray) JSONArray.parse(json);
                int size = array.size();
                for (int index = 0; index < size; index++) {
                    JSONObject image = (JSONObject) array.get(index);
                    String img = image.getString("img");
                    photo.add(img);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return photo;
    }


}
