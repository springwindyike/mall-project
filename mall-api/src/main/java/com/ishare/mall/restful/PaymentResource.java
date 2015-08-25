package com.ishare.mall.restful;

import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.oauth.OAuthService;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.service.pay.AliPayService;
import com.ishare.mall.core.status.OrderState;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by YinLin on 2015/8/24.
 * Description : 支付接口相关
 * Version 1.0
 */
@Controller("/payment")
public class PaymentResource {
    @Autowired
    private OAuthService oAuthService;
    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private OrderService orderService;

    /**
     * 支付接口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/pay",method = {RequestMethod.POST, RequestMethod.GET})
    public Object pay(Model model,HttpServletRequest request) {
        String token = request.getParameter("token");
        if (token == null || !oAuthService.checkAccessToken(token)) {
           // 如果不存在/过期了，返回未验证错误，需重新验证
            OAuthResponse response = null;
            try {
                response = OAuthRSResponse
                .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                .buildHeaderMessage();
            } catch (OAuthSystemException e) {
                e.printStackTrace();
            }
            return response;
        }
        String id = request.getParameter("id");
        //订单ID未传
        if (!StringUtils.isNotEmpty(id)) {
            return null;
        }
        Order order = orderService.findOne(id);
        //订单不存在
        if (order == null) {
            return null;
        }
        //订单状态不是等待支付
        if (order.getState() != OrderState.WAIT_PAYMENT) {

        }
        AliPayDTO aliPayDTO = this.createAliPayDTO(token, order);

        aliPayService.create(aliPayDTO);
        return null;
    }

    /**
     * 创建支付DTO
     * @param token
     * @param order
     * @return
     */
    private AliPayDTO createAliPayDTO(String token, Order order) {
        AliPayDTO aliPayDTO = new AliPayDTO();
        aliPayDTO.setAmount(new BigDecimal(order.getPayableFee()));
        aliPayDTO.setOrderID(order.getOrderId());
        aliPayDTO.setGoodsName(order.getOrderId());
        aliPayDTO.setReturnUrl("127.0.0.1");
        return  aliPayDTO;
    }
}
