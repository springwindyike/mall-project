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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

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
    public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "page2", defaultValue = "0") int page2) {
        log.debug("首页");

        Page<JDCategory> categories = jdCategoryRepository.findByLinkNotNullAndParentNotNull(new PageRequest(page, 20));
        model.addAttribute("categories", categories);
        log.debug("number={}, size={},page={}", categories.getNumber(), categories.getSize(), page);

        Page<JDProduct> products = jdProductRepository.findAll(new PageRequest(page2, 20));
        model.addAttribute("products", products);

        log.debug("categories size {}\nproducts size {}", categories.getTotalElements(), products.getTotalElements());
        return "index";
    }

    @RequestMapping(value = "init")
    public String init() {
        log.debug("初始化");

        Spider spider = crawler.init();

        log.debug("{}, {}, {}", spider.getUUID(), spider.getStartTime(), spider.getStatus());

        return "index";
    }

    @RequestMapping(value = "fetchList")
    public
    @ResponseBody
    Object fetchList(@RequestParam("link") String url) {
        Map<String, Object> result = Maps.newHashMap();
        boolean bln = false;
        try {
            crawler.start(url, listPageProcessor);
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
            crawler.start(url, pageProcessor);
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
}
