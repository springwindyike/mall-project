package com.ishare.mall.crawler.jd;

import javax.persistence.*;

/**
 * Created by dongqi on 15/9/7.
 */
@Entity
@Table(name = "spider_jd_product")
public class JDProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String link;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JDProduct product = (JDProduct) o;

        return link.equals(product.link);

    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }
}
