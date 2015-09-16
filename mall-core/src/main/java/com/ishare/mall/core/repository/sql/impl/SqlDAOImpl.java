package com.ishare.mall.core.repository.sql.impl;

import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.core.repository.sql.SqlDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by YinLin on 2015/9/16.
 * Description :
 * Version 1.0
 */
@Repository
public class SqlDAOImpl implements SqlDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ChannelTokenResultDTO test() {
       return null;
    }
}
