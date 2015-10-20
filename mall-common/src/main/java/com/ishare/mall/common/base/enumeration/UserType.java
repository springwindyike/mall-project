package com.ishare.mall.common.base.enumeration;

/**
 * Created by Zhangzhaoxin on 2015/10/19.
 * Description: 成员类型
 * Version 1.0
 */
public enum UserType {
    SELF {
        @Override
        public String getName() {
            return "系统管理员";
        }
    },
    ADMIN {
        @Override
        public String getName() {
            return "管理员";
        }
    },
    CLERK {
        @Override
        public String getName() {
            return "办事员";
        }
    }
    ,MEMBER {
        @Override
        public String getName() {
            return "普通成员";
        }
    };
    public abstract String getName();
}
