package com.ishare.mall.common.base.constant;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ResourceConstant {
    public interface SEARCH {
        //查询前缀   查询例子 name search_LIKE_param
        public static final String SEARCH_START_WITHS = "search_";
        public static final String NAME = "name";
        public static final String SEARCH = "search";
    }

    public interface PAGE {
        public static final String OFFSET = "offset";
        public static final String LIMIT = "limit";
    }
}
