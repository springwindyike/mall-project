package com.ishare.mall.center.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;


/**
 * Created by liaochenglei on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(value = CenterURIConstant.Sys.REQUEST_MAPPING)
public class SysController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(SysController.class);

    public static Logger getLog() {
        return log;
    }
    
   
  
  @RequestMapping(value = CenterURIConstant.Sys.REQUEST_MAPPING_LIST, method = RequestMethod.GET)
 	public String forwardTOproductList() {
	  return CenterViewConstant.Sys.LIST_SYS;
  }

}
