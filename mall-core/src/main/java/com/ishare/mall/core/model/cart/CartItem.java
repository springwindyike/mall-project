package com.ishare.mall.core.model.cart;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductStyle;

/**
 * Created by YinLin on 2015/8/3.
 * Description:
 * Version 1.0
 */
public class CartItem extends BaseEntity {
    private Integer id;
    /**购物车商品数量*/
    private Integer amount;
    /**购物车商品**/
    private Product product;
    /**商品样式**/
    private ProductStyle style;
}
