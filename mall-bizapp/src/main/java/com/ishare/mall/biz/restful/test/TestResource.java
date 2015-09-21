package com.ishare.mall.biz.restful.test;

import com.ishare.mall.common.base.dto.test.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/21.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestResource {
    private static final Logger log = LoggerFactory.getLogger(TestResource.class);
    @RequestMapping(value = "/gender", method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public TestDTO test(@RequestBody TestDTO testDTO) {
        log.debug(testDTO.getGender().getName());
        return testDTO;
    }
}
