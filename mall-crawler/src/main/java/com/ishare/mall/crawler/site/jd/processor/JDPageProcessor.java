package com.ishare.mall.crawler.site.jd.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ishare.mall.crawler.model.BasePageData;
import com.ishare.mall.crawler.site.FetchConstant;
import com.ishare.mall.crawler.site.jd.JDPageConfig;
import com.ishare.mall.crawler.site.jd.model.JDProduct;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JDPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(JDPageProcessor.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private Site site = Site.me().setUserAgent(USER_AGENT).setRetryTimes(30).setSleepTime(10000);

    /*
    public static void main(String[] args) {
        Spider.create(new JDPageProcessor())
                .addUrl("http://item.jd.com/1259131.html")
                .thread(1).run();
    }*/

    @Override
    public void process(Page page) {
        BasePageData basePageData = new BasePageData();

        JDProduct jdProduct = getProductInfo(page);
        basePageData.setName(jdProduct.getName());
        basePageData.setLink(jdProduct.getLink());
        basePageData.setCode(jdProduct.getCode());
        basePageData.setSelf(jdProduct.getSelf());
        basePageData.setStock(jdProduct.getStock());
        basePageData.setPriceText(String.valueOf(jdProduct.getPrice()));
        basePageData.setPriceOriginText(String.valueOf(jdProduct.getPriceOrigin()));
        basePageData.setAttributes(jdProduct.getAttributes());
        basePageData.setDatetimeText(new DateTime(jdProduct.getJdDatetime()).toString("yyyy-MM-dd"));
        basePageData.setIntroImages(Sets.newHashSet(jdProduct.getIntroImgs()));
        basePageData.setPhotos(Sets.newHashSet(jdProduct.getPhoto()));
        basePageData.setTag(jdProduct.getTag());
        basePageData.setUpdateTime(DateTime.now().toDate());
        basePageData.setThirdPartyShopName(jdProduct.getThirdPartyShopName());

        page.getResultItems().put(FetchConstant.KEY_CLASS, BasePageData.class);
        page.getResultItems().put(FetchConstant.KEY_ITEM, basePageData);
    }

    @Override
    public Site getSite() {
        return site;
    }

    JDProduct getProductInfo(Page page) {
        JDProduct product = new JDProduct();
        product.setLink(page.getUrl().get());
        product.setUpdateTime(new Date());

        Document document = page.getHtml().getDocument();
        JDPageConfig pageConfig = fetchPageConfig(document);

        String tag = document.select("div.breadcrumb").text().trim();
        tag = StringUtils.strip(tag);

        boolean isSelf = document.html().contains("京东自营");

        String name = document.select("div#name > h1").text();

        String jdDatetime = "";
        Map<String, String> attributes = Maps.newHashMap();
        Elements lis = document.select("ul#parameter2.p-parameter-list > li");
        for (Element li : lis) {
            String string = StringUtils.strip(li.text());
            String[] values = null;

            if (string.contains("：")) {
                values = string.split("：");
            } else if (string.contains(":")) {
                values = string.split(":");
            }
            if (values == null || values.length != 2) continue;
            String key = StringUtils.strip(values[0]);
            String val = StringUtils.strip(values[1]);

            attributes.put(key, val);
            //log.debug("商品介绍：{} = {}", key, val);
            if (key.contains("上架时间")) {
                jdDatetime = val;
            }
        }

        String[] prices = fetchPrice(pageConfig);
        String price = prices[0];
        String priceOrigin = prices[1];
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
        product.setPrice(Double.valueOf(price));
        product.setPriceOrigin(Double.valueOf(priceOrigin));
        product.setStock(stock);
        product.setTag(tag);
        product.setPhoto(photo);
        if (StringUtils.isNotBlank(jdDatetime)) {
            product.setJdDatetime(DateTime.parse(jdDatetime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate());
        }

        //page.putField("product.update", product);
        return product;
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
                if (!desc.contains("http:")) {
                    desc = "http:" + desc;
                }
                pageConfig.setDescUrl(desc);

                //log.debug("\nskuid-|{}|\nskuidkey-|{}|\ncat-|{}|\nbrand-|{}|\nvenderId-|{}|\nshopId-|{}|\ndesc-|{}|", skuid, skuidkey, cat, brand, venderId, shopId, desc);
            }
        }
        return pageConfig;
    }

    String[] fetchPrice(JDPageConfig pageConfig) {
        String price[] = new String[2];
        try {
            //log.debug("config.priceUrl {}", pageConfig.getPriceUrl());
            String body = Jsoup.connect(pageConfig.getPriceUrl()).userAgent(USER_AGENT).ignoreContentType(true).execute().body();
            log.debug("\n\t价格\n\t{}\n", body);
            JSONArray array = JSONObject.parseArray(body);
            Object object = array.get(0);
            JSONObject json = (JSONObject) object;
            price[0] = json.getString("p");
            price[1] = json.containsKey("m") ? json.getString("m") : "0";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return price;
    }

    String fetchStock(JDPageConfig pageConfig) {
        String stock = "";

        try {
            log.debug("pageConfig.getStockUrl {}", pageConfig.getStockUrl());
            String body = Jsoup.connect(pageConfig.getStockUrl()).userAgent(USER_AGENT).ignoreContentType(true).execute().body();

            JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
            JSONObject stockJson = (JSONObject) jsonObject.get("stock");

            int stockState = stockJson.getIntValue("StockState");
            log.debug("库存 \n|{}|, \n{}", stockState, body);

            if (stockJson.containsKey("D")) {
                JSONObject D = (JSONObject) stockJson.get("D");
                Object vender = D.get("vender");
                String gbk = new String(vender.toString().getBytes());
                log.debug("gbk {}", Arrays.toString(gbk.getBytes()));
                //TODO GBK 转 UTF8 没成功，先跳过
            }

            stock = stockState == 33 ? "有货" : "无货";
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return stock;
    }

    List<String> fetchIntroImgs(JDPageConfig pageConfig) {
        List<String> list = Lists.newArrayList();
        try {
            log.debug("pageConfig.getDescUrl() {}", pageConfig.getDescUrl());
            Element body = Jsoup.connect(pageConfig.getDescUrl()).userAgent(USER_AGENT).ignoreContentType(true).get().body();

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

            Element body = Jsoup.connect(pageConfig.getPhotoUrl()).userAgent(USER_AGENT).ignoreContentType(true).get().body();
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
                    img = img.replace("/n5/", "/n0/");
                    photo.add(img);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("pageConfig.getPhotoUrl()={}", pageConfig.getPhotoUrl());
        }

        log.debug("{}", photo);
        return photo;
    }


}
