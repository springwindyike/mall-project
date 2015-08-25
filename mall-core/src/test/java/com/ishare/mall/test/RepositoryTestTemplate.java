package com.ishare.mall.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

/**
 * Created by YinLin on 2015/8/25.
 * Description :
 * Version 1.0
 */
public abstract class RepositoryTestTemplate extends TestTemplate {

    @Before
    public abstract void setUp();

    @After
    public abstract void tearDown();

    /**
     * 测试 新增 方法
     */
    @Rollback(value = false)
    @Test
    public abstract void testCreate();

    /**
     * 测试 查询 方法
     */
    @Test
    public abstract void testRetrieve();

    /**
     * 测试 修改 方法
     */
    @Rollback(value = true)
    @Test
    public abstract void testUpdate();

    /**
     * 测试 删除 方法
     */
    @Rollback(value = true)
    @Test
    public abstract void testDelete();

}
