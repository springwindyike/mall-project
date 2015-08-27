package com.ishare.mall.restful;

import com.ishare.mall.common.base.dto.pay.AliPayDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.oauth.OAuthService;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.service.pay.AliPayService;
import com.ishare.mall.core.status.OrderState;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YinLin on 2015/8/24.
 * Description : 支付接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/payment")
public class PaymentResource {

    private static final Logger log = LoggerFactory.getLogger(PaymentResource.class);

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
    public Object pay(Model model, HttpServletRequest request) {
//        String token = request.getParameter("token");
//        if (token == null || !oAuthService.checkAccessToken(token)) {
//           // 如果不存在/过期了，返回未验证错误，需重新验证
//            OAuthResponse response = null;
//            try {
//                response = OAuthRSResponse
//                .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//                .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
//                .buildHeaderMessage();
//            } catch (OAuthSystemException e) {
//                e.printStackTrace();
//            }
//            return response;
//        }
        String id = "20150825000003";//request.getParameter("id");
        System.out.println(id);
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
        AliPayDTO aliPayDTO = this.createAliPayDTO("", order);

        String payForm = aliPayService.create(aliPayDTO);
        model.addAttribute("returnContent", payForm);
        log.debug(payForm);
        return "pay/pay";
    }

    /**
     * 阿里支付回调
     * @return
     */
    @RequestMapping(value = "/pay/notify", method = RequestMethod.POST)
    public Object payNotify(AliPayNotifyDTO notify, HttpServletRequest request) {
        if(!aliPayService.verifyNotifyUrl(notify, null)) {
            return null;
        }
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //如果校验码为空
        if (StringUtils.isBlank(notify.getSign())) {
            log.info("notify from aliPay, sign is null, notify={}, params={}",
                    new Object[]{notify, params});
            return null;
        }

        if (aliPayService.verifyNotifySign(params, notify.getSign())) {
            if (!StringUtils.isBlank(notify.getSign()) &&
                (notify.getTrade_status().equals("TRADE_FINISHED") ||
                 notify.getTrade_status().equals("TRADE_SUCCESS")))
            {

            }
        }

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