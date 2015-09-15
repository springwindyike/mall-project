package com.ishare.mall.crawler.controller;

import com.google.common.collect.Maps;
import com.ishare.mall.crawler.jd.JDCrawler;
import com.ishare.mall.crawler.jd.model.JDCategory;
import com.ishare.mall.crawler.jd.model.JDProduct;
import com.ishare.mall.crawler.jd.processor.JDCategoryPageProcessor;
import com.ishare.mall.crawler.jd.processor.JDListPageProcessor;
import com.ishare.mall.crawler.jd.processor.JDPageProcessor;
import com.ishare.mall.crawler.jd.repository.JDCategoryRepository;
import com.ishare.mall.crawler.jd.repository.JDProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import us.codecraft.webmagic.Spider;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    JDCrawler crawler;

    @Autowired
    JDCategoryRepository jdCategoryRepository;

    @Autowired
    JDProductRepository jdProductRepository;

    @Autowired
    JDCategoryPageProcessor categoryPageProcessor;
    @Autowired
    JDListPageProcessor listPageProcessor;
    @Autowired
    JDPageProcessor pageProcessor;

    @RequestMapping("/")
    public String index(Model model) {
        log.debug("首页");

        return "dashboard";
    }

    @RequestMapping(value = "init")
    public String init() {
        log.debug("初始化");

        Spider spider = crawler.init();

        log.debug("{}, {}, {}", spider.getUUID(), spider.getStartTime(), spider.getStatus());

        return "index";
    }

    @RequestMapping(value = "fetchAll", method = RequestMethod.GET)
    public
    @ResponseBody
    Object fetchAll() {
        Map<String, Object> result = Maps.newHashMap();
        boolean bln = false;
        try {
            List<JDProduct> list = jdProductRepository.findAll();
            String[] urls = new String[list.size()];
            for (int index = 0; index < urls.length; index++) {
                urls[index] = list.get(index).getLink();
            }
            crawler.start(pageProcessor, urls);
            bln = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", bln);
        return result;
    }

    @RequestMapping(value = "fetchList", method = RequestMethod.POST)
    public
    @ResponseBody
    Object fetchList(@RequestParam("link") String url) {
        Map<String, Object> result = Maps.newHashMap();
        boolean bln = false;
        try {
            crawler.start(listPageProcessor, url);
            bln = true;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "error");
        }

        result.put("success", bln);
        return result;
    }

    @RequestMapping(value = "fetchPage")
    public
    @ResponseBody
    Object fetchPage(@RequestParam("link") String url) {
        Map<String, Object> result = Maps.newHashMap();
        boolean bln = false;
        try {
            crawler.start(pageProcessor, url);
            bln = true;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "error");
        }

        result.put("success", bln);
        return result;
    }

    @RequestMapping(value = "product/{id}")
    public String page(@PathVariable(value = "id") long id, Model model) {

        JDProduct product = jdProductRepository.findOne(id);
        model.addAttribute("product", product);

        return "product";
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {

        return "dashboard";
    }

    @RequestMapping(value = "data.json", method = RequestMethod.GET)
    public
    @ResponseBody
    Object data(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "search[value]", defaultValue = "") String search) {
        log.debug("search: {}", search);

        Map<String, Object> data = Maps.newHashMap();
        Page<JDCategory> page = jdCategoryRepository.findAll(new PageRequest(start / length, length));
        data.put("recordsTotal", page.getTotalElements());
        data.put("recordsFiltered", page.getTotalElements());
        data.put("data", page.getContent());
        return data;
    }

    @RequestMapping(value = "jdCategory")
    public String jdCategory() {

        return "jd/category";
    }
}
