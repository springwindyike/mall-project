package com.ishare.mall.api.restful;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.api.restful.base.BaseResource;
import com.ishare.mall.common.base.constant.uri.APIURIConstant;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.express.ExpressDTO;
import com.ishare.mall.common.base.general.Response;

/**
 * Created by liaochenglei on 2015/9/30.
 * Description:产品接口相关
 * Version 1.0
 */
@RestController
@RequestMapping(APIURIConstant.Express.REQUEST_MAPPING)
public class ExpressResource extends BaseResource{

    private static final Logger log = LoggerFactory.getLogger(ExpressResource.class);
    @Autowired
    private RestTemplate restTemplate;
/**
 * 
 * @param id
 * @return
 */
    @RequestMapping(value = APIURIConstant.Express.REQUEST_MAPPING_FIND, method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String getExpress(@NotEmpty @PathVariable("id") String id,@NotEmpty @PathVariable("order") String order) {
    	ResponseEntity<Response<String>> resultEntity = null;
    	ExpressDTO expressDTO = new ExpressDTO();
    	expressDTO.setId(id);
    	expressDTO.setOrder(order);
    	HttpEntity<ExpressDTO> requestDTO = new HttpEntity<ExpressDTO>(expressDTO);
    	try {
    		resultEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Express.REQUEST_MAPPING,APPURIConstant.Express.REQUEST_MAPPING_FIND),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response<String>>() {
                    });
		
    	} catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
        //获取返回结果
    	String returnExchange =	(String) resultEntity.getBody().getData();
    	  return returnExchange;
}
}