package com.ishare.mall.restful;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductListDTO;
import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.information.OrderItemService;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.status.OrderItemSort;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import com.ishare.mall.utils.page.PageUtils;

/**
 * Created by liaochenglei on 2015/8/18.
 * Description:产品接口相关
 * Version 1.0
 */
@RestController
@RequestMapping("/orderitems")
public class OrderItemResource {

    private static final Logger log = LoggerFactory.getLogger(OrderItemResource.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService productReturnService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrderItemDetailDTO get(@NotEmpty @PathVariable("id") Integer id) {
        //用findOne立即加载实体对象
    	OrderItem orderItem = productReturnService.findOne(id);
        //转换为接口输出数据对象DTO 映射规则需要自己添加
        return (OrderItemDetailDTO) MapperUtils.map(orderItem, OrderItemDetailDTO.class);
    }
    
    /**
     * 通过电话号码获取所有退换货商品列表 格式/offset/{offset}/limit/{limit}/{phone} GET
     * @param offset 当前页
     * @param limit 每页数据
     * @param phone 电话号码
     * @return 返回list
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}/{phone}", method = RequestMethod.GET)
    public Page<OrderItemDetailDTO> get(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("phone")String phone) {
    	 PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
         Map<String, Object> searchParams = Maps.newConcurrentMap();
         searchParams.put("EQ_createBy",phone);
         //searchParams.put("NEQ_exchangeOrBack", OrderItemSort.BACK);
         Page<OrderItem> result = productReturnService.search(searchParams, pageRequest);
        return PageUtils.mapper(result, pageRequest, OrderItemDetailDTO.class);
         //return null;
    }

}
