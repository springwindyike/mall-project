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
        //检测是否存在 如果不存在则创建
        String REQUEST_MAPPING_CHECK_AND_CREATE = "/check";

        String REQUEST_MAPPING_GET_BY_ACCOUNT = "/{account}";
    }
    interface ManageUser {
        String REQUEST_MAPPING   = "/manageuser";
        String REQUEST_MAPPING_GET_BY_USERNAME = "/{username}";
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
    	String REQUEST_MAPPING_FIND_ALL_TYPE = "/allType";
    	String REQUEST_MAPPING_FIRST_LEVEL = "/firstLevel";
    	String REQUEST_MAPPING_CHILDREN_LEVEL = "/childLevel";
        String REQUEST_MAPPING_FIND_BY_ID = "/findByID";
        String REQUEST_MAPPING_FIND_BY_PARAM = "/findByParam";
        String REQUEST_MAPPING_QUERY_BY_ID = "/findByID";
        String REQUEST_MAPPING_SAVE = "/save";
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
        String REQUEST_MAPPING_CRAWLER_ADD = "/crawler/add";
        String REQUEST_MAPPING_CRAWLER_LIST_ADD = "/crawler/list/add";
        String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition";
    }

    interface Brand {
        String REQUEST_MAPPING = "/brand";
        String REQUEST_MAPPING_ALL_BRAND = "/allBrand";
        String REQUEST_MAPPING_GET_BY_ID = "/{id}";
        String REQUEST_MAPPING_FIND_BY_PARAM = "/findByParam";
        String REQUEST_MAPPING_GET_BRAND_DETAIL = "/getBrandDetail";
        String REQUEST_MAPPING_DELETE_BY_ID = "/delById";
        String REQUEST_MAPPING_UPDATE_BY_ID = "/updateById";
        String REQUEST_MAPPING_UPDATE = "/update";
    }
    //订单
    interface Order {
        //订单
        String REQUEST_MAPPING   = "/order";
        //根据渠道ID获取订单
        String REQUEST_MAPPING_FIND_BY_CHANNEL_ID = "/findByChannelId";
        //获取所有订单
        String REQUEST_MAPPING_FIND_ALL = "/findAll";
        //创建订单
        String REQUEST_MAPPING_CREATE = "/create";
        //支付成功回调
        String REQUEST_MAPPING_PAY_BACK = "/pay/back";
        //通过订单ID获取订单详情
        String REQUEST_MAPPING_FIND_BY_ID = "/{id}";
        //发货订单提交
        String REQUEST_MAPPING_DELIVER = "/deliver";
        //取消订单提交
        String REQUEST_MAPPING_CANCEL = "/cancel";
        //编辑
        String REQUEST_MAPPING_EDIT = "/edit";
        //获取编辑数据
        String REQUEST_MAPPING_GOTO_EDIT = "/gotoedit";
        //根据条件搜索center
        String REQUEST_MAPPING_FIND_BY_SEARCHCONDITION = "/findBySearchCondition";
        //根据条件搜索manage
        String REQUEST_MAPPING_FIND_ALL_BY_SEARCHCONDITION = "/findAllBySearchCondition";

        String REQUEST_MAPPING_FIND_BY_ACCOUNT_AND_APP_ID = "/member/list";
    }
    //支付
    interface AliPay {
        //支付宝
        String REQUEST_MAPPING = "/alipay";
        //构建支付HTML
        String REQUEST_MAPPING_CREATE_PAY_HTML = "/create";
        //支付宝支付回调
        String REQUEST_MAPPING_NOTIFY = "/notify";
    }

    //渠道
    interface Channel {
        //渠道
        String REQUEST_MAPPING = "/channel";
        //通过appId获取渠道信息
        String REQUEST_MAPPING_GET_BY_APP_ID = "/app/id/{id}";
        //通过Secret获取渠道信息
        String REQUEST_MAPPING_GET_BY_APP_SECRET = "/app/secret/{secret}";
        //通过渠道名称查询重复
        String REQUEST_MAPPING_FIND_VALID_BY_NAME = "/findValidByName";
        String REQUEST_MAPPING_GET_CHANNEL_PAGE = "/getChannelPage";
        String REQUEST_MAPPING_FIND_BY_ID="/findByChnnelId";
        String REQUEST_MAPPING_UPDATE_CHANNEL_STATUS="/updateChannelStatus";
        String REQUEST_MAPPING_SAVE_CHANNEL="/saveChannel";
        String REQUEST_MAPPING_UPDATE_CHANNEL="/updateChannel";
        String REQUEST_MAPPING_FIND_BY_PARAM="/findByParam";
    }
    
    interface Express {
    	String REQUEST_MAPPING ="/express";
    	String REQUEST_MAPPING_FIND ="/find";
    }
}
