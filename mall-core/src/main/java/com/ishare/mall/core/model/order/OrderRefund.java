package com.ishare.mall.core.model.order;

import com.ishare.mall.common.base.constant.DataBaseConstant;
import com.ishare.mall.common.base.object.BaseObject;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_REFUND;

/**
 * Created by wanghao on 2015/11/10.
 * description 退货退款
 */
@Entity
@Table(name = TABLE_ORDER_REFUND)
public class OrderRefund implements BaseObject {
    /**退货、退款id**/
    @Id
    @Column(length = 25, name = "refund_id")
    private String refundId;
    /**订单id**/
    @Column(length = 17, name = "order_id")
    private String orderId;
    /**渠道id**/
    @Column(length = 11, name = "channel_id")
    private Integer channelId;
    /**渠道名字**/
    @Column(length = 30, name = "channel_name")
    private String channelName;
    /**买家id**/
    @Column(length = 11, name = "buyer_id")
    private String buyerId;
    /**买家名字**/
    @Column(length = 50, name = "buyer_name")
    private String buyerName;
    /**产品id**/
    @Column(length = 25, name = "product_id")
    private Integer productId;
    /**产品名字**/
    @Column(length = 50, name = "product_name")
    private String productName;
    /**退换商品数量**/
    @Column(length = 3, name = "product_number")
    private Integer productNumber;
    /**退换金额**/
    @Column(length = 9, name = "refund_amount")
    private Float refundAmount;
    /**商品图片链接**/
    @Column(length = 200, name = "product_image_url")
    private String imageUrl;
    /**申请类型:1为退款,2为退货,默认为1**/
    @Column(length = 1, name = "refund_type")
    private Integer refundType;
    /**卖家处理状态:1为待审核,2为同意,3为不同意,默认为1**/
    @Column(length = 1, name = "center_state")
    private Integer centerState;
    /**申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1**/
    @Column(length = 1, name = "refund_state")
    private Integer refundState;
    /**物流状态**/
    @Column(length = 1, name = "product_state")
    /**物流状态:1为待发货,2为待收货,3为未收到,4为已收货,默认为1**/
    private Integer productState;
    /**卖家处理时间**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 20, name = "center_date")
    private Date centerDate;
    /**卖家id**/
    @Column(length = 11, name = "center_id")
    private Integer centerId;
    /**管理员处理时间**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 20, name = "manage_date")
    private Date manageDate;
    @Column(length = 11, name = "manage_id")
    private Integer manageId;
    /**买家申请时间**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 20, name = "user_date")
    private Date buyerDate;
    /**退换时买家截图**/
    @Column(length = 50, name = "refund_pic")
    private String refundPicture;
    /**退换原因**/
    @Column(length = 50, name = "buyer_message")
    private String buyerMessage;
    /**卖家备注**/
    @Column(length = 50, name = "seller_message")
    private String sellerMessage;
    /**管理员备注**/
    @Column(length = 50, name = "admin_message")
    private String adminMessage;
    /**物流公司编号**/
    @Column(name = "express_no", nullable = true, length = 15)
    private String expressNumber;
    /**物流单号**/
    @Column(name = "express_id", nullable = true, length = 15)
    private String expressId;

    /**买家退货时发货时间**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 20, name = "buyer_ship_date")
    private Date buyerShipDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 20, name = "receive_date")
    private Date receiveDate;
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Float getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Float refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Date getCenterDate() {
        return centerDate;
    }

    public void setCenterDate(Date centerDate) {
        this.centerDate = centerDate;
    }

    public Date getManageDate() {
        return manageDate;
    }

    public void setManageDate(Date manageDate) {
        this.manageDate = manageDate;
    }

    public Date getBuyerDate() {
        return buyerDate;
    }

    public void setBuyerDate(Date buyerDate) {
        this.buyerDate = buyerDate;
    }

    public String getRefundPicture() {
        return refundPicture;
    }

    public void setRefundPicture(String refundPicture) {
        this.refundPicture = refundPicture;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage;
    }

    public String getAdminMessage() {
        return adminMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public Integer getRefundState() {
        return refundState;
    }

    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }

    public Integer getCenterState() {
        return centerState;
    }

    public void setCenterState(Integer centerState) {
        this.centerState = centerState;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Date getBuyerShipDate() {
        return buyerShipDate;
    }

    public void setBuyerShipDate(Date buyerShipDate) {
        this.buyerShipDate = buyerShipDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
}
