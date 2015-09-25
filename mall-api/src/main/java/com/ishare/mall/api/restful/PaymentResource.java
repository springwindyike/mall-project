package com.ishare.mall.api.restful;

import com.ishare.mall.api.annotation.AccessToken;
import com.ishare.mall.api.form.pay.PayForm;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.api.service.order.OrderService;
import com.ishare.mall.api.service.pay.AliPayService;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.enumeration.OrderState;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by YinLin on 2015/8/24.
 * Description : 支付接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/payment")
public class PaymentResource extends BaseResource {

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
     * @param payForm 传递必要支付参数
     * @return
     */
    @AccessToken
    @RequestMapping(value = "/pay",method = {RequestMethod.POST, RequestMethod.GET}, produces = {"application/json"})
    public Object pay(Model model, @Valid PayForm payForm, BindingResult br, HttpServletResponse response) {

        if (br.hasErrors()) {
            throw new ApiLogicException(br.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (payForm.getToken() == null || !oAuthService.checkAccessToken(payForm.getToken())) {
            throw new ApiLogicException(OAuthError.ResourceResponse.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }

        String id = payForm.getOrderId();

        OrderDetailDTO order = orderService.findOne(id);

        //订单状态不是等待支付
        if (order.getState() != OrderState.WAIT_PAYMENT) {
            throw new ApiLogicException("订单不是等待支付状态", HttpStatus.BAD_REQUEST);
        }
        String payFormHtml = aliPayService.create(payForm.getToken(), order);
        model.addAttribute("returnContent", payFormHtml);
        log.debug(payFormHtml);
        return "pay/pay";
    }

    public static Logger getLog() {
        return log;
    }
}
