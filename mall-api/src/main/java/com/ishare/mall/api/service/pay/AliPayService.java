package com.ishare.mall.api.service.pay;

import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;

/**
 * Created by YinLin on 2015/9/24.
 * Description :
 * Version 1.0
 */
public interface AliPayService {
    String create(String accessToken, OrderDetailDTO order) throws ApiLogicException;
}
