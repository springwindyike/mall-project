package com.ishare.mall.core.model.product;

import com.google.common.collect.Sets;
import com.ishare.mall.common.base.dto.generic.GenericDTO;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by YinLin on 2015/10/10.
 * Description :
 * Version 1.0
 */
@Entity
@Table(name = "t_fetch")
public class Fetch extends GenericDTO {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tag")
    private String tag;

    @OneToMany(mappedBy="fetch", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Product> products = Sets.newConcurrentHashSet();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
