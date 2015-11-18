package com.ishare.mall.core.model.supplier;

import com.ishare.mall.core.model.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_SUPPLIER_LOG_NAME;

/**
 * Created by YinLin on 2015/11/18.
 * Description : 供应商日志表 主要用户供应商操作日志
 * Version 1.0
 */
@Entity
@Table(name = TABLE_SUPPLIER_LOG_NAME)
public class SupplierLog extends BaseEntity {
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//自增ID
    @Column(name = "suppler_log_content", length = 256, nullable = false)
    private String content;//日志内容
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "supplier_log_time", length = 20, nullable = false)
    private Date logTime;//记录日志时间
    @Column(name = "supplier_member_id", length = 20, nullable = false)
    private Integer memberId;//操作者ID
    @Column(name = "supplier_member_name", length = 32, nullable = false)
    private String memberName;//操作者名字
    @Column(name = "supplier_id", length = 20, nullable = false)
    private Integer supplierId;//供应商ID
    @Column(name = "supplier_log_ip", length = 20, nullable = false)
    private String ip;//操作IP地址
    @Column(name = "supplier_log_url", length = 256, nullable = false)
    private String url;//操作URL
    @Column(name = "supplier_log_status", length = 1, nullable = false)
    private Integer status;//操作状态 0 表示失败 1 表示成功

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
