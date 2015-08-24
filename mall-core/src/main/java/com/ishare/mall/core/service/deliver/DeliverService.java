package com.ishare.mall.core.service.deliver;

import com.ishare.mall.core.model.order.OrderDeliverInfo;

/**
 * Created by liaochenglei on 2015/8/24.
 * Description :
 * Version 1.0
 */
public interface DeliverService {
	OrderDeliverInfo save(OrderDeliverInfo orderDeliverInfo);
	void delete (Integer id);
}
