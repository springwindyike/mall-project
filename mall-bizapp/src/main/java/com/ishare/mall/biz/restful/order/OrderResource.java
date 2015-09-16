package com.ishare.mall.biz.restful.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.order.OrderService;

/**
 * Created by ZhangZhaoxin on 2015/9/15.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Order.REQUEST_MAPPING)
public class OrderResource {
	
    private static final Logger log = LoggerFactory.getLogger(OrderResource.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private ChannelService channelService;

    public static Logger getLog() {
        return log;
    }

    /**
     * 获取当前渠道下所有的order
     *
     * @return Page<MemberDetailDTO>
     */
    @RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public OrderDetailDTO findByChannelId(@RequestBody OrderDetailDTO orderDetailDTO) {
        List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
        int offset = orderDetailDTO.getOffset();
        int limit = orderDetailDTO.getLimit();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "account");
        Integer channelId = orderDetailDTO.getChannelId();
        Page<Order> result = orderService.findByChannelId(channelId, pageRequest);
        PageDTO pageDTO = new PageDTO();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Order> list = result.getContent();
            for (Order order:list){
                BeanUtils.copyProperties(order, orderDetailDTO);
                orderDetailDTO.setChannelId(order.getChannel().getId());
                listOrder.add(orderDetailDTO);
            					}
			        pageDTO.setContent(listOrder);
			        pageDTO.setTotalPages(result.getTotalPages());
			        pageDTO.setiTotalDisplayRecords(result.getTotalElements());
			        pageDTO.setiTotalRecords(result.getTotalElements());
			        orderDetailDTO.setPageDTO(pageDTO);
        				}
        return orderDetailDTO;
    		}

}
