package com.ishare.mall.api.restful;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.general.QueryResult;
import com.ishare.mall.core.model.order.OrderDeliverInfo;
import com.ishare.mall.core.service.deliver.DeliverService;
import com.ishare.mall.core.status.DeliverWay;
import com.ishare.mall.core.status.Gender;

/**
 * Created by liaochenglei on 2015/8/25.
 * Description:收货地址管理
 * Version 1.0
 */
@RestController
@RequestMapping("/deliver")
public class DeliverResource {

    private static final Logger log = LoggerFactory.getLogger(DeliverResource.class);
    
    @Autowired
    private DeliverService deliverService;

    /**
     * 保存用户地址信息  post
     * 
  * @return  返回的数据对象
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute("orderDeliverInfoAttribute") OrderDeliverInfo odi) {
		log.debug("Here...");
    	OrderDeliverInfo orderDeliverInfo = deliverService.save(odi);
    	QueryResult qr = new QueryResult();
    	if (orderDeliverInfo != null) {
    		return qr;
    	}else {
    		qr.setSuccess(false);
    		return qr;
    	}
    }
    
    @RequestMapping(value = "/savea", method = RequestMethod.GET)
    public Object saveTest() {
        //用findOne立即加载实体对象
     	OrderDeliverInfo orderDeliverInfo = new OrderDeliverInfo();
     	orderDeliverInfo.setDeliverWay(DeliverWay.EMS);
     	orderDeliverInfo.setRecipients("张三");
     	orderDeliverInfo.setCountry("中国");
     	orderDeliverInfo.setProvince("四川省");;
     	orderDeliverInfo.setCity("成都");
     	orderDeliverInfo.setDistrict("双流县");
     	orderDeliverInfo.setDetail("花样年华");
     	orderDeliverInfo.setEmail("1025987410@qq.com");
     	orderDeliverInfo.setPostalCode("610000");
     	orderDeliverInfo.setTel("85687440");
     	orderDeliverInfo.setMobile("15745585851");
     	orderDeliverInfo.setGender(Gender.MAN);
     	orderDeliverInfo.setRequirement("尽快过来");
    	OrderDeliverInfo odi = deliverService.save(orderDeliverInfo);
    	QueryResult qr = new QueryResult();
     	if (odi != null) {
    		return qr;
    	}else {
    		qr.setSuccess(false);
    		return qr;
    	}
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@NotEmpty @PathVariable("id") Integer id){
    	 deliverService.delete(id);
    	 
    }
}
