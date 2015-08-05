package com.tianyu.jty.system.dao;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.system.entity.User;
import org.springframework.stereotype.Repository;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class UserDao extends HibernateDao<User, Integer>{

}
