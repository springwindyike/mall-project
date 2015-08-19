
package com.ishare.mall.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.order.OrderService;

/**
 * Created by YinLin on 2015/7/30.
 * Description:订单接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void detail() {

    }
    
    /**
     *  平台销售总额统计
     */
    @RequestMapping(value = "summeryMoney", method = RequestMethod.GET)
    @ResponseBody
    public float summeryMoney() {
    	List <Order> orderList = orderService.findTotalSales();
    	float summery = 0.0f;
    			for(Order order: orderList) {
    				 summery += order.getTotalPrice();
    			}
    			return summery;
  
    }
}
