package com.ishare.mall.crawler.site.dangdang.processor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.crawler.model.BasePageData;
import com.ishare.mall.crawler.site.FetchConstant;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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

/**
 * 当当的页面居然结构都不统一，醉了。
 * 自营商品页面和第三方商品页面结构不一致，导致下面的代码比较乱，后面来优化。
 */
@Component
public class DangDangPageProcessor implements PageProcessor {

    private static final Logger log = LoggerFactory.getLogger(DangDangPageProcessor.class);

    private Site site = Site.me().setSleepTime(FetchConstant.SLEEP_TIME).setUserAgent(FetchConstant.USER_AGENT).setTimeOut(FetchConstant.TIMEOUT);

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        String url = page.getUrl().get();
        String html = "";
        try {
            html = Jsoup.connect(url).timeout(FetchConstant.TIMEOUT).userAgent(FetchConstant.USER_AGENT).ignoreContentType(true).execute().body();
            document = Jsoup.parse(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //log.debug("\n\n\t{}\n\n", document.html());
        Map<String, Object> data = fetchMainProduct(document);
        String priceText = "", priceOriginText = "", stock = "", code = "";
        Map<String, String> attributes = Maps.newHashMap();
        List<String> introImages = Lists.newArrayList();
        if (!data.isEmpty()) {
            //笔记本、服装页面可以用data来赋值
            priceText = data.get("salePrice").toString();
            priceOriginText = data.get("originalPrice").toString();
            stock = data.get("stock").toString();
            code = data.get("productID").toString();

            Map<String, Object> result = fetchAttributesAndIntroImages(document, code);
            attributes = (Map<String, String>) result.get("attributes");
            introImages = (List<String>) result.get("introImages");
        } else {
            //图书页面
            Map<String, String> stockAndCodeMap = fetchStock(page);
            stock = stockAndCodeMap.get("stock");
            code = stockAndCodeMap.get("code");

            Map<String, Object> result = fetchAttributesAndIntroImages(document, code);
            attributes = (Map<String, String>) result.get("attributes");

            log.debug("price_info={}, sale={}", document.select("div.price_info").isEmpty(), document.select("div.sale").isEmpty());
            if (!document.select("div.price_info").isEmpty()) {
                priceText = document.select("div.right > span.price_d").text();
                priceOriginText = document.select("div.right > span.price_m").text();
            } else if (!document.select("div.sale").isEmpty()) {
                priceText = document.select("span#salePriceTag").text();
                priceOriginText = document.select("span#originalPriceTag").text();
            }
        }

        String tag = document.select("div#main_bd > div.breadcrumb").text();
        String name = document.select("div.head").text();
        if (StringUtils.isBlank(name)) {
            name = document.select("div.name_info > h1").text();
        }

        String thirdPartyShopName = document.select("div#shop_outer_div").select("a.name").text();
        boolean self = thirdPartyShopName.contains("当当");
        log.debug("div.service_more={}", document.select("div.service_more").text());
        if (!document.select("div.service_more").isEmpty()) {
            self = document.select("div.service_more").text().contains("当当");
        }

        List<String> photos = Lists.newArrayList();
        Elements photoElements = document.select("div#mainimg_pic").select("li > a");
        for (Element a : photoElements) {
            String photo = a.attr("id");
            photos.add(photo);
        }

        log.debug("\n\turl {}\n\ttag {}\n\tname {}\n\tprice {}\n\toriginalPrice {}\n\tstock {}\n\tshop {}\n\tself {}\n\tattr {}\n\tphoto {}\n\tintro {}", url, tag, name, priceText, priceOriginText, stock, thirdPartyShopName, self, attributes, photos, introImages);

        BasePageData basePageData = new BasePageData();
        basePageData.setName(name);
        basePageData.setTag(tag);
        basePageData.setPhotos(photos);
        basePageData.setAttributes(attributes);
        basePageData.setCode(code);
        basePageData.setIntroImages(introImages);
        basePageData.setLink(url);
        basePageData.setPriceOriginText(StringUtils.substring(priceOriginText, 0, priceOriginText.length() - 2));
        basePageData.setPriceText(StringUtils.substring(priceText, 0, priceText.length() - 2));
        basePageData.setSelf(self);
        basePageData.setStock(stock);
        basePageData.setThirdPartyShopName(thirdPartyShopName);
        basePageData.setUpdateTime(DateTime.now().toDate());
        log.debug("{}", basePageData);
        page.getResultItems().put(FetchConstant.KEY_CLASS, BasePageData.class);
        page.getResultItems().put(FetchConstant.KEY_ITEM, basePageData);
    }

    @Override
    public Site getSite() {
        return site;
    }

    Map<String, Object> fetchAttributesAndIntroImages(Document document, String productId) {
        Map<String, Object> result = Maps.newHashMap();
        Map<String, String> attributes = Maps.newHashMap();
        List<String> introImages = Lists.newArrayList();

        Elements scripts = document.getElementsByTag("script");
        String src = null;
        for (Element script : scripts) {
            String value = script.attr("src");
            if (value.contains("mallclothv2main")) {
                src = value;
                break;
            } else if (value.contains("mainv")) {
                src = value;
                break;
            }
        }

        if (StringUtils.isNotBlank(src)) {
            //商品详情是放到src这个js里面的，很屌哦
        }
        try {
            String url = "http://product.dangdang.com/detail/main.php?product_id=" + productId + "&_=" + System.currentTimeMillis();
            String html = Jsoup.connect(url).ignoreContentType(true).userAgent(FetchConstant.USER_AGENT).execute().body();
            //log.debug("\n===== start =====\nresult:{}\n{}\n===== end =====\n", url, html);
            Document body = Jsoup.parse(html);

            boolean bln = body.select("div#detail_all > textarea").isEmpty();
            if (!bln) {
                String textarea = body.select("div#detail_all > textarea").first().text();
                Document detailAll = Jsoup.parse(textarea);
                //log.debug("{}", detailAll.select("div.mall_goods_foursort_style > div.mall_goods_foursort_style_frame"));

                for (Element element : detailAll.select("div.mall_goods_foursort_style > div.mall_goods_foursort_style_frame")) {
                    String[] text = StringUtils.strip(element.text()).split("：");
                    if (text.length == 2) {
                        String key = StringUtils.strip(text[0]);
                        String value = StringUtils.strip(text[1]);
                        attributes.put(key, value);
                    }
                    //log.debug("商品详情 = {}", element.text());
                }

                for (Element img : detailAll.select("div.right_content").select("img")) {
                    String imgSrc = StringUtils.strip(img.attr("src"));
                    log.debug("图文 = {}", imgSrc);
                    if (!imgSrc.startsWith("http://")) continue;
                    introImages.add(imgSrc);
                }
            }
            bln = body.select("div#detail_all > div#detail_describe").isEmpty();
            if (!bln) {
                for (Element element : body.select("div#detail_all > div#detail_describe").select("li")) {
                    log.debug("商品详情 = {}", element.text());
                    String[] text = StringUtils.strip(element.text()).split("：");
                    if (text.length == 2) {
                        String key = StringUtils.strip(text[0]);
                        String value = StringUtils.strip(text[1]);
                        attributes.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("attributes", attributes);
        result.put("introImages", introImages);
        return result;
    }

    Map<String, Object> fetchMainProduct(Document document) {
        Map<String, Object> data = Maps.newHashMap();

        Elements scripts = document.getElementsByTag("script");
        String mainProduct = null;
        for (Element script : scripts) {
            if (script.html().contains("main_product")) {
                mainProduct = script.html();
                mainProduct = search("main_product.+};", mainProduct);
                mainProduct = mainProduct.replace("main_product", StringUtils.EMPTY);
                mainProduct = mainProduct.replace("=", StringUtils.EMPTY);
                mainProduct = mainProduct.replace(";", StringUtils.EMPTY);
                mainProduct = mainProduct.replace("\"version\":isw,", StringUtils.EMPTY);
                mainProduct = StringUtils.strip(mainProduct);
                break;
            }
        }

        if (StringUtils.isBlank(mainProduct)) return data;

        try {
            JSONObject productJson = (JSONObject) JSONObject.parse(mainProduct);
            log.debug("mainProduct={}", mainProduct);
            data.put("productID", productJson.getString("product_id"));
            data.put("productName", productJson.getString("product_name"));
            data.put("salePrice", productJson.getString("sale_price"));
            data.put("originalPrice", productJson.getString("original_price"));
            data.put("stock", productJson.getBoolean("isnostock") ? "无货" : "有货");

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.debug("main product data: {}", data);
        return data;
    }

    String search(final String regex, final String text) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        String result = "";
        if (m.find()) result = m.group();
        return result;
    }

    Map<String, String> fetchStock(Page page) {
        Map<String, String> data = Maps.newHashMap();
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
        String url = "http://product.dangdang.com/pricestock/callback.php?type=stockv3&product_id=" + pid + "&shop_id=" + shopId + "&page_type=" + pageType + "&t=" + t + "&template_id=" + tid + iscatalog;
        url += "&fourarea=true&province_id=151&city_id=3001&area_id=1510120";
        //log.debug("pid={}, sid={}, pageType={}, tid={}, isc={}", pid, shopId, pageType, tid, iscatalog);
        //log.debug("{}", url);

        try {
            String html = Jsoup.connect(url).userAgent(FetchConstant.USER_AGENT).timeout(FetchConstant.TIMEOUT).ignoreContentType(true).execute().body();

            JSONObject jsonObject = JSONObject.parseObject(html);

            Object havestock = jsonObject.get("havestock");
            if (havestock.equals("realstock")) {
                stock = "有货";
            } else {
                stock = "无货";
            }
            //log.debug("stock {}", stock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.put("stock", stock);
        data.put("code", pid);
        return data;
    }
}
