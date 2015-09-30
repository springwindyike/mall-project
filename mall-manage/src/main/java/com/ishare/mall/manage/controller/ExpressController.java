package com.ishare.mall.manage.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.dto.express.ExpressDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.controller.base.BaseController;

@Controller
@RequestMapping(ManageURIConstant.Express.REQUEST_MAPPING)
public class ExpressController  extends BaseController{
	
	private static final Logger log = LoggerFactory
			.getLogger(CategoryController.class);

	public static Logger getLog() {
		return log;
	}
	
	@RequestMapping(value = ManageURIConstant.Express.REQUEST_MAPPING_FIND, method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getExpress(@NotEmpty @PathVariable("id") String id,@NotEmpty @PathVariable("order") String order) {
    	ResponseEntity<Response<String>> resultDTO = null;
    	ExpressDTO expressDTO = new ExpressDTO();
    	expressDTO.setId(id);
    	expressDTO.setOrder(order);
    	HttpEntity<ExpressDTO> requestDTO = new HttpEntity<ExpressDTO>(expressDTO);
    	RestTemplate restTemplate = new RestTemplate();
    	try {
    		resultDTO = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.Express.REQUEST_MAPPING,APPURIConstant.Express.REQUEST_MAPPING_FIND),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response<String>>() {
                    });
		
    	} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String returnExchange =	(String) resultDTO.getBody().getData();
  return returnExchange;
	}
}
