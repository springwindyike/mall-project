package com.ishare.mall.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Hello World! OpenAPI Crawler!");

        ApplicationContext context = SpringApplication.run(App.class, args);

        //JDCrawler crawler = context.getBean(JDCrawler.class);
        //crawler.start();
        log.debug("{}", context);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
