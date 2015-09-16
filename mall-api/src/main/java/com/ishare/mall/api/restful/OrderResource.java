
package com.ishare.mall.api.restful;

import com.ishare.mall.api.form.OrderForm;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.api.utils.page.PageUtils;
import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderTotalDTO;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

/**
 * Created by YinLin on 2015/7/30.
 * Description:订单接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/orders")
public class OrderResource extends BaseResource {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OAuthService oAuthService;
    /**
     * 添加订单
     */
	@RequestMapping(value = "buy", method = RequestMethod.POST)
	public OrderDetailDTO buy(OrderForm orderForm) {
		ExchangeDTO exchangeDTO = orderForm.toExchangeDTO();
		exchangeDTO.setAccount(oAuthService.getAccountByAccessToken(orderForm.getToken()));
		exchangeDTO.setClientId(oAuthService.getAuthObjectByAccessToken(orderForm.getToken()).getClientId());

		return null;
    }

    /**
     * 确认订单会跳转支付
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
	@RequestMapping(value = "/offset/{offset}/limit/{limit}/accessToken/{accessToken}", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit,  @NotEmpty @PathVariable("accessToken") String accessToken) throws OAuthSystemException {
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
		if (!oAuthService.checkAccessToken(accessToken)) {  
			// 如果不存在/过期了，返回未验证错误，需重新验证  
			OAuthResponse response = OAuthRSResponse  
			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)  
			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)  
			        .buildHeaderMessage();
	    
			return response;  
		}  
		//用findOne立即加载实体对象
		
    Page<Order> result = orderService.search(null, pageRequest);
    return PageUtils.mapper(result, pageRequest, OrderDetailDTO.class);
    }

    /**
     * 通过订单ID获取详细订单信息
  * @param id
  * @return Order 返回的数据对象
	 */
	@RequestMapping(value = "/{id}/accessToken/{accessToken}", method = RequestMethod.GET)
	@ResponseBody
	public Object detail(@NotEmpty @PathVariable("id") String id, @NotEmpty @PathVariable("accessToken") String accessToken) throws OAuthSystemException {
		if (!oAuthService.checkAccessToken(accessToken)) {  
			// 如果不存在/过期了，返回未验证错误，需重新验证  
			OAuthResponse response = OAuthRSResponse  
			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)  
			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)  
			        .buildHeaderMessage();
	    
			return response;  
		}  
		//用findOne立即加载实体对象
		Order order = orderService.findOne(id);
		return (OrderDetailDTO) MapperUtils.map(order, OrderDetailDTO.class);
    }
    
	@RequestMapping(value = "/offset/{offset}/limit/{limit}/createBy/{createBy}/accessToken/{accessToken}", method = RequestMethod.GET)
	@ResponseBody
	public Object listByCreateBy(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, @NotEmpty @PathVariable("createBy") String createBy, @NotEmpty @PathVariable("accessToken") String accessToken) throws OAuthSystemException {
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
		if (!oAuthService.checkAccessToken(accessToken)) {  
			// 如果不存在/过期了，返回未验证错误，需重新验证
			OAuthResponse response = OAuthRSResponse  
			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)  
			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)  
			        .buildHeaderMessage();
	    
			return response;  
		}  
		//用findOne立即加载实体对象
		 Page<Order> result = orderService.search(null, pageRequest);
    return PageUtils.mapper(result, pageRequest, OrderDetailDTO.class);
    }
	
	@RequestMapping(value = "/accessToken/{accessToken}/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object addOrder(@NotEmpty @PathVariable("accessToken") String accessToken, @ModelAttribute("orderAttribute") Order order) throws OAuthSystemException {
		if (!oAuthService.checkAccessToken(accessToken)) {  
			// 如果不存在/过期了，返回未验证错误，需重新验证  
			OAuthResponse response = OAuthRSResponse  
			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)  
			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)  
			        .buildHeaderMessage();
	    
			return response;
		}  
		//用findOne立即加载实体对象
//		return orderService.createNewOrder(order);
		Order newOrder = orderService.createNewOrder(order);
		return (OrderDetailDTO) MapperUtils.map(newOrder, OrderDetailDTO.class);
    }
	
    /**
     *  平台销售总额统计
     */
    @RequestMapping(value = "summeryMoney", method = RequestMethod.GET)
    @ResponseBody
    public Object summeryMoney() {
    	System.out.println(orderService);
    	List <Order> orderList = orderService.findTotalSales();
    	if (orderList!=null){

        	float summery = 0.0f;
        			for(Order order: orderList) {
        				 summery += order.getTotalPrice();
        			}
        			OrderTotalDTO ot = new OrderTotalDTO();
        			ot.setTotalMoney(summery);
        			return ot;
      
        } else{
        	return null;
        }
    	}

}