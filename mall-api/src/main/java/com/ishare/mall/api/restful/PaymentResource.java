package com.ishare.mall.api.restful;

import com.ishare.mall.api.annotation.AccessToken;
import com.ishare.mall.api.form.pay.PayForm;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.api.service.order.OrderService;
import com.ishare.mall.api.service.pay.AliPayService;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.enumeration.OrderState;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

        String id = payForm.getOrderId();

        OrderDetailDTO order = orderService.findOne(id);

        //订单状态不是等待支付
        if (order.getState() != OrderState.WAIT_PAYMENT) {
            throw new ApiLogicException("订单不是等待支付状态", HttpStatus.BAD_REQUEST);
        }
        String payFormHtml = aliPayService.create(payForm.getAccess_token(), order);
        model.addAttribute("returnContent", payFormHtml);
        log.debug(payFormHtml);
        return "pay/pay";
    }

    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public Object back(AliPayNotifyDTO notify, HttpServletRequest request) {
        log.debug("支付宝回调");
        log.debug(notify.toString());
//        if(!aliPayService.verifyNotifyUrl(notify, null)) {
//            return null;
//        }
//        // 获取支付宝POST过来反馈信息
//        Map<String, String> params = new HashMap<>();
//        Map requestParams = request.getParameterMap();
//        for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext(); ) {
//            String name = iterator.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//            params.put(name, valueStr);
//        }
//        //如果校验码为空
//        if (StringUtils.isBlank(notify.getSign())) {
//            log.info("notify from aliPay, sign is null, notify={}, params={}",
//                    new Object[]{notify, params});
//            return null;
//        }
//        if (aliPayService.verifyNotifySign(params, notify.getSign())) {
//            if (isPaySuccess(notify)) {
//                OrderPayLog payLog = orderPayLogService.findByOrderId(notify.getOut_trade_no());
//                if (payLog != null && payLog.getPayType().equals(PayType.NEW)) {
//                    payLog.setAmount(new BigDecimal(notify.getTotal_fee()));
//                    payLog.setTansId(notify.getTrade_no());
//                    payLog.setUpdateTime(new Date());
//                    log.warn("pay from pc and pay by blank amount payment={}", "支付宝");
//                    //更新支付log状态
//                    orderPayLogService.updateForProcess(payLog);
//                    //设置支付完成
//                    orderService.payComplete(notify.getOut_trade_no());
//                }
//            }
//        }
        return null;
    }

    public static Logger getLog() {
        return log;
    }
}
