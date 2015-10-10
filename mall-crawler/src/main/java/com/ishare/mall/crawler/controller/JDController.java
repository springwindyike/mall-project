package com.ishare.mall.crawler.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.crawler.site.jd.model.JDCategory;
import com.ishare.mall.crawler.site.jd.model.JDProduct;
import com.ishare.mall.crawler.site.jd.processor.JDPageProcessor;
import com.ishare.mall.crawler.site.jd.repository.JDCategoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jd")
public class JDController {

    private static final Logger log = LoggerFactory.getLogger(JDController.class);

    @Autowired
    JDCategoryRepository categoryDao;

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

        pageResult = null;
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

        /*
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
        result.put("rows", pageResult.getContent());*/
        return result;
    }

    @RequestMapping(value = "/fetch/page/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Object fetchPage(@PathVariable(value = "id") long id) {
        Map<String, Object> result = Maps.newHashMap();
        boolean success = false;
        try {
//            crawler.start(pageProcessor, product.getLink());
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("success", success);
        return result;
    }

    @RequestMapping(value = "/fetch/page", method = RequestMethod.GET)
    public
    @ResponseBody
    Object fetchPage(@RequestParam(value = "url") String url) {
        Map<String, Object> result = Maps.newHashMap();
        boolean success = false;
        try {
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
        JDProduct product = null;//productDao.findOne(id);
        return product;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public
    @ResponseBody
    Object search(@RequestParam(value = "searchParam") String searchParam, @RequestParam(value = "action") String action) {
        log.debug("search action: {}, param: {}", action, searchParam);
        Map<String, Object> result = Maps.newHashMap();
        boolean success = false;

        try {

            if (action.equals("search")) {
                List<Map<String, String>> mapList = Lists.newArrayList();
                String url = "http://search.jd.com/Search?enc=utf-8&keyword=" + searchParam;
                Document document = Jsoup.connect(url).ignoreContentType(true).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36").timeout(10000).execute().parse();
                Elements elements = document.select("div#plist > ul > li > div");
                log.debug("{}", elements);
                for (Element item : elements) {
                    String img = item.select("div.p-img > a > img").attr("data-lazyload");
                    String name = item.select("div.p-name > a").text();
                    String link = item.select("div.p-name > a").attr("href");
                    String price = item.select("div.p-price > strong").text();
                    log.debug("{}, {}, {}, {}", price, img, name, link);
                    if (StringUtils.isNotBlank(price + img + name + link)) {
                        Map<String, String> map = Maps.newHashMap();
                        map.put("img", img);
                        map.put("name", name);
                        map.put("link", link);
                        map.put("price", price);
                        mapList.add(map);
                    }
                }
                result.put("searchResult", mapList);
            } else if (action.equals("fetch")) {
                List<JDProduct> products = Lists.newArrayList();
                result.put("searchResult", products);
            }

            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.put("success", success);
        return result;
    }
}
