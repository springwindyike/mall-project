package com.ishare.mall.biz.restful.order;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderRequestDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.pay.AliPayNotifyDTO;
import com.ishare.mall.common.base.enumeration.OrderState;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.order.OrderDeliverInfo;
import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.service.information.OrderItemService;
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
        try {
			Page<Order> result = orderService.findByChannelId(channelId, pageRequest);
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
					innerOrderDetailDTO.setCreateBy(order.getCreateBy().getAccount());
					innerOrderDetailDTO.setStateValue(order.getState().getName());
					innerOrderDetailDTO.setRecipients(order.getOrderDeliverInfo().getRecipients());

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

		Member updateMember = memberService.findOne(Integer.parseInt(orderDetailDTO.getUpdateBy()));
		order.setUpdateBy(updateMember);
		order.setUpdateTime(new Date());
		String logStr = "发货操作：" + orderDetailDTO.getLog();
		try {
			Order newOrder = orderService.updateOrder(order, logStr);
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

		Member updateMember = memberService.findOne(Integer.parseInt(orderDetailDTO.getUpdateBy()));
		order.setUpdateBy(updateMember);
		order.setUpdateTime(new Date());
		String logStr = "取消操作：" + orderDetailDTO.getLog();
		try {
			Order newOrder = orderService.updateOrder(order, logStr);
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

		Member updateMember = memberService.findOne(Integer.parseInt(orderDetailDTO.getUpdateBy()));
		order.setUpdateBy(updateMember);
		order.setUpdateTime(new Date());
		String logStr = "编辑操作：" + orderDetailDTO.getLog();
		try {
			Order newOrder = orderService.editOrder(order, logStr, orderDeliverInfo, orderItem);
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
	 * @param orderDetailDTO
	 * @return
	 */
    @RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response findBySearchCondition(@RequestBody OrderDetailDTO orderDetailDTO){
		List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
		Response response = new Response();
		String orderId = orderDetailDTO.getOrderId();
		int offset = orderDetailDTO.getOffset();
		int limit = orderDetailDTO.getLimit();
		Integer channelId = orderDetailDTO.getChannelId();
		try{
			PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
			Page<Order> result = orderService.findBycondition(orderId, channelId,pageRequest);
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
	 * @param orderDetailDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL_BY_SEARCHCONDITION, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public Response findAllBySearchCondition(@RequestBody OrderDetailDTO orderDetailDTO){
		List<OrderDetailDTO> listOrder = new ArrayList<OrderDetailDTO>();
		Response response = new Response();
		String orderId = orderDetailDTO.getOrderId();
		int offset = orderDetailDTO.getOffset();
		int limit = orderDetailDTO.getLimit();
		try{
			PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "orderId");
			Page<Order> result = orderService.findAllBycondition(orderId, pageRequest);
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

	@RequestMapping(value = APPURIConstant.Order.REQUEST_MAPPING_FIND_BY_ACCOUNT_AND_APP_ID, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
    public Response list(@RequestBody OrderRequestDTO requestDTO) {
		Page<Order> orders = orderService.listByAccount(requestDTO);
		PageDTO pageDTO = PageUtils.mapper(orders, OrderDetailDTO.class);
		Response<PageDTO<OrderDetailDTO>> response = new Response<>();
		response.setData(pageDTO);
		return response;
	}
    
}