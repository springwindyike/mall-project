package com.ishare.mall.core.service.information;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Set;

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
     * 分页查询
     * @param
     * @param pageRequest
     * @return
     */
    Page<Channel> getChannelpage(PageRequest pageRequest);

    void save(Channel channel);

    /**
     *
     * @param pageRequest
     * @param name
     * @param phone
     * @param industry
     * @return
     */
    Page<Channel> getChannelpage(PageRequest pageRequest,String name,String phone,String industry);
    /**
     * 查询本周的新增渠道
     */
    Page<Channel> findAllThisWeek(PageRequest pageRequest);
    /**
     * 查询本周新增渠道的数量
     */
    Long findThisWeekCount();
    /**
     * 查询所有渠道数
     */
    long findCount();
}
