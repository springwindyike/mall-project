package com.ishare.mall.crawler;


import com.ishare.mall.crawler.repository.FetchUrlRepository;
import com.ishare.mall.crawler.service.FetchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class FetchServiceTests {

    @Autowired
    FetchService fetchService;

    @Autowired
    FetchUrlRepository fetchUrlRepository;

    @Test
    public void testFetchCategory() {
        fetchService.fetchCategoryUrl("http://category.dangdang.com", true);
        fetchService.fetchCategoryUrl("http://www.jd.com/allSort.aspx", true);
        fetchService.fetchCategoryUrl("http://www.amazon.cn/gp/site-directory", true);
        /**/
        Assert.assertTrue(true);
    }

    /*
    @Test
    public void testFetchList() {
        PageRequest pageRequest = new PageRequest(123, 1);
        Page<FetchUrl> fetchUrls = null;
        String[] urls = null;

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
        fetchService.fetchListUrl(true, urls);

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
        }
        fetchService.fetchListUrl(true, urls);
    }

    @Test
    public void testFetchDangdangPage() {
        fetchPage(FetchSite.DANG_DANG, true);
        Assert.assertTrue(true);
    }

    public void testFetchJDPage() {
        fetchPage(FetchSite.JD, true);
        Assert.assertTrue(true);
    }

    public void testFetchAmazonPage() {
        fetchPage(FetchSite.AMAZON, true);
        Assert.assertTrue(true);
    }

    void fetchPage(FetchSite fetchSite, boolean store) {
        Page<FetchUrl> page = fetchUrlRepository.findByFetchSiteAndType(fetchSite, FetchUrlType.PAGE, new PageRequest(0, 10));

        List<FetchUrl> fetchUrlList = page.getContent();
        String[] urls = new String[fetchUrlList.size()];
        for (int index = 0; index < urls.length; index++) {
            urls[index] = fetchUrlList.get(index).getLink();
        }
        fetchService.fetchPageUrl(fetchSite, store, urls);
    }
    */
}
