package com.ishare.mall.crawler.jd;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dongqi on 15/9/7.
 */
@Entity
@Table(name = "spider_jd_category")
public class JDCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String link;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private JDCategory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JDCategory> children = Sets.newHashSet();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public JDCategory getParent() {
        return parent;
    }

    public void setParent(JDCategory parent) {
        this.parent = parent;
    }

    public Set<JDCategory> getChildren() {
        return children;
    }

    public void setChildren(Set<JDCategory> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "JDCategory{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
