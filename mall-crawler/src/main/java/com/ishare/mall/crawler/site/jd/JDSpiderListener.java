package com.ishare.mall.crawler.site.jd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

/**
 * Created by dongqi on 15/9/7.
 */
@Component
public class JDSpiderListener implements SpiderListener {

    private static final Logger log = LoggerFactory.getLogger(JDSpiderListener.class);

    @Override
    public void onSuccess(Request request) {
        log.debug("{}", request);
    }

    @Override
    public void onError(Request request) {
        log.debug("{}", request);
    }
}
