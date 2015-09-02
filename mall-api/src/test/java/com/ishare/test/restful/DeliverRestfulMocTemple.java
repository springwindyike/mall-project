package com.ishare.test.restful;

import com.ishare.mall.restful.DeliverResource;
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
 * Created by liaochenglei on 2015/9/1.
 * Description :
 * Version 1.0
 */

public class DeliverRestfulMocTemple extends TestTemplate {
    @Autowired
    private DeliverResource deliverResource;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deliverResource).build();
    }
    @Test
    public void deliverTestAdd() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/deliver/save").param("deliverWay", "国内特快专递EMS")
                .param("recipients", "张三")
                .param("country", "中国")
                .param("province", "四川省")
                .param("city", "成都")
                .param("district", "上流")
                .param("detail", "花样年华")
                .param("email", "1025987410@qq.com")
                .param("postalCode", "610000")
                .param("tel", "85687440")
                .param("mobile", "15745585851")
                .param("gender", "男")
                .param("requirement", "赶快来").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
			        Assert.assertNotNull(result.getResponse().getContentAsString());
    
    }
   
    @Test
    public void deliverTestDelete() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/deliver/delete/4").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
    }
}
