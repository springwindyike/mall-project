package com.ishare.test.restful;

import com.ishare.mall.restful.BrandResource;
import com.ishare.test.TestTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by YinLin on 2015/8/25.
 * Description :
 * Version 1.0
 */

public class RestfulMocTemple extends TestTemplate {
    @Autowired
    private BrandResource brandResource;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(brandResource).build();
    }
    @Test
    public void testBrandDetailView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/brands/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNull(result.getModelAndView());
    }
    @Test
    public void testBrandListView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/brands/offset/1/limit/5").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNull(result.getModelAndView());
    }
}
