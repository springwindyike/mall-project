package com.ishare.mall.core.service.information.impl;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.order.Order;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.service.information.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {
    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);
    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Channel findOne(Integer id) {
        return channelRepository.findOne(id);
    }

    @Override
    public Channel findByAppId(String appId) {
        List<Channel> channels = channelRepository.findByAppId(appId);
        if (channels == null || channels.size() == 0) return null;
        return channels.get(0);
    }

    @Override
    public Channel findByAppSecret(String appSecret) {
        List<Channel> channels = channelRepository.findByAppSecret(appSecret);
        if (channels == null || channels.size() == 0) return null;
        return channels.get(0);
    }

    public static Logger getLog() {
        return log;
    }

	@Override
	public Set<Order> findAllOrderByChannelId(Integer id) {
		 Channel channel = channelRepository.findOne(id);
		 if(channel != null) {
			 return channel.getOrders();
		 }
		return null;
	}

    @Override
    public Page<Channel> getChannelpage(PageRequest pageRequest) {
        return channelRepository.getChannelpage(pageRequest);
    }

    @Override
    public void save(Channel channel) {
        channelRepository.save(channel);
    }

    @Override
    public Page<Channel> getChannelpage(PageRequest pageRequest, String name, String phone, String industry) {
        return channelRepository.getChannelpage(pageRequest, name, phone, industry);
    }

    @Override
	public Channel findByName(String name) {
    List<Channel> channels = channelRepository.findByName(name);
    if (channels == null || channels.size() == 0) return null;
    return channels.get(0);
	}
    @Override
    public Page<Channel> findAllThisWeek(PageRequest pageRequest){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        Page<Channel> page = channelRepository.findThisWeek(cal.getTime(), pageRequest);
        return page;

    }
    /**
     *查询本周新增渠道的数量
     */
    public Long findThisWeekCount(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        return  channelRepository.findThisWeekCount(cal.getTime());
    }
    /**
     * 查询所有渠道数量
     */
    public long findCount(){
        return channelRepository.findCount();
    }
}
