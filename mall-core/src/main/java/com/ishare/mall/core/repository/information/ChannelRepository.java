package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.information.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YinLin on 2015/8/12.
 * Description :
 * Version 1.0
 */
public interface ChannelRepository extends JpaRepository<Channel, Integer>, JpaSpecificationExecutor {
    @Query("SELECT c FROM Channel WHERE c.appId = ?1")
    List<Channel> findByAppId(String appId);
    @Query("SELECT c FROM Channel WHERE c.appSecret = ?1")
    List<Channel> finaByAppSecret(String appSecret);
}
