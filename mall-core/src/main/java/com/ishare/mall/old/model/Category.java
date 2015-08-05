package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dongqi on 15/7/12.
 *
 * 类目
 */
@Entity
@Table(name = "es_category")
public class Category extends AbstractEntity {

    @Column(name = "category_name")
    private String name;

    @Column(name = "sort_index")
    private int index;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> children = Lists.newArrayList();

    @OneToMany
    @JoinTable(name = "es_products_categories", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products = Lists.newArrayList();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addChild(Category child) {
        child.setParent(this);
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", parent=" + parent +
                ", children=" + children.size() +
                ", products=" +
                '}';
    }
}
