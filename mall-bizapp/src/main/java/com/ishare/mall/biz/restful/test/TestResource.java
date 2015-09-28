package com.ishare.mall.biz.restful.test;

import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.common.base.dto.test.TestDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.exception.OrderServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinLin on 2015/9/21.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestResource {
    private static final Logger log = LoggerFactory.getLogger(TestResource.class);
    @RequestMapping(value = "/gender", method = RequestMethod.GET,
            produces = { "application/json"})
    public Response test() {
       // log.debug(testDTO.getGender().getName());
        TestDTO testDTO1 = new TestDTO();
        testDTO1.setGender(Gender.MAN);
        Response response = new Response();
        response.setCode(200);
        response.setMessage("成功");
        response.setData(testDTO1);
        PageDTO<ProductDTO> pageDTO = new PageDTO();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(i);
            productDTO.setCode("1234");
            productDTOs.add(productDTO);
        }
        pageDTO.setContent(productDTOs);
        testDTO1.setPageDTO(pageDTO);
        testDTO1.setList(productDTOs);
        return response;
    }

    @RequestMapping(value = "/exp", method = RequestMethod.GET,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"})
    public Response test2() {
        System.out.println("来咯");
        if (false)
            throw new OrderServiceException("错误");
        Response response = new Response();
        response.setMessage("成功");
       // response.setData("成功啦");
        return response;
    }
}
