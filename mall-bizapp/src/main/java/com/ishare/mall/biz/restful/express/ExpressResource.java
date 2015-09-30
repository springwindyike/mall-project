package com.ishare.mall.biz.restful.express;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.express.ExpressDTO;
import com.ishare.mall.common.base.general.Response;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Express.REQUEST_MAPPING)
public class ExpressResource {
	
    private static final Logger log = LoggerFactory.getLogger(ExpressResource.class);

    public static Logger getLog() {
        return log;
    }



    @RequestMapping(value = APPURIConstant.Express.REQUEST_MAPPING_FIND, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response findExpress(@RequestBody ExpressDTO expressDTO)throws Exception{
    	 Response response = new Response();
 		CloseableHttpResponse httpResponse = null;
 		CloseableHttpClient httpClient = HttpClients.createDefault();
    			try {
    				String url = "http://www.aikuaidi.cn/rest/?key=266f8c229f87424c9637bf1d3221c00f&order="+expressDTO.getOrder()+"&id="+expressDTO.getId();
    				HttpGet httpGet = new HttpGet(url);
					httpResponse = httpClient.execute(httpGet);
					int statusCode = httpResponse.getStatusLine().getStatusCode();
					if (statusCode == HttpStatus.SC_OK) {
						HttpEntity entity = httpResponse.getEntity();
						String entityString = EntityUtils.toString(entity);
						response.setData(entityString);
						}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		} finally {
			if (httpResponse != null) {
				httpResponse.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
    			return response;
}  
}
