package com.ishare.mall.crawler.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ishare.mall.crawler.model.FetchSite;
import com.ishare.mall.crawler.model.FetchUrl;
import com.ishare.mall.crawler.model.FetchUrlType;
import com.ishare.mall.crawler.model.jqgrid.JQGridPageRequest;
import com.ishare.mall.crawler.model.jqgrid.JQGridPageResponse;
import com.ishare.mall.crawler.repository.BasePageDataRepository;
import com.ishare.mall.crawler.repository.FetchUrlRepository;
import com.ishare.mall.crawler.service.FetchService;
import com.ishare.mall.crawler.service.IShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fetch")
public class FetchRestController {

    private static final Logger log = LoggerFactory.getLogger(FetchRestController.class);

    @Autowired
    FetchUrlRepository fetchUrlRepository;

    @Autowired
    FetchService fetchService;

    @Autowired
    IShareService iShareService;

    @Autowired
    BasePageDataRepository basePageDataRepository;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false));
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object fetch(@RequestParam(value = "url") String url) {
        return null;
    }

    @RequestMapping(value = "/urls.json", method = RequestMethod.GET)
    public JQGridPageResponse<FetchUrl> fetchUrls(JQGridPageRequest pageRequest, FetchSite fetchSite) {
        return fetch(pageRequest, FetchUrlType.LIST, fetchSite);
    }

    @RequestMapping(value = "/pages.json", method = RequestMethod.GET)
    public JQGridPageResponse<FetchUrl> fetchPageUrls(JQGridPageRequest pageRequest, FetchSite fetchSite) {
        return fetch(pageRequest, FetchUrlType.PAGE, fetchSite);
    }

    JQGridPageResponse<FetchUrl> fetch(JQGridPageRequest pageRequest, FetchUrlType type, FetchSite fetchSite) {
        Sort sort = new Sort(Sort.Direction.fromString(pageRequest.getSord()), pageRequest.getSidx());
        PageRequest page = new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows(), sort);
        Page<FetchUrl> fetchUrlPage = null;
        if (fetchSite == null) {
            fetchUrlPage = fetchUrlRepository.findByTypeAndValidIsTrue(type, page);
        } else {
            fetchUrlPage = fetchUrlRepository.findByTypeAndValidIsTrue(type, page);
        }

        JQGridPageResponse<FetchUrl> response = new JQGridPageResponse(fetchUrlPage);
        log.debug("{}, {}", type, response);
        return response;
    }

    @RequestMapping(value = "/url", method = RequestMethod.POST)
    public Object fetch(long id) {
        Map<String, Object> response = Maps.newHashMap();

        try {
            FetchUrl fetchUrl = fetchUrlRepository.findOne(id);
            if (fetchUrl.getType().equals(FetchUrlType.LIST)) {
                fetchService.fetchListUrl(true, fetchUrl.getLink());
            } else if (fetchUrl.getType().equals(FetchUrlType.PAGE)) {
                fetchService.fetchPageUrl(fetchUrl.getFetchSite(), true, fetchUrl.getLink());
            }
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/urls", method = RequestMethod.POST)
    public Object fetch(@RequestParam(value = "ids[]") String[] ids) {
        log.debug("ids {}", Arrays.toString(ids));
        Map<String, Object> response = Maps.newHashMap();

        try {
            List<Long> idList = Lists.newArrayList();
            for (String id : ids) {
                idList.add(Long.valueOf(id));
            }
            List<FetchUrl> urls = fetchUrlRepository.findByIdIn(idList);
            String[] links = new String[urls.size()];
            FetchSite fetchSite = null;
            for (int index = 0; index < links.length; index++) {
                FetchUrl fetchUrl = urls.get(index);
                String url = fetchUrl.getLink();
                links[index] = url;
                fetchSite = fetchUrl.getFetchSite();

            }

            fetchService.fetchPageUrl(fetchSite, true, links);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/oper", method = RequestMethod.POST)
    public Object operation(String oper, Long id) {
        Map<String, Object> response = Maps.newHashMap();
        try {
            if ("del".equals(oper)) {
                FetchUrl fetchUrl = fetchUrlRepository.findOne(id);
                fetchUrl.setValid(false);
                fetchUrlRepository.save(fetchUrl);
                response.put("success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        }
        return response;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(boolean isSingle, @RequestParam(value = "isAll", defaultValue = "false") boolean isAll) {
        Map<String, Object> response = Maps.newHashMap();
        try {
            if (isAll) {
                iShareService.toSave(basePageDataRepository.findAll());
            } else if (isSingle) {
                iShareService.toSave(basePageDataRepository.findOne(1L));
            } else {
                iShareService.toSave(basePageDataRepository.findAll(new PageRequest(0, 10)).getContent());
            }
            response.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
        }
        return response;
    }
}
