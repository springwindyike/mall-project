package com.ishare.mall.core.service.information;

import java.util.Set;

import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface ChannelService {
    Channel findOne(Integer id);
    Channel findByAppId(String appId);
    Channel findByAppSecret(String appSecret);
    Channel findByName(String name);
    Set<Order> findAllOrderByChannelId(Integer id);

    /**
     * 根据id分页查询
     * @param id
     * @param pageRequest
     * @return
     */
    Page<Channel> getChannelpage(Integer id,PageRequest pageRequest);
}
