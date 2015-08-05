package com.tianyu.jty.shop.service;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.shop.dao.GoodsDao;
import com.tianyu.jty.shop.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品service
 * @author ty
 * @date 2015年1月22日
 */
@Service
@Transactional(readOnly=true)
public class GoodsService extends BaseService<Goods, Integer> {
	
	@Autowired
	private GoodsDao goodsDao;

	@Override
	public HibernateDao<Goods, Integer> getEntityDao() {
		return goodsDao;
	}

}
