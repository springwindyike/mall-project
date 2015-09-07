package com.ishare.mall.crawler;

import com.ishare.mall.crawler.jd.JDCategory;
import com.ishare.mall.crawler.jd.JDCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class JDCategoryRepositoryTests {

    @Autowired
    JDCategoryRepository jdCategoryRepository;

    @Test
    public void testSave() {
        JDCategory category = new JDCategory();
        category.setName("test");
        category.setLink("http://www.baidu.com");

        jdCategoryRepository.save(category);
        Assert.assertTrue(true);
    }
}
