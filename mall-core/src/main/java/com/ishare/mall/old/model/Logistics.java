package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dongqi on 15/7/20.
 * 物流公司
 */
@Entity
@Table(name = "es_logistics")
public class Logistics extends AbstractEntity {

    @Column(name = "logistics_name")
    private String name;//物流公司名称
    private String contact;//联系人姓名
    private String tel;//联系电话
    @Column(name = "logistics_type")
    private String type;//物流类型：快递、物流、
    @Column(name = "query_url")
    private String query;//查询地址
    @Column(name = "official_url")
    private String url;//官网地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
