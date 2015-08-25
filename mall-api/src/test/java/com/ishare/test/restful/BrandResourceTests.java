package com.ishare.test.restful;

import com.ishare.test.TestTemplate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YinLin on 2015/8/25.
 * Description :
 * Version 1.0
 */
public class BrandResourceTests extends TestTemplate {
    private HandlerMapping handlerMapping;
    private HandlerAdapter handlerAdapter;
    @Before
    public void up() {
        handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
        handlerAdapter = (HandlerAdapter) context.getBean(RequestMappingHandlerAdapter.class);
    }

    @Test
    public void testGet() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setRequestURI("/brands/1");
        request.setMethod("GET");

        try{
            final ModelAndView mav = this.excuteAction(request, response);
            System.out.println(mav.toString());
//            System.out.println(mav.getViewName());
//            Object msg = mav.getModelMap().get("roles");
//            System.out.println(" = " + msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ModelAndView excuteAction(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        // 这里需要声明request的实际类型，否则会报错
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        ModelAndView model = null;
        try {
            model = handlerAdapter
                    .handle(request, response, chain.getHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
