package com.ishare.mall.center.service.channel;

import com.ishare.mall.common.base.dto.channel.ChannelDTO;

/**
 * Created by Administrator on 2015/11/12.
 */
public interface ChannelService {
    ChannelDTO findByChannelId(Integer id);
}
