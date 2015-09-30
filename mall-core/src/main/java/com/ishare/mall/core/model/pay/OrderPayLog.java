package com.ishare.mall.core.model.pay;


import com.ishare.mall.common.base.enumeration.CostType;
import com.ishare.mall.common.base.enumeration.PayType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_PAY_LOG_NAME;

/**
 * Created by YinLin on 2015/8/24.
 * Description : 支付信息LOG 入库
 * Version 1.0
 */
@Entity
@Table(name = TABLE_ORDER_PAY_LOG_NAME)
public class OrderPayLog {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "pay_order_id",length = 30)
    private String orderId;
    @Column(name = "pay_channel_id",length = 15)
    private Integer channelId;
    @Enumerated(EnumType.STRING)
    @Column(length=7, nullable = false, name = "pay_cost_type")
    private CostType costType;
    @Column(name = "pay_member_account",length = 15)
    private String account;
    @Column(name = "pay_amount",length = 3)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(length=7, nullable = false, name = "pay_log_type")
    private PayType payType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_log_create_time",length = 20)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_log_update_time",length = 20)
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_log_finish_time",length = 20)
    private Date finishTime;
    @Column(name = "pay_log_tans_id",length = 15)
    private String tansId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTansId() {
        return tansId;
    }

    public void setTansId(String tansId) {
        this.tansId = tansId;
    }
}
