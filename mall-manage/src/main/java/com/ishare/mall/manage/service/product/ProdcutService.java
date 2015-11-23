package com.ishare.mall.manage.service.product;

/**
 * Created by Administrator on 2015/11/23.
 */
public interface ProdcutService {
    long findCount();
    /**
     * 本周新增商品数量
     */
    long findThisWeekCount();
}
