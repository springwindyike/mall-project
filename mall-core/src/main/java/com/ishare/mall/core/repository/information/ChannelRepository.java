package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
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
    @Query("SELECT c FROM Channel c WHERE c.name=?1")
    List<Channel> findByName(String name);

    @Query("SELECT c FROM Channel c WHERE 1 = 1")
    Page<Channel> getChannelpage(Pageable pageable);

    @Query("SELECT c FROM Channel c WHERE c.name like ?1 or c.phone like ?2 or c.industry like ?3")
    Page<Channel> getChannelpage(Pageable pageable,String name, String phone, String industry);
    @Query("SELECT  c from Channel c where c.createTime >=?1")
    Page<Channel> findThisWeek(Date date,Pageable pageable);
    @Query("SELECT m FROM Channel m")
    Page<Channel> findAll(Pageable pageable);
    @Query("SELECT  count(*) from Channel")
    Long findCount();
    @Query("SELECT Count(*) from Channel m where m.createTime >= ?1")
    Long findThisWeekCount(Date date);
}