package com.ishare.mall.center.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by YinLin on 2015/9/10.
 * Description :
 * Version 1.0
 */
public class WebUtils {
    public static void main(String[] args) {
        String pwd = new Md5Hash("111111", "183283705145a54415cbe474acebcd2ea20ddbaf36f", 2).toHex();
        System.out.println(pwd);
    }
}
