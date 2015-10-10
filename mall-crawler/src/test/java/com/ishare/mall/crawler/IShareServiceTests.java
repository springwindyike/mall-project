package com.ishare.mall.crawler;

import com.ishare.mall.crawler.model.BasePageData;
import com.ishare.mall.crawler.repository.BasePageDataRepository;
import com.ishare.mall.crawler.service.IShareService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class IShareServiceTests {

    @Autowired
    IShareService service;

    @Autowired
    BasePageDataRepository basePageDataRepository;

    @Test
    public void testSave() {
        boolean bln = basePageDataRepository.exists(11L);
        Assert.assertTrue(bln);

        BasePageData data = basePageDataRepository.findOne(11L);
        service.toSave(data);

        Assert.assertNotNull(data);
    }
}
