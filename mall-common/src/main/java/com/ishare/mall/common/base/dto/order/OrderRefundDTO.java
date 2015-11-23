package com.ishare.mall.common.base.dto.order;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by wanghao on 2015/11/10.
 */
@XmlRootElement
@JsonAutoDetect
public class OrderRefundDTO extends GenericDTO {

    /**退货、退款id**/
    private String refundId;
    /**订单id**/
    private String orderId;
    /**渠道id**/
    private Integer channelId;
    /**渠道名字**/
    private String channelName;
    /**买家id**/
    private String buyerId;
    /**买家名字**/
    private String buyerName;
    /**产品id**/
    private Integer productId;
    /**产品名字**/
    private String productName;
    /**退换商品数量**/
    private Integer productNumber;
    /**退换金额**/
    private Float refundAmount;
    /**商品图片链接**/
    private String imageUrl;
    /**申请类型:1为退款,2为退货,默认为1**/
    private Integer refundType;
    private String refundTypeStr;
    /**卖家处理状态:1为待审核,2为同意,3为不同意,默认为1**/
    private Integer centerState;
    private String centerStateStr;
    /**申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1**/
    private Integer refundState;
    private String refundStateStr;
    /**物流状态:1为待发货,2为待收货,3为未收到,4为已收货,默认为1**/
    private Integer productState;
    /**卖家处理时间**/
    private Date centerDate;
    private String centerDateStr;
    /**管理员处理时间**/
    private Date manageDate;
    private String manageDateStr;
    /**买家申请时间**/
    private Date buyerDate;
    private String buyerDateStr;
    /**退换时买家截图**/
    private String refundPicture;
    /**退换原因**/
    private String buyerMessage;
    /**卖家备注**/
    private String sellerMessage;
    /**管理员备注**/
    private String adminMessage;
    /**物流公司编号**/
    private String expressNumber;
    /**物流单号**/
    private String expressId;
    /**卖家id**/
    private Integer centerId;
    /**管理员Id**/
    private Integer manageId;
    /**买家退货时发货时间**/
    private Date buyerShipDate;
    private String buyerShipDateStr;
    /**收货时间**/
    private  Date receiveDate;
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

    public Integer getCenterState() {
        return centerState;
    }

    public void setCenterState(Integer centerState) {
        this.centerState = centerState;
    }

    public Integer getRefundState() {
        return refundState;
    }

    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }

    public String getCenterDateStr() {
        return centerDateStr;
    }

    public void setCenterDateStr(String centerDateStr) {
        this.centerDateStr = centerDateStr;
    }

    public String getBuyerDateStr() {
        return buyerDateStr;
    }

    public void setBuyerDateStr(String buyerDateStr) {
        this.buyerDateStr = buyerDateStr;
    }

    public String getManageDateStr() {
        return manageDateStr;
    }

    public void setManageDateStr(String manageDateStr) {
        this.manageDateStr = manageDateStr;
    }

    public String getRefundTypeStr() {
        return refundTypeStr;
    }

    public void setRefundTypeStr(String refundTypeStr) {
        this.refundTypeStr = refundTypeStr;
    }

    public String getCenterStateStr() {
        return centerStateStr;
    }

    public void setCenterStateStr(String centerStateStr) {
        this.centerStateStr = centerStateStr;
    }

    public String getRefundStateStr() {
        return refundStateStr;
    }

    public void setRefundStateStr(String refundStateStr) {
        this.refundStateStr = refundStateStr;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public Date getBuyerShipDate() {
        return buyerShipDate;
    }

    public void setBuyerShipDate(Date buyerShipDate) {
        this.buyerShipDate = buyerShipDate;
    }

    public String getBuyerShipDateStr() {
        return buyerShipDateStr;
    }

    public void setBuyerShipDateStr(String buyerShipDateStr) {
        this.buyerShipDateStr = buyerShipDateStr;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
}
