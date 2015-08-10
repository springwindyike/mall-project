
package com.ishare.mall.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by YinLin on 2015/7/30.
 * Description:订单接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderResource {
    /**
     * 添加订单
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add() {

    }

    /**
     * 确认订单
     */
    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public void confirm() {

    }

    /**
     * 详细订单信息
     */
    public void detail() {

    }
}
