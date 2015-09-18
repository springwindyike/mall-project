package com.ishare.mall.crawler;


import com.ishare.mall.crawler.model.FetchSite;
import com.ishare.mall.crawler.model.FetchUrl;
import com.ishare.mall.crawler.repository.FetchUrlRepository;
import com.ishare.mall.crawler.service.FetchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class FetchServiceTests {

    @Autowired
    FetchService fetchService;

    @Autowired
    FetchUrlRepository fetchUrlRepository;

    @Test
    public void testFetch() {
        /*
        fetchService.fetchCategoryUrl("http://category.dangdang.com", true);
        fetchService.fetchCategoryUrl("http://www.jd.com/allSort.aspx", true);
        fetchService.fetchCategoryUrl("http://www.amazon.cn/gp/site-directory", true);
        */
        PageRequest pageRequest = new PageRequest(123, 1);
        Page<FetchUrl> fetchUrls = null;
        String[] urls = null;
        /*
        fetchUrls = fetchUrlRepository.findByFetchSiteAndType(FetchSite.AMAZON, FetchUrlType.LIST, pageRequest);
        urls = new String[fetchUrls.getContent().size()];
        for (int index = 0; index < urls.length; index++) {
            urls[index] = fetchUrls.getContent().get(index).getLink();
        }
        fetchService.fetchListUrl(true, urls);

        fetchUrls = fetchUrlRepository.findByFetchSiteAndType(FetchSite.JD, FetchUrlType.LIST, pageRequest);
        urls = new String[fetchUrls.getContent().size()];
        for (int index = 0; index < urls.length; index++) {
            urls[index] = fetchUrls.getContent().get(index).getLink();
        }
        fetchService.fetchListUrl(true, urls);*/
        /*
        fetchUrls = fetchUrlRepository.findByFetchSiteAndType(FetchSite.DANG_DANG, FetchUrlType.LIST, pageRequest);
        urls = new String[fetchUrls.getContent().size()];
        for (int index = 0; index < urls.length; index++) {
            urls[index] = fetchUrls.getContent().get(index).getLink();
        }
        fetchService.fetchListUrl(true, urls);

        fetchUrls = fetchUrlRepository.findByFetchSiteAndType(FetchSite.JD, FetchUrlType.PAGE, pageRequest);
        urls = new String[fetchUrls.getContent().size()];
        for (int index = 0; index < urls.length; index++) {
            urls[index] = fetchUrls.getContent().get(index).getLink();
        }*/
        fetchService.fetchPageUrl(FetchSite.DANG_DANG, true, "http://product.dangdang.com/60600067.html", "http://product.dangdang.com/1226813133.html");

        Assert.assertTrue(true);
    }
}
