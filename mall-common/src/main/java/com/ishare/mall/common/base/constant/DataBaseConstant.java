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
        String TABLE_PRODUCT_NAME                       = Common.TABLE_PRE + "product";
        //商品样式表
        String TABLE_PRODUCT_STYLE_NAME                 = TABLE_PRODUCT_NAME + "_style";
        //商品样式图片表
        String TABLE_PRODUCT_STYLE_IMAGE_NAME           = TABLE_PRODUCT_STYLE_NAME + "_image";
        //商品图文描述
        String TABLE_PRODUCT_INTRO_IMAGE_NAME           = TABLE_PRODUCT_NAME + "_intro_image";
        //商品相册图片
        String TABLE_PRODUCT_PHOTO_IMAGE_NAME           = TABLE_PRODUCT_NAME + "_photo_image";
        //商品类型表
        String TABLE_PRODUCT_TYPE_NAME                  = TABLE_PRODUCT_NAME + "_type";
        //品牌表
        String TABLE_BRAND_NAME                         = Common.TABLE_PRE + "brand";
        //厂商表
        String TABLE_MANUFACTURER_NAME                  = Common.TABLE_PRE + "manufacturer";
        //渠道
        String TABLE_CHANNEL_NAME                       = Common.TABLE_PRE + "channel";
        //成员 or 操作员
        String TABLE_MEMBER_NAME                        = Common.TABLE_PRE + "member";
        //订单表
        String TABLE_ORDER_NAME                         = Common.TABLE_PRE + "order";
        //订单号表
        String TABLE_ORDER_ID_NAME                      = TABLE_ORDER_NAME + "_id";
        //订单附加信息表
        String TABLE_ORDER_MESSAGE_NAME                 = TABLE_ORDER_NAME + "_message";
        //订单明细项表
        String TABLE_ORDER_ITEM_NAME                    = TABLE_ORDER_NAME + "_item";
        //订单订购者信息表
        String TABLE_ORDER_CONTACT_INFO_NAME            = TABLE_ORDER_NAME + "_contact_info";
        //订单收货人信息表
        String TABLE_ORDER_DELIVER_INFO_NAME            = TABLE_ORDER_NAME + "_deliver_info";
        //物流信息表
        String TABLE_LOGISTICS_NAME                     = Common.TABLE_PRE + "logistics";
        //支付日志
        String TABLE_ORDER_PAY_LOG_NAME                 = TABLE_ORDER_NAME + "_pay_log";
        //角色
        String TABLE_ROLE_NAME                          = Common.TABLE_PRE + "role";
        //详细权限
        String TABLE_PERMISSION_NAME                    = Common.TABLE_PRE + "permission";
        //用户角色中间表
        String TABLE_MEMBER_ROLE_NAME                   = TABLE_MEMBER_NAME + "_role";
        //角色权限表
        String TABLE_ROLE_PERMISSION                    = TABLE_ROLE_NAME + "_permission";
        //属性表
        String TABLE_ATTRIBUTE_NAME                     = Common.TABLE_PRE + "attribute";
        //属性组表
        String TABLE_ATTRIBUTE_GROUT_NAME               = TABLE_ATTRIBUTE_NAME + "_group";
        //商品属性表
        String TABLE_PRODUCT_ATTRIBUTE_NAME             = TABLE_PRODUCT_NAME + "_attribute";
        //地区
        String TABLE_REGION_NAME                        = Common.TABLE_PRE + "region";
        //第三方信息
        String TABLE_ORIGIN_NAME                        = Common.TABLE_PRE + "origin";
        //订单修改日志
        String TABLE_ORDER_UPDATE_LOG_NAME              = TABLE_ORDER_NAME + "_update_log";
    }
}
