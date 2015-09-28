package com.ishare.mall.test.repository;

import com.ishare.mall.core.repository.sql.SqlDAO;
import com.ishare.mall.test.RepositoryTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YinLin on 2015/9/16.
 * Description :
 * Version 1.0
 */
public class SqlDaoTest extends RepositoryTestTemplate {
    @Autowired
    private SqlDAO sqlDAO;

    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Override
    public void testCreate() {

    }

    @Override
    public void testRetrieve() {
        sqlDAO.test();
    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testDelete() {

    }
}
