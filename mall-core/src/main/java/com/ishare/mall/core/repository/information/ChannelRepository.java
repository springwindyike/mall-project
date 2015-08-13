package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.information.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

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
}
