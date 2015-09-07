package com.ishare.mall.crawler.jd;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by dongqi on 15/9/7.
 */
public class JDCategory {

    private String name;
    private String link;

    private Set<JDCategory> children = Sets.newHashSet();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<JDCategory> getChildren() {
        return children;
    }

    public void setChildren(Set<JDCategory> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JDCategory that = (JDCategory) o;

        if (!name.equals(that.name)) return false;
        return !(link != null ? !link.equals(that.link) : that.link != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JDCategory{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", children=" + children +
                '}';
    }
}
