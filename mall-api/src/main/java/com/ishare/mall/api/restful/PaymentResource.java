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
import com.ishare.mall.common.base.general.Response;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @RequestMapping(value = "/back/{token}", method = RequestMethod.GET, produces = {"application/json"})
    public Object back(@NotEmpty @PathVariable("token")String token, AliPayNotifyDTO notify, HttpServletRequest request) {
        log.debug("支付宝回调");
        log.debug(notify.toString());

        if(!aliPayService.verifyNotifyUrl(notify, null)) {
            throw new ApiLogicException("支付认证失败", HttpStatus.UNAUTHORIZED);
        }
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext(); ) {
            String name = iterator.next();
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
            return new ApiLogicException("支付宝支付回调失败");
        }
        Response response = new Response();
        response.setCode(200);
        response.setMessage("支付成功");
        if (aliPayService.verifyNotifySign(params, notify.getSign())) {
            if (isPaySuccess(notify)) {
                //支付成功
                //调用bizapp修改订单状态和记录订单日志，这里会有并发问题为统一处理，需放到service统一处理
                response.setData(orderService.payComplete(notify));
            }
        }

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


    /**
     * 简单验证支付是否成功
     * @param notify
     * @return
     */
    private boolean isPaySuccess(AliPayNotifyDTO notify) {
        return !StringUtils.isBlank(notify.getTrade_status()) && (notify.getTrade_status().equals("TRADE_FINISHED") || notify.getTrade_status().equals("TRADE_SUCCESS"));
    }

    public static Logger getLog() {
        return log;
    }
}
