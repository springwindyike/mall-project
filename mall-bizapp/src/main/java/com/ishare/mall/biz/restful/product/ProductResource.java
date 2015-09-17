package com.ishare.mall.biz.restful.product;


import java.util.ArrayList;
import java.util.List;

import com.ishare.mall.common.base.dto.product.*;
import com.ishare.mall.core.model.product.ProductStyle;
import com.ishare.mall.core.service.product.ProductStyleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.status.MemberType;
import com.ishare.mall.core.utils.mapper.MapperUtils;

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
	@Autowired
	private ProductStyleService productStyleService;
    public static Logger getLog() {
        return log;
    }



    @RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_SAVE, method = RequestMethod.POST,
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
        			product.setUpdateBy(member);
        			product.setChannel(channel);
        			product.setType(productType);
        			
        			try {
						productService.saveProduct(product);
					} catch (Exception e) {
						e.printStackTrace();
					}
		return productDetailDTO;
    }

	/**
	 * 根据商品ID 查询商品详细信息
	 * @param productDetailDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_ID, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public ProductDetailDTO findByID(@RequestBody ProductDetailDTO productDetailDTO){
		Product product = productService.findOne(productDetailDTO.getId());
		if(product == null || !product.getVisible()){
			return productDetailDTO;
		}
		List<ProductStyle> list = productStyleService.findByProductStyle(productDetailDTO.getId());
		List<ProductStyleDTO> listStyle = new ArrayList<ProductStyleDTO>();
		if(list != null && list.size()>0){
			for(ProductStyle productStyle:list){
				ProductStyleDTO productStyleDTO = new ProductStyleDTO();
				BeanUtils.copyProperties(productStyle,productStyleDTO);
				listStyle.add(productStyleDTO);
				log.debug(list.get(0).getProduct().getName());
			}
		}
		ProductDetailDTO returnDTO = (ProductDetailDTO)MapperUtils.map(product, ProductDetailDTO.class);
		returnDTO.setList(listStyle);
		return returnDTO;
	}

	/**
	 * 根据商品的code查询详细信息
	 * @param productDetailDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_CODE, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public ProductDetailDTO findByCode(@RequestBody ProductDetailDTO productDetailDTO){
		Product product = productService.findByCode(productDetailDTO.getCode());
		if(product == null || !product.getVisible()){
			return productDetailDTO;
		}
		List<ProductStyle> list = productStyleService.findByProductStyle(product.getId());
		List<ProductStyleDTO> listStyle = new ArrayList<ProductStyleDTO>();
		if(list != null && list.size()>0){
			for(ProductStyle productStyle:list){
				ProductStyleDTO productStyleDTO = new ProductStyleDTO();
				BeanUtils.copyProperties(productStyle,productStyleDTO);
				listStyle.add(productStyleDTO);
				log.debug(list.get(0).getProduct().getName());
			}
		}
		ProductDetailDTO returnDTO = (ProductDetailDTO)MapperUtils.map(product, ProductDetailDTO.class);
		returnDTO.setList(listStyle);
		return returnDTO;
	}

	/**
	 * 根据请求page 和 page size 返回数据
	 * @param productListDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public ProductListDTO findByParam(@RequestBody ProductListDTO productListDTO){
		List<ProductListDTO> list = new ArrayList<ProductListDTO>();
		Integer offset = productListDTO.getOffset();
		Integer limit = productListDTO.getLimit();
		PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
		Page<Product> result = null;
		if(productListDTO.getMap() != null && !productListDTO.getMap().isEmpty()){
			result = productService.search(productListDTO.getMap(), pageRequest);
		}else {
			result = productService.search(null, pageRequest);
		}

		PageDTO pageDTO = new PageDTO();
		if(result != null && result.getSize() > 0 && result.getContent() != null && result.getContent().size() >0){
			List<Product> productList = result.getContent();
			for (Product product:productList){
				if(!product.getVisible()) continue;
				ProductListDTO productDTO = new ProductListDTO();
				BeanUtils.copyProperties(product,productDTO);
				list.add(productDTO);
			}
			pageDTO.setContent(list);
			pageDTO.setTotalPages(result.getTotalPages());
			pageDTO.setTotalElements(result.getTotalElements());
			log.debug("total page = " + result.getTotalPages() + "total element = " + result.getTotalElements());
			productListDTO.setPageDTO(pageDTO);
		}
		return productListDTO;
	}
    @RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_DEL, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ProductDetailResultDTO delProduct(@RequestBody ProductDetailDTO productDetailDTO){
    	 Product product = new Product();
    		ProductDetailResultDTO  productDetailResultDTO = new ProductDetailResultDTO();
         try {
			productService.delProduct(productDetailDTO.getId());
			productDetailResultDTO.setSuccess(true);
			return productDetailResultDTO;
		
		} catch (Exception e) {e.printStackTrace();
			productDetailResultDTO.setSuccess(false);
			productDetailResultDTO.setCode(200);
			productDetailResultDTO.setMessage("删除商品失败");
			return productDetailResultDTO;
		}
    }
    
    /**
     * 获取当前渠道下所有的product
     *
     * @return Page<ProductDTO>
     */
    @RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_CHANNEL_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ProductDTO findByChannelId(@RequestBody ProductDTO productDTO) {
        List<ProductDetailDTO> listProductList = new ArrayList<ProductDetailDTO>();
        int offset = productDTO.getOffset();
        int limit = productDTO.getLimit();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "account");
        Integer channelId = productDTO.getChannelId();
        Page<Product> result = productService.findByChannelId(channelId, pageRequest);
        PageDTO pageDTO = new PageDTO();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Product> listProduct = result.getContent();
            for (Product product:listProduct){
               ProductDetailDTO productDetailDTO = new ProductDetailDTO();
                BeanUtils.copyProperties(product, productDetailDTO);
                productDetailDTO.setChannelId(product.getChannel().getId());
                listProductList.add(productDetailDTO);
            }
            pageDTO.setContent(listProductList);
            pageDTO.setTotalPages(result.getTotalPages());
            pageDTO.setiTotalDisplayRecords(result.getTotalElements());
            pageDTO.setiTotalRecords(result.getTotalElements());
            productDTO.setPageDTO(pageDTO);
        }
        return productDTO;
    }
    
}
