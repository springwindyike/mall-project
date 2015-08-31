package com.ishare.mall.restful;

import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.order.OrderService;

/**
 * Created by YinLin on 2015/7/30.
 * Description:产品接口相关
 * Version 1.0
 */
@RestController
@RequestMapping("/channels")
public class ChannelResource {

    private static final Logger log = LoggerFactory.getLogger(ChannelResource.class);
    @Autowired
    private ChannelService channelService;
    @Autowired
			private OrderService orderService;
    /**
     * 通过渠道ID获取销售额 /summeryMoney/{id} GET
     * @param id 商品ID
     * @return ProductDetailDTO 返回的数据对象
     */
 
    @RequestMapping(value = "summeryMoney/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ChannelDTO summeryMoney(@NotEmpty @PathVariable("id") Integer id) {
    	Set <Order> orderList = channelService.findAllOrderByChannelId(id);
    	float summery = 0.0f;
    			for(Order order: orderList) {
    				 summery += order.getTotalPrice();
    			}
    			ChannelDTO cdto = new ChannelDTO();
    			cdto.setChannelId(id);
    			cdto.setTotalMoney(summery);
    			return cdto;
    }
}
