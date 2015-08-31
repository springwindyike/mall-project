package com.ishare.test.shiro;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YinLin on 2015/8/31.
 * Description :
 * Version 1.0
 */
public class PermissionTest extends BaseShiroTest {
    @Test
    public void testIsPermitted() {
        login("classpath:shiro/shiro-permission.ini", "yin", "123");
        Assert.assertTrue(subject().isPermitted("user:create"));
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test
    public void TestCheckPermission() {
        login("classpath:shiro/shiro-permission.ini", "yin", "123");
        subject().checkPermission("user:create");
        subject().checkPermissions("user:delete", "user:update");
        subject().checkPermissions("user:view");
    }
}
