package com.ishare.mall.crawler.site.jd;

import com.ishare.mall.crawler.site.jd.model.JDCategory;
import com.ishare.mall.crawler.site.jd.model.JDProduct;
import com.ishare.mall.crawler.site.jd.repository.JDCategoryRepository;
import com.ishare.mall.crawler.site.jd.repository.JDProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Set;

/**
 * Created by dongqi on 15/9/7.
 */
@Component
public class JDPipeline implements Pipeline {

    private static final Logger log = LoggerFactory.getLogger(JDPipeline.class);

    @Autowired
    private JDCategoryRepository jdCategoryRepository;

    @Autowired
    private JDProductRepository jdProductRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {

        if (resultItems.get("category") != null) {
            Object object = resultItems.get("category");
            JDCategory category = (JDCategory) object;
            jdCategoryRepository.save(category);
            log.debug("save JDCategory");
        }

        if (resultItems.get("product") != null) {
            //新增
            Object object = resultItems.get("product");
            Set<JDProduct> products = (Set<JDProduct>) object;
            jdProductRepository.save(products);
            log.debug("save JDProduct");
        }

        if (resultItems.get("product.update") != null) {

            JDProduct product = resultItems.get("product.update");
            log.debug("{},{},{}", product.getCode(), product.getLink(), this);
            /**/
            JDProduct productInDB = jdProductRepository.findByCode(product.getCode());
            if (productInDB != null) {
                //更新
                BeanUtils.copyProperties(product, productInDB, "id", "createTime");
                jdProductRepository.save(productInDB);
            } else {
                //新增
                jdProductRepository.save(product);
            }

        }

        log.debug("done ...");
    }
}
