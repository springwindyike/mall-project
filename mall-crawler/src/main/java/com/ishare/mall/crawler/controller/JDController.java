package com.ishare.mall.crawler.controller;

import com.google.common.collect.Maps;
import com.ishare.mall.crawler.jd.JDCrawler;
import com.ishare.mall.crawler.jd.model.JDCategory;
import com.ishare.mall.crawler.jd.model.JDProduct;
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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/jd")
public class JDController {

    private static final Logger log = LoggerFactory.getLogger(JDController.class);

    @Autowired
    JDCategoryRepository categoryDao;

    @Autowired
    JDProductRepository productDao;

    @Autowired
    JDCrawler crawler;

    @Autowired
    JDListPageProcessor listPageProcessor;

    @Autowired
    JDPageProcessor pageProcessor;

    @RequestMapping(value = "/category")
    public String category() {
        return "jd/category";
    }

    @RequestMapping(value = "/category.json")
    public
    @ResponseBody
    Object categories(
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "_search", defaultValue = "false") boolean search,
            @RequestParam(value = "name", defaultValue = "") String name) {
        log.debug("search[{}]: name={}", search, name);
        Map<String, Object> result = Maps.newHashMap();

        Page<JDCategory> pageResult = null;
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        if (search) {
            pageResult = categoryDao.findByNameLike(name, pageRequest);
        } else {
            pageResult = categoryDao.findAll(pageRequest);
        }
        result.put("total", pageResult.getTotalPages());
        result.put("page", page);
        result.put("records", pageResult.getTotalElements());
        result.put("rows", pageResult.getContent());
        return result;
    }

    @RequestMapping(value = "/fetch/category/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Object fetchCategory(@PathVariable(value = "id") long id) {
        Map<String, Object> result = Maps.newHashMap();
        boolean success = false;
        try {
            JDCategory category = categoryDao.findOne(id);
            crawler.start(listPageProcessor, category.getLink());
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", success);
        return result;
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "jd/list";
    }

    @RequestMapping(value = "/lists.json")
    public
    @ResponseBody
    Object lists(
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "_search", defaultValue = "false") boolean search,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "price", defaultValue = "") String price,
            @RequestParam(value = "search_price_min", defaultValue = "0") String priceMin,
            @RequestParam(value = "search_price_max", defaultValue = "999999999") String priceMax,
            @RequestParam(value = "stock", defaultValue = "") String stock,
            @RequestParam(value = "self", defaultValue = "") String self,
            @RequestParam(value = "updateTime", defaultValue = "") String time) {

        log.debug("search: name={}, price={}, stock={}, self={}, time={}", name, price, stock, self, time);

        Map<String, Object> result = Maps.newHashMap();

        Page<JDProduct> pageResult = null;
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        if (search) {
            pageResult = productDao.findByNameContainingAndPriceBetween(name, Double.valueOf(priceMin), Double.valueOf(priceMax), pageRequest);
        } else {
            pageResult = productDao.findAll(pageRequest);
        }
        result.put("total", pageResult.getTotalPages());
        result.put("page", page);
        result.put("records", pageResult.getTotalElements());
        result.put("rows", pageResult.getContent());
        return result;
    }

    @RequestMapping(value = "/fetch/page/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Object fetchPage(@PathVariable(value = "id") long id) {
        Map<String, Object> result = Maps.newHashMap();
        boolean success = false;
        try {
            JDProduct product = productDao.findOne(id);
            crawler.start(pageProcessor, product.getLink());
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", success);
        return result;
    }

    @RequestMapping(value = "/page")
    public String page() {
        return "jd/page";
    }
}
