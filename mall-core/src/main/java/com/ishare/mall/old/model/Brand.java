package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dongqi on 15/7/12.
 *
 * 品牌
 */
@Entity
@Table(name = "es_brand")
public class Brand extends AbstractEntity {

    @Column(name = "brand_name")
    private String name;
    private String logoUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = Lists.newArrayList();

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
