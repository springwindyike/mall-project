package com.ishare.mall.crawler;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        List list = Lists.newArrayList(json());
        return new RestTemplate(list);
    }

    @Bean
    public MappingJackson2HttpMessageConverter json() {
        return new MappingJackson2HttpMessageConverter();
    }
}
