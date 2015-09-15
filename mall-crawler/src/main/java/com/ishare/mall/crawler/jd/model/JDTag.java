package com.ishare.mall.crawler.jd.model;

import javax.persistence.*;

@Entity
@Table(name = "spider_jd_tag")
public class JDTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int index;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private JDTag parent;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public JDTag getParent() {
        return parent;
    }

    public void setParent(JDTag parent) {
        this.parent = parent;
    }
}
