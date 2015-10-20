package com.ishare.mall.crawler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spider_fetch_url")
public class FetchUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 512)
    private String name;
    @Column(length = 1024)
    private String link;

    @Enumerated(EnumType.STRING)
    private FetchSite fetchSite;
    @Enumerated(EnumType.STRING)
    private FetchUrlType type;

    @JsonFormat(timezone = "Asia/Shanghai", pattern = "yyyy/MM/dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Type(type = "yes_no")
    @Column(name = "is_valid")
    private boolean valid;

    @OneToOne(cascade = CascadeType.ALL)
    private BasePageData pageData;

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

    public FetchSite getFetchSite() {
        return fetchSite;
    }

    public void setFetchSite(FetchSite fetchSite) {
        this.fetchSite = fetchSite;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public FetchUrlType getType() {
        return type;
    }

    public void setType(FetchUrlType type) {
        this.type = type;
    }

    public BasePageData getPageData() {
        return pageData;
    }

    public void setPageData(BasePageData pageData) {
        this.pageData = pageData;
    }
}
