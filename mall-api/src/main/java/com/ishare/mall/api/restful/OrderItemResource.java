package com.ishare.mall.api.restful;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.general.ErrorMessage;
import com.ishare.mall.common.base.general.ResponseMessage;
import com.ishare.mall.api.utils.page.PageUtils;

/**
 * Created by liaochenglei on 2015/8/18.
 * Description:产品接口相关
 * Version 1.0
 */
@RestController
@RequestMapping("/orderitems")
public class OrderItemResource {

//    private static final Logger log = LoggerFactory.getLogger(OrderItemResource.class);
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private OrderItemService orderItemService;
//			@Autowired
//			private OAuthService oAuthService;
//
///**
// * 退换货详情
// */
//    @RequestMapping(value = "/{id}/accessToken/{accessToken}", method = RequestMethod.GET)
//    public OrderItemDetailDTO get(@NotEmpty @PathVariable("id") Integer id) {
//        //用findOne立即加载实体对象
//    	OrderItem orderItem = orderItemService.findOne(id);
//        //转换为接口输出数据对象DTO 映射规则需要自己添加
//        return (OrderItemDetailDTO) MapperUtils.map(orderItem, OrderItemDetailDTO.class);
//    }
//
//    /**
//     * 通过电话号码获取所有退换货商品列表 格式/offset/{offset}/limit/{limit}/{phone} GET
//     * @param offset 当前页
//     * @param limit 每页数据
//     * @param phone 电话号码
//     * @return 返回list
//     */
//    @RequestMapping(value = "/offset/{offset}/limit/{limit}/{phone}", method = RequestMethod.GET)
//    public Page<OrderItemDetailDTO> get(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("phone")String phone) {
//    	 PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
//         Map<String, Object> searchParams = Maps.newConcurrentMap();
//         searchParams.put("EQ_createBy",phone);
//         //searchParams.put("NEQ_exchangeOrBack", OrderItemSort.BACK);
//         Page<OrderItem> result = orderItemService.search(searchParams, pageRequest);
//        return PageUtils.mapper(result, pageRequest, OrderItemDetailDTO.class);
//         //return null;
//    }
//
//	@RequestMapping(value = "/accessToken/{accessToken}/addOrderItem", method = RequestMethod.POST)
//	@ResponseBody
//	public Object addOrderItem(@NotEmpty @PathVariable("accessToken") String accessToken, @ModelAttribute("orderItemAttribute") AddOrderItemForm addOrderItem) throws OAuthSystemException {
//		if (!oAuthService.checkAccessToken(accessToken)) {
//			// 如果不存在/过期了，返回未验证错误，需重新验证
//			OAuthResponse response = OAuthRSResponse
//			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
//			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
//			        .buildHeaderMessage();
//
//			return response;
//		}
//		OrderItem orderItem = orderItemService.createNewOrderItem(addOrderItem);
//		if(null == orderItem){
//			ErrorMessage error = new ErrorMessage();
//			error.setErrorCode(100001);
//			error.setMsg(ResponseMessage.findByValue(100001).toString());
//			return error;
//		}
//		return orderItem;
//    }

	
}
