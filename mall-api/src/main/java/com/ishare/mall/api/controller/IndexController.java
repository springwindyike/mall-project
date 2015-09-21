package com.ishare.mall.api.controller;


import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.common.base.dto.test.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by YinLin on 2015/7/30.
 * Description: Test Demo 这个Api的Demo类
 * Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseResource {
   private static final Logger log = LoggerFactory.getLogger(IndexController.class);
//    @Autowired
//    private ChannelService channelService;
//    @Autowired
//    private OrderService orderService;
//    @RequestMapping(value = "get", method = RequestMethod.GET)
//    @ResponseBody
//    public String get() {
//        return "success";
//    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public TestDTO show(TestForm testForm) {
        log.debug(testForm.getGender().getName());
        ResponseEntity<TestDTO> resultEntiy = null;
        TestDTO testDTO = new TestDTO();
        testDTO.setGender(testForm.getGender());
        RestTemplate restTemplate = new RestTemplate();
        resultEntiy = restTemplate.postForEntity(this.buildBizAppURI("/test", "/gender"), testDTO, TestDTO.class);
        testDTO = resultEntiy.getBody();
        return testDTO;
    }

//    @RequestMapping(value = "list", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Product> list() {
//        List<Product> products = Lists.newArrayList();
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setCode("10001010" + (i + 1));
//            product.setName("衣服");
//            products.add(product);
//        }
//        return products;
//    }
//    @RequestMapping(value = "/channels/a/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Channel getByA(@NotEmpty @PathVariable("id")String appId) {
//        return channelService.findByAppId(appId);
//    }
//    @RequestMapping(value = "/channels/s/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Channel getBys(@NotEmpty @PathVariable("id")String appId) {
//        return channelService.findByAppSecret(appId);
//    }
//    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Order getOrder(@NotEmpty @PathVariable("id") String orderId) {
//    	return orderService.findOne(orderId);
//    }
//    @RequestMapping(value = "/test")
//    @ResponseBody
//    public Object test(TestForm form) {
//        if (form != null) {
//            log.debug(form.getId());
//            log.debug(form.getName());
//            log.debug(form.getGender().getName());
//        }
//        return form;
//    }

//    @RequestMapping(value = "/testRes")
//    public void testResponse(HttpServletResponse response) throws OAuthSystemException {
//        OAuthResponse responses =
//                OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
//                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
//                        .setErrorDescription(INVALID_CLIENT_DESCRIPTION)
//                        .buildJSONMessage();
//        Servlets.responseHttpJson(response, new ResponseEntity(responses.getBody(), HttpStatus.valueOf(responses.getResponseStatus())));
//    }
//
//    @RequestMapping(value = "/testResE")
//    public ResponseEntity testResponseE() throws OAuthSystemException {
//        OAuthResponse response =
//                OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
//                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
//                        .setErrorDescription(INVALID_CLIENT_DESCRIPTION)
//                        .buildJSONMessage();
//        return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
//    }
}
