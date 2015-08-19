
package com.ishare.mall.restful;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 通过当前页和每页数量获取订单列表
	 * @param offset 分页下标
	 * @param limit 分页size
	 * @return 返回Order
     */
	@RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
	@ResponseBody
	public Page<Order> list(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
		Page<Order> result = orderService.search(null, pageRequest);
		return result;
    }

    /**
     * 通过订单ID获取详细订单信息
  * @param id
  * @return Order 返回的数据对象
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Order detail(@NotEmpty @PathVariable("id") String id) {
		//用findOne立即加载实体对象
		Order order = orderService.findOne(id);
		return order;
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
