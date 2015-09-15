package com.ishare.test.restful;

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

import com.ishare.mall.api.restful.OrderResource;
import com.ishare.test.TestTemplate;

/**
 * Created by Zhangzhaoxin on 2015/9/2.
 * Description :
 * Version 1.0
 */

public class OrderRestfulMocTemple extends TestTemplate {
    @Autowired
    private OrderResource orderResource;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderResource).build();
    }
    @Test
    public void testOrderDetailView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
    }
	@Test
	public void testOrderListView() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/offset/1/limit/1").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}

}
