package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanghao on 2015/10/21.
 *
 */
@Controller
@RequestMapping(ManageURIConstant.Channel.REQUEST_MAPPING)
public class ChannelController {
    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

    @RequestMapping(value = "getChannelPage",method = RequestMethod.GET)
    @ResponseBody
    public String getChannelPage(){

        return null;
    }
}
