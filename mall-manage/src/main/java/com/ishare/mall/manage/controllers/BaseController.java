package com.ishare.mall.manage.controllers;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongqi on 15/7/24.
 */
public class BaseController {

    public Map<String, Object> getEasyUIData(Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", page.getContent());
        map.put("total", page.getTotalElements());
        return map;
    }
}
