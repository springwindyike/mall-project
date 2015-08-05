package com.ishare.mall.core.model.cart;

import com.google.common.collect.Sets;
import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.member.Member;

import java.util.Set;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 购物车 表
 * Version 1.0
 */
public class Cart extends BaseEntity {
    private Integer id;
    private Member member;
    private Set<CartItem> cartItems = Sets.newConcurrentHashSet();
}
