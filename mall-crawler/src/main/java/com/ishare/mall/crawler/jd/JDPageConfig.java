package com.ishare.mall.crawler.jd;

import org.apache.commons.lang3.StringUtils;

public class JDPageConfig {

    private final String PRICE_URL = "http://p.3.cn/prices/get?type=1&skuid=J_";
    private final String STOCK_URL = "http://c0.3.cn/stock?";
    private final String DESC_URL = "";
    private final String PHOTO_URL = "http://www.jd.com/bigimage.aspx?id=";
    private final String area = "22_1930_50949_0";
    private final String extraParam = "{\"originid\":\"1\"}";
    private String skuid;
    private String skuidkey;
    private String cat;
    private String brand;
    private String venderId = "0";
    private String shopId;
    private int stockState;
    private String priceUrl;
    private String stockUrl;
    private String descUrl;
    private String photoUrl;

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getSkuidkey() {
        return skuidkey;
    }

    public void setSkuidkey(String skuidkey) {
        this.skuidkey = skuidkey;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getStockState() {
        return stockState;
    }

    public void setStockState(int stockState) {
        this.stockState = stockState;
    }

    public String getArea() {
        return area;
    }

    public String getExtraParam() {
        return extraParam;
    }

    public String getPriceUrl() {
        return PRICE_URL + skuid;
    }

    public void setPriceUrl(String priceUrl) {
        this.priceUrl = priceUrl;
    }

    public String getStockUrl() {
        return STOCK_URL + "skuId=" + skuid + "&venderId=" + (StringUtils.isNotBlank(venderId) ? venderId : "0") + "&cat=" + cat + "&area=" + area + "&buyNum=1&extraParam=" + extraParam + "&ch=1";
    }

    public void setStockUrl(String stockUrl) {
        this.stockUrl = stockUrl;
    }

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }

    public String getPhotoUrl() {
        return PHOTO_URL + skuid;
    }
}
