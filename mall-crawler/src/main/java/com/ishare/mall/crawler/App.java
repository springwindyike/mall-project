package com.ishare.mall.crawler;

import com.ishare.mall.crawler.jd.JDCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Hello World!");

        ApplicationContext context = SpringApplication.run(App.class, args);

        JDCrawler crawler = context.getBean(JDCrawler.class);
        crawler.start();
    }
}
