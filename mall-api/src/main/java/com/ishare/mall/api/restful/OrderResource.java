
package com.ishare.mall.api.restful;

import com.ishare.mall.api.annotation.AccessToken;
import com.ishare.mall.api.form.order.OrderForm;
import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.api.service.oauth.OAuthService;
import com.ishare.mall.api.service.order.OrderService;
import com.ishare.mall.api.utils.page.PageUtils;
import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

/**
 * Created by YinLin on 2015/7/30.
 * Description:订单接口相关
 * Version 1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderResource extends BaseResource {

    private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private OAuthService oAuthService;
    /**
     * 添加订单
     * 有@AccessToken的标注表示必须要token
     */
    @AccessToken
	@RequestMapping(value = "create", method = {RequestMethod.POST, RequestMethod.GET}, produces = {"application/json"})
	public ResponseEntity create(@Valid OrderForm orderForm, BindingResult br) {

        if (br.hasErrors()) {
            throw new ApiLogicException(br.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

		ExchangeDTO exchangeDTO = orderForm.toExchangeDTO();
        //从缓存中通过accessToken获取用户信息
		exchangeDTO.setAccount(oAuthService.getAccountByAccessToken(orderForm.getAccess_token()));
		exchangeDTO.setClientId(oAuthService.getAuthObjectByAccessToken(orderForm.getAccess_token()).getClientId());
        Response< List<OrderDetailDTO>> response = new Response<List<OrderDetailDTO>>();
        //创建订单
        List<OrderDetailDTO> orderDetailDTOList = orderService.create(exchangeDTO);
        response.setData(orderDetailDTOList);
        response.setCode(200);
        //请求成功
		return new ResponseEntity(response, HttpStatus.OK);
    }

    @AccessToken
    @RequestMapping(value = "{id}", method = {RequestMethod.POST, RequestMethod.GET}, produces = {"application/json"})
    public ResponseEntity get(@NotEmpty @PathVariable("id")String  id, HttpServletRequest request) {
        String token = request.getParameter("access_token");
        OrderDetailDTO orderDetailDTO = orderService.findOne(id);
        String account = oAuthService.getAccountByAccessToken(token);
        if (!account.equals(orderDetailDTO.getCreateBy())) {
            throw new ApiLogicException("订单不存在", HttpStatus.NOT_FOUND);
        }
        Response<OrderDetailDTO> response = new Response<>();
        response.setCode(200);
        response.setData(orderDetailDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @AccessToken
    @RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity list(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit, HttpServletRequest request) {
        String token = request.getParameter("access_token");
        String account = oAuthService.getAccountByAccessToken(token);
        String clientId = oAuthService.getClientId(token);
        Response<PageDTO<OrderDetailDTO>> response = orderService.listByAccount(account, clientId, PageUtils.getPageRequestDTO(offset, limit, "id"));
        return new ResponseEntity(response, HttpStatus.OK);
    }

}