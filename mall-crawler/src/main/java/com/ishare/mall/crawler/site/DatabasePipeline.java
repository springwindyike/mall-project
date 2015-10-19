package com.ishare.mall.crawler.site;

import com.google.common.collect.Lists;
import com.ishare.mall.crawler.model.BasePageData;
import com.ishare.mall.crawler.model.FetchUrl;
import com.ishare.mall.crawler.repository.BasePageDataRepository;
import com.ishare.mall.crawler.repository.FetchUrlRepository;
import com.ishare.mall.crawler.service.IShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class DatabasePipeline implements Pipeline {

    private static final Logger log = LoggerFactory.getLogger(DatabasePipeline.class);

    @Autowired
    FetchUrlRepository fetchUrlRepository;

    @Autowired
    BasePageDataRepository basePageDataRepository;

    @Autowired
    IShareService iShareService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.debug("UUID={}, Site={}", task.getUUID(), task.getSite());

        Object items = resultItems.get(FetchConstant.KEY_ITEM);
        log.debug("items = {}", items);
        if (items == null) return;

        Class clazz = resultItems.get(FetchConstant.KEY_CLASS);
        log.debug("clazz = {}", clazz);
        if (clazz.equals(FetchUrl.class)) {
            List<FetchUrl> fetchUrls = (List<FetchUrl>) items;
            List<FetchUrl> needSaveList = Lists.newArrayList();
            for (FetchUrl fetchUrl : fetchUrls) {
                FetchUrl fetchUrlInDB = fetchUrlRepository.findByLinkIs(fetchUrl.getLink());
                if (fetchUrlInDB == null) {
                    //add
                    needSaveList.add(fetchUrl);
                } else {
                    //modify
                    BeanUtils.copyProperties(fetchUrl, fetchUrlInDB, "id", "createTime");
                    needSaveList.add(fetchUrlInDB);
                }
            }
            if (!needSaveList.isEmpty()) {
                fetchUrlRepository.save(needSaveList);
            }

        }

        if (clazz.equals(BasePageData.class)) {
            BasePageData basePageData = (BasePageData) items;

            //Long fetchUrlId = basePageDataRepository.findByLinkAndCode(basePageData.getLink(), basePageData.getCode());
            FetchUrl fetchUrl = fetchUrlRepository.findByLinkIs(basePageData.getLink());
            BasePageData pageData = fetchUrl.getPageData();
            log.debug("fetchUrl={}, link={}, code={}", fetchUrl, basePageData.getLink(), basePageData.getCode());
            if (pageData == null) {
                basePageData.setFetchSite(fetchUrl.getFetchSite());
                fetchUrl.setPageData(basePageData);
                basePageDataRepository.save(basePageData);
                fetchUrlRepository.save(fetchUrl);
            } else {
                BeanUtils.copyProperties(basePageData, pageData, "id", "fetchUrl", "fetchSite");
                basePageDataRepository.save(pageData);
            }

        }
    }
}
