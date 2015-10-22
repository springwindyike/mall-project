package com.ishare.mall.common.base.enumeration;

/**
 * Created by ZhangZhaoxin on 2015/10/21.
 * Description: 成员类型
 * Version 1.0
 */
public enum OrderActionLogType {
    CREATE {
        @Override
        public String getName() {
            return "创建";
        }
    },
    CANCEL {
        @Override
        public String getName() {
            return "取消";
        }
    },
    EDIT {
        @Override
        public String getName() {
            return "编辑";
        }
    }
    ,DELIVER {
        @Override
        public String getName() {
            return "发货";
        }
    };
    public abstract String getName();
}
