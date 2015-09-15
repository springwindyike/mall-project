package com.ishare.mall.crawler.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.crawler.jd.JDCrawler;
import com.ishare.mall.crawler.jd.model.JDCategory;
import com.ishare.mall.crawler.jd.model.JDProduct;
import com.ishare.mall.crawler.jd.processor.JDListPageProcessor;
import com.ishare.mall.crawler.jd.processor.JDPageProcessor;
import com.ishare.mall.crawler.jd.repository.JDCategoryRepository;
import com.ishare.mall.crawler.jd.repository.JDProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
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
            //@RequestParam(value = "_search", defaultValue = "false") boolean search,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "price", defaultValue = "") String price,
            @RequestParam(value = "stock", defaultValue = "") String stock,
            @RequestParam(value = "self", defaultValue = "") String self,
            @RequestParam(value = "updateTime", defaultValue = "") String time) {

        log.debug("search: name={}, price={}, stock={}, self={}, time={}", name, price, stock, self, time);

        Map<String, Object> result = Maps.newHashMap();

        Page<JDProduct> pageResult = null;
        PageRequest pageRequest = new PageRequest(page - 1, rows);

        pageResult = productDao.findAll(pageRequest);
        result.put("total", pageResult.getTotalPages());
        result.put("page", page);
        result.put("records", pageResult.getTotalElements());
        result.put("rows", pageResult.getContent());
        return result;
    }

    @RequestMapping(value = "/lists/search.json")
    public
    @ResponseBody
    Object search(
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "page", defaultValue = "1") int page,
            HttpServletRequest request
    ) throws ServletRequestBindingException {
        final String name = ServletRequestUtils.getStringParameter(request, "search_name", "");
        final double min = ServletRequestUtils.getDoubleParameter(request, "search_price_min", 0);
        final double max = ServletRequestUtils.getDoubleParameter(request, "search_price_max", 0);
        final String start = ServletRequestUtils.getStringParameter(request, "search_update_time_min", "");
        final String end = ServletRequestUtils.getStringParameter(request, "search_update_time_max", "");
        final String stock = ServletRequestUtils.getStringParameter(request, "search_stock", "");
        final String self = ServletRequestUtils.getStringParameter(request, "search_self", "");

        log.debug("查询 {}, {}, {}, {}, {}, {}, {}, {}, {}", rows, page, name, min, max, start, end, stock, self);
        Map<String, Object> result = Maps.newHashMap();

        Page<JDProduct> pageResult = productDao.findAll(new Specification<JDProduct>() {
            @Override
            public Predicate toPredicate(Root<JDProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();

                //root = criteriaQuery.from(JDProduct.class);
                if (StringUtils.isNotBlank(name)) {
                    Path nameExp = root.get("name");
                    predicates.add(criteriaBuilder.like(nameExp, "%" + name + "%"));
                }
                if (min < max && max > 0) {
                    Path priceExp = root.get("price");
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(priceExp, min));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(priceExp, max));
                }
                if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
                    Date _start = DateTime.parse(start, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
                    Date _end = DateTime.parse(end, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
                    Path timeExp = root.get("jdDatetime");
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(timeExp, _start));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(timeExp, _end));
                }
                if (StringUtils.isNotBlank(stock)) {
                    Path stockExp = root.get("stock");
                    predicates.add(criteriaBuilder.equal(stockExp, stock));
                }
                if (StringUtils.isNotBlank(self)) {
                    Path selfExp = root.get("self");
                    predicates.add(criteriaBuilder.equal(selfExp, Boolean.valueOf(self).booleanValue()));
                }

                if (predicates.isEmpty()) {
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, new PageRequest(page - 1, rows));
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

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    JDProduct getProduct(@PathVariable(value = "id") long id) {
        JDProduct product = productDao.findOne(id);
        return product;
    }
}
