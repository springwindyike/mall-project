package com.ishare.mall.core.model.information;

import com.ishare.mall.core.model.base.BaseEntity;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_LOGISTICS_NAME;
import javax.persistence.*;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 物流公司信息 对接物流公司信息，主要由平台管理对接
 * Version 1.0
 */
@Entity(name = TABLE_LOGISTICS_NAME)
public class Logistics extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "logistics_name",length = 27)
    private String name;//物流公司名称
    @Column(name = "logistics_contact_name",length = 15)
    private String contact;//联系人姓名
    @Column(name = "logistics_contact_tel",length = 11)
    private String tel;//联系电话
    @Column(name = "logistics_type",length = 9)
    private String type;//物流类型：快递、物流、
    @Column(name = "logistics_query_url",length = 200)
    private String query;//查询地址
    @Column(name = "logistics_official_url",length = 50)
    private String url;//官网地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
