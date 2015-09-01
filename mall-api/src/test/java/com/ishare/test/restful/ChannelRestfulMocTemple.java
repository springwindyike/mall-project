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

import com.ishare.mall.restful.ChannelResource;
import com.ishare.test.TestTemplate;

/**
 * Created by liaochenglei on 2015/9/1.
 * Description :
 * Version 1.0
 */

public class ChannelRestfulMocTemple extends TestTemplate {
    @Autowired
    private ChannelResource channelResource;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(channelResource).build();
    }
    @Test
    public void testBrandDetailView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/channels/summeryMoney/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
    }
 
}
