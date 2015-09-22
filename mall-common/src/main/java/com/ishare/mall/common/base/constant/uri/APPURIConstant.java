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
        String REQUEST_MAPPING_CHANGE_PASSWORD = "/changePassword";
        String REQUEST_MAPPING_REGISTER_MEMBER = "/registerMember";
        //通过账号获取用户信息用于登录
        String REQUEST_MAPPING_QUERY_BY_ACCOUNT = "/queryByAccount";
        String REQUEST_MAPPING_DELETE = "/delete";
        String REQUEST_MAPPING_UPDATE = "/update";
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
        String REQUEST_MAPPING_FIND_BY_ID = "/findByID";
        String REQUEST_MAPPING_FIND_BY_PARAM = "/findByParam";
    }
    //产品
    interface Product{
   	 String REQUEST_MAPPING   = "/product";
   	 String REQUEST_MAPPING_SAVE = "/save";
   	 String REQUEST_MAPPING_UPDATE = "/update";
   	 String REQUEST_MAPPING_DEL ="/del";
      String REQUEST_MAPPING_FIND_BY_CHANNEL_ID = "/findByChannelId";
      String REQUEST_MAPPING_FIND_ID="/findByID";
      String REQUEST_MAPPING_FIND_CODE="/findByCode";
      String REQUEST_MAPPING_FIND_BY_PARAM="/findByParam";
   
    }

    interface Brand {
        String REQUEST_MAPPING = "/brand";
        String REQUEST_MAPPING_GET_BY_ID = "/{id}";
    }
    //订单
    interface Order{
      	 String REQUEST_MAPPING   = "/order";
      	 String REQUEST_MAPPING_FIND_BY_CHANNEL_ID = "/findByChannelId";
       }

    //渠道
    interface Channel {
        //渠道
        String REQUEST_MAPPING = "/channel";
        //通过appId获取渠道信息
        String REQUEST_MAPPING_GET_BY_APP_ID = "/app/id/";
        //通过appId获取渠道信息参数
        String REQUEST_MAPPING_GET_BY_APP_ID_PARAM = "{id}";
        //通过Secret获取渠道信息
        String REQUEST_MAPPING_GET_BY_APP_SECRET = "/app/secret/";
        //通过Secret获取渠道信息参数
        String REQUEST_MAPPING_GET_BY_APP_SECRET_PARAM = "{secret}";
        //通过渠道名称查询重复
        String REQUEST_MAPPING_FIND_VALID_BY_NAME = "/findValidByName";
    }
}
