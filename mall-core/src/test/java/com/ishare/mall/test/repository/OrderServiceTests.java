package com.ishare.mall.test.repository;

import com.ishare.mall.common.base.dto.order.ExchangeDTO;
import com.ishare.mall.common.base.dto.order.OrderDetailDTO;
import com.ishare.mall.core.service.order.OrderService;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void testRetrieve() {
        ExchangeDTO exchangeDTO = new ExchangeDTO();
        this.initExchangeDTO(exchangeDTO);
        OrderDetailDTO orderDetailDTO = orderService.create(exchangeDTO);
        log.debug(orderDetailDTO.toString());
    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testDelete() {

    }

    private void initExchangeDTO(ExchangeDTO exchangeDTO) {
        exchangeDTO.setAccount("13800138000");
        exchangeDTO.setTel("13800138000");
        exchangeDTO.setRecipients("殷林");
        exchangeDTO.setProductId(1);
        exchangeDTO.setAmount(1);
        exchangeDTO.setCountry("中国");
        exchangeDTO.setProvince("四川");
        exchangeDTO.setCity("成都");
        exchangeDTO.setDistrict("高新区");
        exchangeDTO.setDetail("中和镇城南春天");
        exchangeDTO.setClientId("123");
        exchangeDTO.setStyleId(1l);
    }
}
