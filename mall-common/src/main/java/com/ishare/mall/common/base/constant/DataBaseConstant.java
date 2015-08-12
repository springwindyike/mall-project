package com.ishare.mall.common.base.constant;

/**
 * Created by YinLin on 2015/7/31.
 * Description:
 * Version 1.0
 */
public interface DataBaseConstant {
    /**
     * 公共
     */
    interface Common {
        String DATABASE_PRE = "db_";
        String TABLE_PRE    = "t_";
    }

    /**
     * 表名
     */
    interface Table {
        //商品表
        String TABLE_PRODUCT_NAME = Common.TABLE_PRE + "product";
        //商品样式表
        String TABLE_PRODUCT_STYLE_NAME = TABLE_PRODUCT_NAME + "_style";
        //商品样式图片表
        String TABLE_PRODUCT_STYLE_IMAGE_NAME = TABLE_PRODUCT_STYLE_NAME + "_image";
        //商品类型表
        String TABLE_PRODUCT_TYPE_NAME = TABLE_PRODUCT_NAME + "_type";
        //品牌表
        String TABLE_BRAND_NAME   = Common.TABLE_PRE + "brand";
        //厂商表
        String TABLE_MANUFACTURER_NAME = Common.TABLE_PRE + "manufacturer";
        //渠道
        String TABLE_CHANNEL_NAME = Common.TABLE_PRE + "channel";
        //成员 or 操作员
        String TABLE_MEMBER_NAME = Common.TABLE_PRE + "member";
        //订单表
        String TABLE_ORDER_NAME = Common.TABLE_PRE + "order";
        //订单号表
        String TABLE_ORDER_ID_NAME = TABLE_ORDER_NAME + "_id";
        //订单附加信息表
        String TABLE_ORDER_MESSAGE_NAME = TABLE_ORDER_NAME + "_message";
        //订单明细项表
        String TABLE_ORDER_ITEM_NAME = TABLE_ORDER_NAME + "_item";
        //订单订购者信息表
        String TABLE_ORDER_CONTACT_INFO_NAME = TABLE_ORDER_NAME + "_contact_info";
        //订单收货人信息表
        String TABLE_ORDER_DELIVER_INFO_NAME = TABLE_ORDER_NAME + "_deliver_info";
        //物流信息表
        String TABLE_LOGISTICS_NAME = Common.TABLE_PRE + "logistics";

    }
}
