package com.ishare.mall.common.base.constant.uri;

/**
 * Created by YinLin on 2015/9/2.
 * Description :
 * Version 1.0
 */
public interface APPURIConstant {
    interface Member {
        String REQUEST_MAPPING   = "/member";
        String REQUEST_MAPPING_LOGIN    =  "/login";
        String REQUEST_MAPPING_FIND_BY_ROL_ID = "/findMemberByRoleId";
        String REQUEST_MAPPING_FIND_BY_CHANNEL_ID = "/findByChannelId";
        String REQUEST_MAPPING_FIND_BY_ACCOUNT = "/findByAccount";
    }

    //权限
    interface Permission {
        String REQUEST_MAPPING = "/permission";
    }
    //角色
    interface Role {
        String REQUEST_MAPPING = "/role";
    }
}
