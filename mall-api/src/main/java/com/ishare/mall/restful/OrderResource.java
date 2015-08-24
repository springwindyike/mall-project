
package com.ishare.mall.restful;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.oauth.OAuthService;
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
	private static OrderService orderService;
	@Autowired
	private OAuthService oAuthService;
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
		return result;
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
		return order;
    }
    
	@RequestMapping(value = "/createBy/{createBy}/accessToken/{accessToken}", method = RequestMethod.GET)
	@ResponseBody
	public Object listByCreateBy(@NotEmpty @PathVariable("createBy") String createBy, @NotEmpty @PathVariable("accessToken") String accessToken) throws OAuthSystemException {
		if (!oAuthService.checkAccessToken(accessToken)) {  
			// 如果不存在/过期了，返回未验证错误，需重新验证  
			OAuthResponse response = OAuthRSResponse  
			        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)  
			        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)  
			        .buildHeaderMessage();
	    
			return response;  
		}  
		//用findOne立即加载实体对象
		List<Order> orderList = orderService.findByCreateBy(createBy);
		return orderList;
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
		return orderService.createNewOrder(order);
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

	public static void main(String[] args) {
		Order order = new Order();
		order.getChannel();
		order.getDeliverFee();
		order.getNote();
		orderService.createNewOrder(order);
	}
    
}
