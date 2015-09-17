package com.ishare.mall.crawler.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/fetch")
public class FetchRestController {

    private static final Logger log = LoggerFactory.getLogger(FetchRestController.class);

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object fetch(@RequestParam(value = "url") String url) {


        return null;
    }
}
