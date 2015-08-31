package com.ishare.test.shiro;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by YinLin on 2015/8/31.
 * Description :
 * Version 1.0
 */
public class RoleTest extends BaseShiroTest {
    @Test
    public void testHasRole() {
        login("classpath:shiro/user-role.ini", "yin", "123");
        Assert.assertTrue(subject().hasRole("role1"));
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertTrue(result[0]);
        Assert.assertTrue(result[1]);
        Assert.assertTrue(result[2]);
    }
}
