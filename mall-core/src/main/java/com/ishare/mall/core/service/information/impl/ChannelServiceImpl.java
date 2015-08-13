package com.ishare.mall.core.service.information.impl;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.service.information.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
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
}
