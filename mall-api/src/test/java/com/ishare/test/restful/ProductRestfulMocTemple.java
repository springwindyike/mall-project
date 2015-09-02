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

import com.ishare.mall.restful.ProductResource;
import com.ishare.test.TestTemplate;

/**
 * Created by Zhangzhaoxin on 2015/9/1.
 * Description :
 * Version 1.0
 */

public class ProductRestfulMocTemple extends TestTemplate {
    @Autowired
    private ProductResource productResource;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productResource).build();
    }
    @Test
    public void testProductDetailView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
        Assert.assertNotNull(result.getResponse().getContentAsString());
    }
	@Test
	public void testProductListView() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/offset/1/limit/5").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
	@Test
	public void testProductFindByCode() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/code/2110293").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
	@Test
	public void testProductListFindByBrandName() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/offset/1/limit/1/brand/匡威").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
	@Test
	public void testProductListFindBybrandId() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/offset/1/limit/1/brandId/1").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
	@Test
	public void testProductListFindByTypeName() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/offset/1/limit/1/type/男装").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
	@Test
	public void testProductListFindByTypeId() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/offset/1/limit/1/typeId/1").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
	@Test
	public void testProductListFindByName() throws Exception {
	    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/offset/1/limit/1/name/鞋").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andReturn();
	    Assert.assertNotNull(result.getResponse().getContentAsString());
	}
}
