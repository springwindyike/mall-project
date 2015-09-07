package com.ishare.mall.crawler.jd;

/**
 * Created by dongqi on 15/9/7.
 */
public class JDProduct {

    private String link;
    private String name;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JDProduct{" +
                "link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
