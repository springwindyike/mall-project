package com.ishare.mall.test.repository;

import com.ishare.mall.core.model.order.GeneratedOrderId;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.model.order.OrderContactInfo;
import com.ishare.mall.core.model.order.OrderDeliverInfo;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.repository.order.GeneratedOrderIdRepository;
import com.ishare.mall.core.repository.order.OrderRepository;
import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.status.OrderState;
import com.ishare.mall.core.status.PaymentWay;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhangzhaoxin on 2015/8/25.
 * Description :
 * Version 1.0
 */
public class OrderRepositoryTests extends RepositoryTestTemplate {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private GeneratedOrderIdRepository orderIdRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void testName() throws Exception {
    }

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Override
    public void testCreate() {
    		Order order = buildOrder();
				Date current=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				String date=sdf.format(current);
				GeneratedOrderId generatedOrderId = orderIdRepository.findOne(date);
				if(null == generatedOrderId){
					generatedOrderId = new GeneratedOrderId();
					generatedOrderId.setId(date);
					generatedOrderId.setOrderId(1);
					orderIdRepository.save(generatedOrderId);
					String orderIdStr = String.format("%06d", generatedOrderId.getOrderId());
					order.setOrderId(date + orderIdStr);
					repository.save(order);
				}
				generatedOrderId.setOrderId(generatedOrderId.getOrderId()+1);
				orderIdRepository.save(generatedOrderId);
				String orderIdStr = String.format("%06d",generatedOrderId.getOrderId());
				order.setOrderId(date + orderIdStr);
				Order orderNew = repository.save(order);
				System.out.println(orderNew);
    }

		public Order buildOrder() {
			Order order = new Order();
			order.setChannel(channelRepository.findOne(1));
			order.setCreateBy("18298362843");
			order.setCreateTime(new Date());
			order.setDeliverFee((float) 15);
			order.setExpressId("1111111111");
			order.setExpressOrder("11111111111");
			order.setLockMember(null);
			order.setNote("note..note..note..");
			order.setOrderContactInfo(null);
			order.setOrderDeliverInfo(new OrderDeliverInfo());
			order.setPayableFee((float) 150);
			order.setPaymentState(false);
			order.setPaymentWay(PaymentWay.NET);
			order.setProductTotalPrice((float) 895);
			order.setState(OrderState.WAIT_PAYMENT);
			order.setTotalPrice((float) 999);
			order.setUpdateBy("12346712912");
			order.setUpdateTime(new Date());
			order.setItems(null);
			order.setOrderMessages(null);
			return order;
		}
		public OrderContactInfo buildOrderContactInfo() {
			OrderContactInfo orderContactInfo = new OrderContactInfo();
			orderContactInfo.setBuyerName("buyerName");
			orderContactInfo.setCity("City");
			orderContactInfo.setCountry("Country");
			orderContactInfo.setDetail("Detail");
			orderContactInfo.setDistrict("District");
			orderContactInfo.setEmail("Email");
			orderContactInfo.setGender(Gender.MAN);
			orderContactInfo.setMobile("18298368028");
			orderContactInfo.setPostalCode("610000");
			orderContactInfo.setProvince("province");	
			orderContactInfo.setTel("12345678");
			return orderContactInfo;
		}

    @Override
    public void testRetrieve() {

    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testDelete() {

    }
}
