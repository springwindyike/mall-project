package com.ishare.mall.biz.restful.order;

import com.ishare.mall.common.base.constant.CodeConstant;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.order.*;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.enumeration.OrderActionLogType;
import com.ishare.mall.common.base.enumeration.OrderState;
import com.ishare.mall.common.base.enumeration.PaymentWay;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.model.manage.ManageUser;
import com.ishare.mall.core.model.order.*;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.information.OrderItemService;
import com.ishare.mall.core.service.manageuser.ManageUserService;
import com.ishare.mall.core.service.member.MemberService;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import com.ishare.mall.core.utils.page.PageUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private OrderItemService orderItemService;
    @Autowired
    private ChannelService channelService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ManageUserService manageUserService;

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
    public Response findByChannelId(@RequestBody OrderDetailDTO orderDetailDTO) {
        List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
        int offset = orderDetailDTO.getOffset();
        int limit = orderDetailDTO.getLimit();
        Response response = new Response();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
        Integer channelId = orderDetailDTO.getChannelId();
		Integer sellerId = orderDetailDTO.getSellerId();
		Page<Order> result = null;
        try {
			if (sellerId != null){
				result = orderService.findBySellerId(sellerId, pageRequest);
			}else {
				result = orderService.findByChannelId(channelId, pageRequest);
			}

			PageDTO<OrderDetailDTO> pageDTO = new PageDTO<OrderDetailDTO>();
			if(result != null && result.getContent() != null && result.getContent().size()>0){
				List<Order> list = result.getContent();
				for (Order order:list){
					OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
					BeanUtils.copyProperties(order, innerOrderDetailDTO);
					innerOrderDetailDTO.setChannelId(order.getChannel().getId());
					innerOrderDetailDTO.setChannelName(order.getChannel().getName());
					innerOrderDetailDTO.setCreateBy(order.getCreateBy().getAccount());
					innerOrderDetailDTO.setStateValue(order.getState().getName());
					innerOrderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());
					innerOrderDetailDTO.setPaymentWay(order.getPaymentWay().getName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String newTime =  sdf.format(order.getCreateTime());
					innerOrderDetailDTO.setCreateTime(newTime);

					List<OrderItem> orderItems = orderItemService.findByOrderId(order.getOrderId());

					Iterator<OrderItem> it = orderItems.iterator();
					Set<OrderItemDetailDTO> items = new HashSet<OrderItemDetailDTO>();
					while (it.hasNext()) {
						OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
					  OrderItem orderItem = it.next();
					  BeanUtils.copyProperties(orderItem, orderItemDetailDTO);
					  items.add(orderItemDetailDTO);
					}
						innerOrderDetailDTO.setItems(items);
						listOrder.add(innerOrderDetailDTO);
					}
					pageDTO.setContent(listOrder);
					pageDTO.setTotalPages(result.getTotalPages());
					pageDTO.setITotalDisplayRecords(result.getTotalElements());
					pageDTO.setITotalRecords(result.getTotalElements());
					response.setData(pageDTO);
				}else {
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(0);
				pageDTO.setITotalDisplayRecords(0L);
				pageDTO.setITotalRecords(0L);
				response.setData(pageDTO);
			}
			return response;
		} catch (OrderServiceException e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
	}

    /**
     * 获取所有的order
     *
     * @return Page<MemberDetailDTO>
     */
    @RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response findAll(@RequestBody OrderDetailDTO orderDetailDTO) {
		List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
		int offset = orderDetailDTO.getOffset();
		int limit = orderDetailDTO.getLimit();
		Response response = new Response();
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
		try {
			Page<Order> result = orderService.findAll(pageRequest);
			PageDTO<OrderDetailDTO> pageDTO = new PageDTO<OrderDetailDTO>();
			if(result != null && result.getContent() != null && result.getContent().size()>0){
				List<Order> list = result.getContent();
				for (Order order:list){
					OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
					BeanUtils.copyProperties(order, innerOrderDetailDTO);
					innerOrderDetailDTO.setChannelId(order.getChannel().getId());
					innerOrderDetailDTO.setChannelName(order.getChannel().getName());
					innerOrderDetailDTO.setCreateBy(order.getCreateBy().getAccount());
					innerOrderDetailDTO.setStateValue(order.getState().getName());
					innerOrderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());
					innerOrderDetailDTO.setPaymentWay(order.getPaymentWay().getName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String newTime =  sdf.format(order.getCreateTime());
					innerOrderDetailDTO.setCreateTime(newTime);
					listOrder.add(innerOrderDetailDTO);
				}
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(result.getTotalPages());
				pageDTO.setITotalDisplayRecords(result.getTotalElements());
				pageDTO.setITotalRecords(result.getTotalElements());
				response.setData(pageDTO);
			}else {
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(0);
				pageDTO.setITotalDisplayRecords(0L);
				pageDTO.setITotalRecords(0L);
				response.setData(pageDTO);
			}
			return response;
		} catch (OrderServiceException e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
		}
    
    /**
     * 创建订单
     * @param exchangeDTO
     * @return Response
     * @throws OrderServiceException
     */
    @RequestMapping(value       = APPURIConstant.Order.REQUEST_MAPPING_CREATE,
                    method      = RequestMethod.POST,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json"},
                    consumes    = {"application/json"})
    public Response create(@RequestBody ExchangeDTO exchangeDTO) throws OrderServiceException{
        OrderDetailDTO orderDetailDTO = orderService.create(exchangeDTO);
        Response response = new Response();
        response.setCode(Response.Status.OK);
        response.setData(orderDetailDTO);
        return response;
    }
    
    /**
     * 查找一个订单
     * @param id
     * @return
     * @throws OrderServiceException
     */
    @RequestMapping(value       = APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_ID,
            method      = RequestMethod.GET,
            headers     = "Accept=application/xml, application/json",
            produces    = {"application/json", "application/xml"})
    public Response find(@NotEmpty @PathVariable("id") String id) throws OrderServiceException {
        Response response = new Response();
        Order order = orderService.findOne(id);
        try {
            OrderDetailDTO orderDetailDTO = (OrderDetailDTO) MapperUtils.map(order, OrderDetailDTO.class);
			Set<OrderItemDetailDTO> orderDetailDTOs = (Set<OrderItemDetailDTO>) MapperUtils.mapAsSet(orderItemService.findByOrderId(id), OrderItemDetailDTO.class);
			orderDetailDTO.setItems(orderDetailDTOs);
			response.setData(orderDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new OrderServiceException("类型转换错误");
        }
        return response;
    }

	/**
	 * 获取编辑数据
	 * @param orderDetailDTO
	 * @return
	 * @throws OrderServiceException
	 */
	@RequestMapping(value       = APPURIConstant.Order.REQUEST_MAPPING_GOTO_EDIT,
			method      = RequestMethod.POST,
			headers     = "Accept=application/xml, application/json",
			produces    = {"application/json", "application/xml"},
			consumes    = {"application/json", "application/xml"})
	public Response gotoEdit(@RequestBody OrderDetailDTO orderDetailDTO) throws OrderServiceException{
		Response response = new Response();

		Order order = orderService.findOne(orderDetailDTO.getOrderId());
		try {
			OrderDetailDTO newOrderDetailDTO = (OrderDetailDTO) MapperUtils.map(order, OrderDetailDTO.class);
			newOrderDetailDTO.setStateValue(order.getState().getName());
			newOrderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String newTime =  sdf.format(order.getCreateTime());
			newOrderDetailDTO.setCreateTime(newTime);

			List<OrderItem> orderItems = orderItemService.findByOrderId(order.getOrderId());

			Iterator<OrderItem> it = orderItems.iterator();
			Set<OrderItemDetailDTO> items = new HashSet<OrderItemDetailDTO>();
			while (it.hasNext()) {
				OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
				OrderItem orderItem = it.next();
				BeanUtils.copyProperties(orderItem, orderItemDetailDTO);
				items.add(orderItemDetailDTO);
			}
			newOrderDetailDTO.setItems(items);

			response.setData(newOrderDetailDTO);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			response.setData(orderDetailDTO);
			throw new OrderServiceException("类型转换错误");
		}
		return response;
	}

    /**
     * 发货
     * @param orderDetailDTO
     * @return
     * @throws OrderServiceException
     */
    @RequestMapping(value       = APPURIConstant.Order.REQUEST_MAPPING_DELIVER,
            method      = RequestMethod.POST,
            headers     = "Accept=application/xml, application/json",
            produces    = {"application/json", "application/xml"},
            consumes    = {"application/json", "application/xml"})
	public Response update(@RequestBody OrderDetailDTO orderDetailDTO) throws OrderServiceException{
		Response response = new Response();

		Order order = orderService.findOne(orderDetailDTO.getOrderId());
		order.setExpressId(orderDetailDTO.getExpressId());
		order.setExpressOrder(orderDetailDTO.getExpressOrder());
		order.setState(OrderState.DELIVERED);

		ManageUser updateUser = manageUserService.findOne(Integer.parseInt(orderDetailDTO.getUpdateBy()));

		order.setUpdateTime(new Date());
		String logStr = "发货操作：" + orderDetailDTO.getLog();

		OrderActionLog orderActionLog = new OrderActionLog();
		orderActionLog.setOrderActionLogType(OrderActionLogType.DELIVER);
		orderActionLog.setNote(logStr);
		orderActionLog.setOrder(order);
		orderActionLog.setActionById(updateUser.getId().toString());
		orderActionLog.setActionByname(updateUser.getName());
		orderActionLog.setActionBytype(updateUser.getUserType().getName());
		orderActionLog.setActionByfrom("manage");
		orderActionLog.setActionTime(order.getUpdateTime());
		try {
			Order newOrder = orderService.updateOrder(order, orderActionLog);
				OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
				BeanUtils.copyProperties(newOrder, innerOrderDetailDTO);
				innerOrderDetailDTO.setChannelId(newOrder.getChannel().getId());
				innerOrderDetailDTO.setCreateBy(newOrder.getCreateBy().getAccount());
				innerOrderDetailDTO.setStateValue(newOrder.getState().getName());
				innerOrderDetailDTO.setRecipients(newOrder.getOrderDeliverInfo().getRecipients());

				response.setCode(Response.Status.OK);
				response.setData(orderDetailDTO);
				return response;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				response.setMessage("系统错误");
				response.setSuccess(false);
				return response;
			}
	}
    
    /**
     * 取消订单
     * @param orderDetailDTO
     * @return
     * @throws OrderServiceException
     */
    @RequestMapping(value       = APPURIConstant.Order.REQUEST_MAPPING_CANCEL,
            method      = RequestMethod.POST,
            headers     = "Accept=application/xml, application/json",
            produces    = {"application/json", "application/xml"},
            consumes    = {"application/json", "application/xml"})
	public Response cancel(@RequestBody OrderDetailDTO orderDetailDTO) throws OrderServiceException{
		Response response = new Response();

		Order order = orderService.findOne(orderDetailDTO.getOrderId());
		order.setState(OrderState.CANCEL);

		ManageUser updateUser = manageUserService.findOne(Integer.parseInt(orderDetailDTO.getUpdateBy()));

		order.setUpdateTime(new Date());
		String logStr = "取消操作：" + orderDetailDTO.getLog();

		OrderActionLog orderActionLog = new OrderActionLog();
		orderActionLog.setOrderActionLogType(OrderActionLogType.CANCEL);
		orderActionLog.setNote(logStr);
		orderActionLog.setOrder(order);
		orderActionLog.setActionById(updateUser.getId().toString());
		orderActionLog.setActionByname(updateUser.getName());
		orderActionLog.setActionBytype(updateUser.getUserType().getName());
		orderActionLog.setActionByfrom("manage");
		orderActionLog.setActionTime(order.getUpdateTime());
		try {
			Order newOrder = orderService.updateOrder(order, orderActionLog);
				OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
				BeanUtils.copyProperties(newOrder, innerOrderDetailDTO);
				innerOrderDetailDTO.setChannelId(newOrder.getChannel().getId());
				innerOrderDetailDTO.setCreateBy(newOrder.getCreateBy().getAccount());
				innerOrderDetailDTO.setStateValue(newOrder.getState().getName());
				innerOrderDetailDTO.setRecipients(newOrder.getOrderDeliverInfo().getRecipients());

				response.setCode(Response.Status.OK);
				response.setData(orderDetailDTO);
				return response;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				response.setMessage("系统错误");
				response.setSuccess(false);
				return response;
			}
	}

	/**
	 * 编辑订单
	 * @param orderDetailDTO
	 * @return
	 * @throws OrderServiceException
	 */
	@RequestMapping(value       = APPURIConstant.Order.REQUEST_MAPPING_EDIT,
			method      = RequestMethod.POST,
			headers     = "Accept=application/xml, application/json",
			produces    = {"application/json", "application/xml"},
			consumes    = {"application/json", "application/xml"})
	public Response edit(@RequestBody OrderDetailDTO orderDetailDTO) throws OrderServiceException{
		Response response = new Response();

		Order order = orderService.findOne(orderDetailDTO.getOrderId());

		OrderDeliverInfo orderDeliverInfo = order.getOrderDeliverInfo();
		orderDeliverInfo.setRecipients(orderDetailDTO.getRecipients());

		List<OrderItem> orderItems = orderItemService.findByOrderId(order.getOrderId());
		OrderItem orderItem  = orderItems.get(0);//目前一个订单下只有一个订单项

		Iterator<OrderItemDetailDTO> it = orderDetailDTO.getItems().iterator();
		while (it.hasNext()) {
			//目前一个订单下只有一个订单项
			OrderItemDetailDTO newItem = it.next();
			orderItem.setAmount(newItem.getAmount());
			orderItem.setProductPrice(newItem.getProductPrice());
		}

		ManageUser updateUser = manageUserService.findOne(Integer.parseInt(orderDetailDTO.getUpdateBy()));

		order.setUpdateTime(new Date());
		String logStr = "编辑操作：" + orderDetailDTO.getLog();

		OrderActionLog orderActionLog = new OrderActionLog();
		orderActionLog.setOrderActionLogType(OrderActionLogType.EDIT);
		orderActionLog.setNote(logStr);
		orderActionLog.setOrder(order);
		orderActionLog.setActionById(updateUser.getId().toString());
		orderActionLog.setActionByname(updateUser.getName());
		orderActionLog.setActionBytype(updateUser.getUserType().getName());
		orderActionLog.setActionByfrom("manage");
		orderActionLog.setActionTime(order.getUpdateTime());
		try {
			Order newOrder = orderService.editOrder(order, orderDeliverInfo, orderItem, orderActionLog);
			OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
			BeanUtils.copyProperties(newOrder, innerOrderDetailDTO);
			innerOrderDetailDTO.setChannelId(newOrder.getChannel().getId());
			innerOrderDetailDTO.setCreateBy(newOrder.getCreateBy().getAccount());
			innerOrderDetailDTO.setStateValue(newOrder.getState().getName());
			innerOrderDetailDTO.setRecipients(newOrder.getOrderDeliverInfo().getRecipients());

			response.setCode(Response.Status.OK);
			response.setData(orderDetailDTO);
			return response;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
	}
    
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_PAY_BACK, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json"})
	public Response payComplete(@RequestBody AliPayNotifyDTO notify) {
		Order order = orderService.payComplete(notify);
		Response response = new Response();
		if (order != null) {
			OrderDetailDTO orderDetailDTO = (OrderDetailDTO) MapperUtils.map(order, OrderDetailDTO.class);
			response.setCode(200);
			response.setData(orderDetailDTO);
		}
		return response;
	}
    
	/**
	 * 根据条件查询 center
	 * @param map
	 * @return
	 */
    @RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
    public Response findBySearchCondition(@RequestBody Map map){
		List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
		Response response = new Response();
		//String orderId = orderDetailDTO.getOrderId();
		int offset = (int)map.get("offset");
		int limit = (int)map.get("limit");
		map.remove("offset");
		map.remove("limit");
		if(map.get("EQ_paymentWay") !=null ){
			map.put("EQ_paymentWay", PaymentWay.valueOf((String)map.get("EQ_paymentWay")));
		}
		if(map.get("EQ_state") !=null ){
			map.put("EQ_state", OrderState.valueOf((String) map.get("EQ_state")));
		}
		try{
			PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
			Page<Order> result = orderService.findAllBycondition(map, pageRequest);
			PageDTO<OrderDetailDTO> pageDTO = new PageDTO<OrderDetailDTO>();
			if(result != null && result.getContent() != null && result.getContent().size()>0){
				List<Order> list = result.getContent();
				for (Order order:list){
					OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
					BeanUtils.copyProperties(order, innerOrderDetailDTO);
					innerOrderDetailDTO.setChannelId(order.getChannel().getId());
					innerOrderDetailDTO.setCreateBy(order.getCreateBy().getAccount());
					innerOrderDetailDTO.setStateValue(order.getState().getName());
					innerOrderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());
					innerOrderDetailDTO.setPaymentWay(order.getPaymentWay().getName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String newTime =  sdf.format(order.getCreateTime());
					innerOrderDetailDTO.setCreateTime(newTime);

					List<OrderItem> orderItems = orderItemService.findByOrderId(order.getOrderId());

					Iterator<OrderItem> it = orderItems.iterator();
					Set<OrderItemDetailDTO> items = new HashSet<OrderItemDetailDTO>();
					while (it.hasNext()) {
						OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
					  OrderItem orderItem = it.next();
					  BeanUtils.copyProperties(orderItem, orderItemDetailDTO);
					  items.add(orderItemDetailDTO);
					}
					innerOrderDetailDTO.setItems(items);
					listOrder.add(innerOrderDetailDTO);
				}
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(result.getTotalPages());
				pageDTO.setITotalDisplayRecords(result.getTotalElements());
				pageDTO.setITotalRecords(result.getTotalElements());
				response.setData(pageDTO);
			}else {
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(0);
				pageDTO.setITotalDisplayRecords(0L);
				pageDTO.setITotalRecords(0L);
				response.setData(pageDTO);
			}
				return response;
		}catch (OrderServiceException e){
				log.error(e.getMessage());
				response.setSuccess(false);
				response.setMessage(e.getMessage());
				return response;
		}
	}

	/**
	 * 根据条件查询 manage
	 * @param  map
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL_BY_SEARCHCONDITION, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public Response findAllBySearchCondition(@RequestBody Map map){
		List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
		Response response = new Response();
		int offset = (int)map.get("offset");
		int limit = (int)map.get("limit");
		map.remove("offset");
		map.remove("limit");
		if(map.get("EQ_paymentWay") !=null ){
			map.put("EQ_paymentWay", PaymentWay.valueOf((String)map.get("EQ_paymentWay")));
		}
		if(map.get("EQ_state") !=null ){
			map.put("EQ_state", OrderState.valueOf((String) map.get("EQ_state")));
		}
		try{
			PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
			Page<Order> result = orderService.findAllBycondition(map, pageRequest);
			PageDTO<OrderDetailDTO> pageDTO = new PageDTO<OrderDetailDTO>();
			if(result != null && result.getContent() != null && result.getContent().size()>0){
				List<Order> list = result.getContent();
				for (Order order:list){
					OrderDetailDTO innerOrderDetailDTO = new OrderDetailDTO();
					BeanUtils.copyProperties(order, innerOrderDetailDTO);
					innerOrderDetailDTO.setChannelId(order.getChannel().getId());
					innerOrderDetailDTO.setChannelName(order.getChannel().getName());
					innerOrderDetailDTO.setCreateBy(order.getCreateBy().getAccount());
					innerOrderDetailDTO.setStateValue(order.getState().getName());
					innerOrderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());
					innerOrderDetailDTO.setPaymentWay(order.getPaymentWay().getName());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String newTime =  sdf.format(order.getCreateTime());
					innerOrderDetailDTO.setCreateTime(newTime);
					listOrder.add(innerOrderDetailDTO);

				}
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(result.getTotalPages());
				pageDTO.setITotalDisplayRecords(result.getTotalElements());
				pageDTO.setITotalRecords(result.getTotalElements());
				response.setData(pageDTO);
			}else {
				pageDTO.setContent(listOrder);
				pageDTO.setTotalPages(0);
				pageDTO.setITotalDisplayRecords(0L);
				pageDTO.setITotalRecords(0L);
				response.setData(pageDTO);
			}
			return response;
		}catch (OrderServiceException e){
			log.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return response;
		}
	}

	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_ACCOUNT_AND_APP_ID, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
    public Response list(@RequestBody OrderRequestDTO requestDTO) {
		Page<Order> orders = orderService.listByAccount(requestDTO);
		PageDTO<OrderDetailDTO> pageDTO = PageUtils.mapper(orders, OrderDetailDTO.class);
		List<OrderDetailDTO> orderDetailDTOs = pageDTO.getContent();
		if (orderDetailDTOs != null) {
			for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {

			}
		}
		Response<PageDTO<OrderDetailDTO>> response = new Response<>();
		response.setData(pageDTO);
		return response;
	}

	/**
	 * 订单详情
	 * @param orderDetailDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_GET_ORDER_DETAIL, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
	public Response<OrderDetailDTO> getOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
		Response<OrderDetailDTO> response = new Response<OrderDetailDTO>();
		Order order = orderService.findOne(orderDetailDTO.getOrderId());
		try {
			orderDetailDTO = (OrderDetailDTO) MapperUtils.map(order, OrderDetailDTO.class);
			orderDetailDTO.setStateValue(order.getState().getName());
			orderDetailDTO.setPaymentWay(order.getPaymentWay().getName());
			orderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());
			orderDetailDTO.setDeliver( (OrderDeliverDTO) MapperUtils.map(order.getOrderDeliverInfo(), OrderDeliverDTO.class));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String newTime = sdf.format(order.getCreateTime());
			orderDetailDTO.setCreateTime(newTime);
			List<OrderItem> orderItems = orderItemService.findByOrderId(order.getOrderId());
			Iterator<OrderItem> it = orderItems.iterator();
			Set<OrderItemDetailDTO> items = new HashSet<OrderItemDetailDTO>();
			while (it.hasNext()) {
				OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
				OrderItem orderItem = it.next();
				BeanUtils.copyProperties(orderItem, orderItemDetailDTO);
				items.add(orderItemDetailDTO);
			}
			orderDetailDTO.setItems(items);

		}catch (OrderServiceException e){
			log.error("error: ",e.getStackTrace());
			response.setSuccess(false);
		}
		response.setData(orderDetailDTO);
		return response;
	}

	/**
	 * 审核订单通过
	 * @param map
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_CONFIRM_ORDER, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
	public Response confirmOrder(@RequestBody Map map){
		Response response = new Response();
		Order order = orderService.findOne((String) map.get("id"));
		order.setState(OrderState.WAIT_DELIVER);
		ManageUser updateUser = manageUserService.findByUsername((String) map.get("account"));
		order.setUpdateTime(new Date());
		String logStr = "审核通过。";
		OrderActionLog orderActionLog = new OrderActionLog();
		orderActionLog.setOrderActionLogType(OrderActionLogType.CONFIRM);
		orderActionLog.setNote(logStr);
		orderActionLog.setOrder(order);
		orderActionLog.setActionById(updateUser.getId().toString());
		orderActionLog.setActionByname(updateUser.getName());
		orderActionLog.setActionBytype(updateUser.getUserType().getName());
		orderActionLog.setActionByfrom("manage");
		orderActionLog.setActionTime(order.getUpdateTime());
		try{
			orderService.updateOrder(order, orderActionLog);
		}catch (OrderServiceException e){
			log.error("error",e.getStackTrace());
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * 获取退款列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_MONEY, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
	public Response<PageDTO<OrderRefundDTO>> getRefundMoney(@RequestBody Map map){
		Response<PageDTO<OrderRefundDTO>> response = new Response<PageDTO<OrderRefundDTO>>();
		int limit = (int)map.get("limit");
		int offset = (int)map.get("offset");
		map.remove("limit");
		map.remove("offset");
		List<OrderRefundDTO> orderRefundDTOList = new ArrayList<OrderRefundDTO>();
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "refundId");
		try {
			Page<OrderRefund> page = orderService.findRefundPage(map,pageRequest);
			PageDTO<OrderRefundDTO> pageDTO = new PageDTO<OrderRefundDTO>();
			if(page != null && page.getContent() != null && page.getContent().size() >0){
				List<OrderRefund> orderRefundList = page.getContent();
				for(OrderRefund orderRefund:orderRefundList){
					OrderRefundDTO orderRefundDTO = new OrderRefundDTO();
					BeanUtils.copyProperties(orderRefund, orderRefundDTO);
					orderRefundDTO.setBuyerDateStr(this.getDateStr(orderRefund.getBuyerDate()));
					orderRefundDTO.setCenterDateStr(this.getDateStr(orderRefund.getCenterDate()));
					orderRefundDTO.setManageDateStr(this.getDateStr(orderRefund.getManageDate()));
					orderRefundDTO.setCenterStateStr(this.getCenterStateStr(orderRefundDTO.getCenterState()));
					orderRefundDTO.setRefundStateStr(this.getRefundStateStr(orderRefundDTO.getRefundState()));
					orderRefundDTOList.add(orderRefundDTO);
				}
			}
			pageDTO.setContent(orderRefundDTOList);
			pageDTO.setTotalPages(page.getTotalPages());
			pageDTO.setITotalDisplayRecords(page.getTotalElements());
			pageDTO.setITotalRecords(page.getTotalElements());
			response.setData(pageDTO);
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * 获取退款详情
	 * @param refundId
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_GET_REFUND_DETAIL_BY_REFUND_ID, method = RequestMethod.POST,
			headers     = "Accept=application/xml, application/json",
			produces    = {"application/json"})
	public Response<OrderRefundDTO> getRefundDetail(@NotEmpty @PathVariable("refundId") String refundId){
		OrderRefund orderRefund = orderService.getRefundDetail(refundId);
		OrderRefundDTO orderRefundDTO = new OrderRefundDTO();
		BeanUtils.copyProperties(orderRefund, orderRefundDTO);
		orderRefundDTO.setBuyerDateStr(this.getDateStr(orderRefund.getBuyerDate()));
		orderRefundDTO.setCenterDateStr(this.getDateStr(orderRefund.getCenterDate()));
		orderRefundDTO.setManageDateStr(this.getDateStr(orderRefund.getManageDate()));
		orderRefundDTO.setCenterStateStr(this.getCenterStateStr(orderRefundDTO.getCenterState()));
		orderRefundDTO.setRefundStateStr(this.getRefundStateStr(orderRefundDTO.getRefundState()));
		Response<OrderRefundDTO> response = new Response<OrderRefundDTO>();
		response.setData(orderRefundDTO);
		return response;
	}

	/**
	 * 确认退款
	 * @param orderRefundDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_GO_TO_CONFIRM, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
	public Response go2Confirm(@RequestBody OrderRefundDTO orderRefundDTO){
		OrderRefund orderRefund = orderService.getRefundDetail(orderRefundDTO.getRefundId());
		orderRefund.setAdminMessage(orderRefundDTO.getAdminMessage());
		orderRefund.setRefundState(CodeConstant.Refund.MANAGE_CONFIRM);
		orderRefund.setManageDate(new Date());
		orderRefund.setManageId(orderRefundDTO.getManageId());
		orderService.saveRefund(orderRefund);
		return new Response();
	}

	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPOMG_GET_REFUND_BY_CONDITION, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
	public Response getRefundMoneyByCondition(@RequestBody Map map){
		Response<PageDTO<OrderRefundDTO>> response = new Response<PageDTO<OrderRefundDTO>>();
		int limit = (int)map.get("limit");
		int offset = (int)map.get("offset");
		map.remove("limit");
		map.remove("offset");
		List<OrderRefundDTO> orderRefundDTOList = new ArrayList<OrderRefundDTO>();
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "refundId");
		Page<OrderRefund> page = null;
		try {
			if (map.size()==0){
				page = orderService.findRefundPage(null,pageRequest);
			}else {
				page = orderService.findRefundPage(map,pageRequest);
			}
			PageDTO<OrderRefundDTO> pageDTO = new PageDTO<OrderRefundDTO>();
			if(page != null && page.getContent() != null && page.getContent().size() >0){
				List<OrderRefund> orderRefundList = page.getContent();
				for(OrderRefund orderRefund:orderRefundList){
					OrderRefundDTO orderRefundDTO = new OrderRefundDTO();
					BeanUtils.copyProperties(orderRefund, orderRefundDTO);
					orderRefundDTO.setBuyerDateStr(this.getDateStr(orderRefund.getBuyerDate()));
					orderRefundDTO.setCenterDateStr(this.getDateStr(orderRefund.getCenterDate()));
					orderRefundDTO.setManageDateStr(this.getDateStr(orderRefund.getManageDate()));
					orderRefundDTO.setCenterStateStr(this.getCenterStateStr(orderRefundDTO.getCenterState()));
					orderRefundDTO.setRefundStateStr(this.getRefundStateStr(orderRefundDTO.getRefundState()));
					orderRefundDTOList.add(orderRefundDTO);
				}
			}
			pageDTO.setContent(orderRefundDTOList);
			pageDTO.setTotalPages(page.getTotalPages());
			pageDTO.setITotalDisplayRecords(page.getTotalElements());
			pageDTO.setITotalRecords(page.getTotalElements());
			response.setData(pageDTO);
		}catch (Exception e){
			log.error("error",e.getStackTrace());
			response.setSuccess(false);
		}
		return response;
	}
	public String getDateStr(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date == null) return CodeConstant.Refund.BLANK;
		return dateFormat.format(date);
	}

	public String getCenterStateStr(Integer state){
		if (state == CodeConstant.Refund.CENTER_CONFIRM) return CodeConstant.Refund.CENTER_CONFIRM_STRING;
		if (state == CodeConstant.Refund.CENTER_NOT_CONFIRM) return CodeConstant.Refund.CENTER_NOT_CONFIRM_STRING;
		if (state == CodeConstant.Refund.WAIT_CENTER_CONFIRM) return CodeConstant.Refund.WAIT_CENTER_CONFIRM_STRING;
		return CodeConstant.Refund.BLANK;
	}

	public String getRefundStateStr(Integer state){
		if (state == CodeConstant.Refund.MANAGE_CONFIRM) return CodeConstant.Refund.MANAGE_CONFIRM_STRING;
		if (state == CodeConstant.Refund.WAIT_MANAGE_CONFIRM) return CodeConstant.Refund.WAIT_MANAGE_CONFIRM_STRING;
		if (state == CodeConstant.Refund.MANAGE_NOT_CONFIRM) return CodeConstant.Refund.MANAGE_NOT_CONFIRM_STRING;
		return CodeConstant.Refund.BLANK;
	}
}