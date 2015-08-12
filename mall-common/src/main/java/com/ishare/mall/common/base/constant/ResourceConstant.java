package com.ishare.mall.common.base.constant;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface ResourceConstant {
    interface SEARCH {
        //查询前缀   查询例子 name search_LIKE_param
        String SEARCH_START_WITHS = "search_";
        String NAME = "name";
        String SEARCH = "search";
    }

    interface PAGE {
        String OFFSET = "offset";
        String LIMIT = "limit";
    }
    //oauth认证
    interface OAUTH {
        //token过期时间
        Long EXPIRE_IN = 3600L;
    }
}
