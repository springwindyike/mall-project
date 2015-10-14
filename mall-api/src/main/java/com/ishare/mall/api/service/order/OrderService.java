package com.ishare.mall.api.service.order;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.page.PageRequestDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;

/**
 * Created by YinLin on 2015/9/24.
 * Description : 客户端 order Service
 * Version 1.0
 */
public interface OrderService {

    /**
     * 通过订单号查找订单
     * @param id
     * @return
     * @throws ApiLogicException
     */
    OrderDetailDTO findOne(String id) throws ApiLogicException;

    /**
     * 创建订单
     * @param exchangeDTO
     * @return
     * @throws ApiLogicException
     */
    OrderDetailDTO create(ExchangeDTO exchangeDTO) throws ApiLogicException;

    /**
     * 支付完成
     * @param notify
     * @return
     * @throws ApiLogicException
     */
    OrderDetailDTO payComplete(AliPayNotifyDTO notify) throws ApiLogicException;

    /**
     * 获取所在渠道订单列表
     * @param account
     * @param clientId
     * @param pageRequest
     * @return
     */
    Response<PageDTO<OrderDetailDTO>> listByAccount(String account, String clientId, PageRequestDTO pageRequest);
}
