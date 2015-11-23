package com.ishare.mall.test.repository;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.common.base.dto.order.OrderItemDetailDTO;
import com.ishare.mall.common.base.enumeration.DeliverWay;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.model.order.OrderItem;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinLin on 2015/9/19.
 */
public class OrderServiceTests extends RepositoryTestTemplate {
    @Autowired
    private OrderService orderService;
    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Override
    public void testCreate() {

    }

    @Override
    public void testRetrieve() throws OrderServiceException {
        ExchangeDTO exchangeDTO = new ExchangeDTO();
        this.initExchangeDTO(exchangeDTO);
        List<OrderDetailDTO> orderDetailDTO = orderService.create(exchangeDTO);
        log.debug(orderDetailDTO.toString());
    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testDelete() {

    }

    private void initExchangeDTO(ExchangeDTO exchangeDTO) {
        exchangeDTO.setAccount("qazwc1");
        exchangeDTO.setTel("13800138000");
        exchangeDTO.setRecipients("殷林");
        //exchangeDTO.setProductId(1);
        List<OrderItemDetailDTO> list = new ArrayList<OrderItemDetailDTO>();
        for (int i= 0;i<5;i++){
            OrderItemDetailDTO orderItemDetailDTO = new OrderItemDetailDTO();
            if (i<3){
                orderItemDetailDTO.setChannelId(11);
            }else {
                orderItemDetailDTO.setChannelId(8);
            }

            orderItemDetailDTO.setProductId(i + 1);
            orderItemDetailDTO.setStyleId(1);
            orderItemDetailDTO.setAmount(4);
           // orderItemDetailDTO.setProductPrice();
            list.add(orderItemDetailDTO);
        }
        exchangeDTO.setOrderItemDetailDTOList(list);
        exchangeDTO.setAmount(1);
        exchangeDTO.setCountry("中国");
        exchangeDTO.setProvince("四川");
        exchangeDTO.setCity("成都");
        exchangeDTO.setDistrict("高新区");
        exchangeDTO.setDetail("中和镇城南春天");
        exchangeDTO.setClientId("c7f5b9e5026c4c82828daa52843f7c9d");
        exchangeDTO.setStyleId(1l);
        exchangeDTO.setDeliverWay(DeliverWay.EMS);
    }
}
