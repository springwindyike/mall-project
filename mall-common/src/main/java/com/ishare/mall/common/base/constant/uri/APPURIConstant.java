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
        String REQUEST_MAPPING_SAVE_MEMBER = "/saveMember";
        String REQUEST_MAPPING_ADD_MEMBER_PAGE = "/addMemberPage";
        String REQUEST_MAPPING_FIND_BY_CONDITION = "/findBySearchCondition";
        String REQUEST_MAPPING_FIND_VALID_BY_ACCOUNT = "/findValidByAccount";
        
        String REQUEST_MAPPING_REGISTER_MEMBER = "/registerMember";
        //通过账号获取用户信息用于登录
        String REQUEST_MAPPING_QUERY_BY_ACCOUNT = "/queryByAccount";
    }

    //权限
    interface Permission {
        String REQUEST_MAPPING = "/permission";
    }
    //角色
    interface Role {
        String REQUEST_MAPPING = "/role";
    }
    
    //商品分类
    interface ProductType{
    	String REQUEST_MAPPING = "/productType";
    	String REQUEST_MAPPING_FIND_FIRST_LEVEL = "/findFirstLevel";
    	
    }
    
    interface Product{

   	 String REQUEST_MAPPING   = "/product";
   	 String REQUEST_MAPPING_SAVE = "/save";
   
    }
}
