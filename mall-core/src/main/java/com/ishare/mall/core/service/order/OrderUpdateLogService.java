package com.ishare.mall.core.service.order;

import com.ishare.mall.core.model.order.OrderActionLog;

/**
 * Created by Zhang Zhaoxin on 2015/9/25.
 * Description :
 * Version 1.0
 */
public interface OrderUpdateLogService {
	OrderActionLog save(OrderActionLog orderActionLog);
}
