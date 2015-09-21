package com.ishare.mall.core.status;

/**
 * Created by YinLin on 2015/8/3.
 * Description: 成员类型
 * Version 1.0
 */
public enum MemberType {
    ADMIN {
        @Override
        public String getName() {
            return "管理员";
        }
    },MEMBER {
        @Override
        public String getName() {
            return "普通成员";
        }
    };
    public abstract String getName();
}
