package com.ishare.mall.biz.restful.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.status.MemberType;

/**
 * Created by YinLin on 2015/9/1.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Product.REQUEST_MAPPING)
public class ProductResource {
	
    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    private ProductService productService;

    public static Logger getLog() {
        return log;
    }



    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ProductDetailDTO saveProduct(@RequestBody ProductDetailDTO productDetailDTO){
            Product product = new Product();
            BeanUtils.copyProperties(productDetailDTO, product);
        			Brand brand = new Brand();
        			brand.setId(productDetailDTO.getBrandId());
        			Member member = new Member();
        			member.setAccount(productDetailDTO.getCreateByAccount());
        			member.setMemberType(MemberType.MEMBER);
        			member.setSex(Gender.MAN);
        			Channel channel = new Channel();
        			channel.setId(productDetailDTO.getChannelId());
        			member.setChannel(channel);
        			ProductType productType = new ProductType();
        			productType.setId(productDetailDTO.getTypeId());
        			productType.setCode("1001001001");
        			productType.setName("衬衫");
        			productType.setNote("非常好");
        			productType.setLevel(3);
        			product.setBrand(brand);
        			product.setCreateBy(member);
        			product.setChannel(channel);
        			product.setType(productType);
        			
        			try {
						productService.saveProduct(product);
					} catch (Exception e) {
						e.printStackTrace();
					}
		return productDetailDTO;
    }

}
