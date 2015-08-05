package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dongqi on 15/7/12.
 *
 * 生产（制造/供应）厂商
 */
@Entity
@Table(name = "es_manufacturer")
public class Manufacturer extends AbstractEntity {

    @Column(name = "manu_name")
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Brand> brands = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
