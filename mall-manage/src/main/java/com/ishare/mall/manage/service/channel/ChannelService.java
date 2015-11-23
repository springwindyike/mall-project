package com.ishare.mall.manage.service.channel;

import com.ishare.mall.common.base.dto.channel.ChannelDTO;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public interface ChannelService {
    /**
     * 获取当前所有会员
     */
    List<ChannelDTO> findAll();

    Long findCount();
    /**
     * 获取本周新增会员数量
     */
    Long findThisWeekCount();
}
