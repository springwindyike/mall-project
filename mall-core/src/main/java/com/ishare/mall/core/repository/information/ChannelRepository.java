package com.ishare.mall.core.repository.information;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ishare.mall.core.model.information.Channel;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface ChannelRepository extends JpaRepository<Channel, Integer>, JpaSpecificationExecutor {
    //@Query("SELECT c FROM t_channel c WHERE c.appId=?1")
    List<Channel> findByAppId(String appId);
    //@Query("SELECT c FROM t_channel c WHERE c.appSecret=?1")
    List<Channel> findByAppSecret(String appSecret);
    @Query("SELECT c FROM Channel c WHERE c.name=?1")
    List<Channel> findByName(String name);
}