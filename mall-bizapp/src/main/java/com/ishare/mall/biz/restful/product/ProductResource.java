package com.ishare.mall.biz.restful.product;


import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.*;
import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.enumeration.MemberType;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.exception.ProductServiceException;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.member.Member;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.model.product.ProductStyle;
import com.ishare.mall.core.model.product.ProductType;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.service.product.ProductStyleService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
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

import java.util.ArrayList;
import java.util.List;

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
    public Response saveProduct(@RequestBody ProductDetailDTO productDetailDTO){
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
									log.error(e.getMessage(), e);
	            Response response = new Response();
	            response.setMessage("系统错误");
	            response.setSuccess(false);
	            return response;
	            }
    			 Response response = new Response();
    			return response;
}

    @RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_UPDATE, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response updateProduct(@RequestBody ProductDetailDTO productDetailDTO){
        Product product = new Product();
        Response response = new Response();
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
    			product.setId(productDetailDTO.getId());
    			try {
					productService.saveProduct(product);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					response.setMessage("系统错误");
					response.setSuccess(false);
					return response;

				}
	return response;
}
	/**
	 * 根据商品ID 查询商品详细信息
	 * @param productDetailDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_ID, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json",},
            consumes = {"application/json",})
	public Response findByID(@RequestBody ProductDetailDTO productDetailDTO){
		Product product;
		List<ProductStyle> list;
		Response response = new Response();
		try {
			product = productService.findOne(productDetailDTO.getId());
			list = productStyleService.findByProductStyle(productDetailDTO.getId());
		} catch (ProductServiceException e) {
				log.error(e.getMessage(), e);
				response.setMessage("系统错误");
				response.setSuccess(false);
				return response;
		}
		if(product == null || !product.getVisible()){
			response.setData(null);
			return response;
		}
		List<ProductStyleDTO> listStyle = new ArrayList<ProductStyleDTO>();
		if(list != null && list.size()>0){
			for(ProductStyle productStyle:list){
				ProductStyleDTO productStyleDTO = new ProductStyleDTO();
				BeanUtils.copyProperties(productStyle, productStyleDTO);
				listStyle.add(productStyleDTO);
			}
		}
		ProductDetailDTO returnDTO = (ProductDetailDTO)MapperUtils.map(product, ProductDetailDTO.class);
		returnDTO.setList(listStyle);
		response.setData(returnDTO);
		return response;
	}

	/**
	 * 根据商品的code查询详细信息
	 * @param productDetailDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_CODE, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json",},
			consumes = {"application/json",})
	public Response findByCode(@RequestBody ProductDetailDTO productDetailDTO){
		Response response = new Response();
		Product product = productService.findByCode(productDetailDTO.getCode());
		if(product == null || !product.getVisible()){
			response.setMessage("商品不存在");
			response.setData(product);
			return response;
		}
		List<ProductStyle> list = productStyleService.findByProductStyle(product.getId());
		List<ProductStyleDTO> listStyle = new ArrayList<ProductStyleDTO>();
		try{
			if(list != null && list.size()>0){
				for(ProductStyle productStyle:list){
					ProductStyleDTO productStyleDTO = new ProductStyleDTO();
					BeanUtils.copyProperties(productStyle, productStyleDTO);
					listStyle.add(productStyleDTO);
				}
			}
			ProductDetailDTO returnDTO = (ProductDetailDTO)MapperUtils.map(product, ProductDetailDTO.class);
			returnDTO.setList(listStyle);
			response.setData(returnDTO);
		}catch (ProductServiceException e){
			log.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	/**
	 * 根据请求page 和 page size 返回数据
	 * @param productListDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_PARAM, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json",},
			consumes = {"application/json",})
	public Response findByParam(@RequestBody ProductListDTO productListDTO){
		List<ProductListDTO> list = new ArrayList<ProductListDTO>();
		Integer offset = productListDTO.getOffset();
		Integer limit = productListDTO.getLimit();
		PageDTO pageDTO = new PageDTO();
		Response response = new Response();
		Page<Product> result = null;
		try{
			PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
			if(productListDTO.getMap() != null && !productListDTO.getMap().isEmpty()){
				result = productService.search(productListDTO.getMap(), pageRequest);
			}else {
				result = productService.search(null, pageRequest);
			}
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
			response.setData(productListDTO);
		}catch (ProductServiceException e){
			log.error(e.getMessage());
			response.setMessage(e.getMessage());
			response.setSuccess(false);
		}


		return response;
	}
    @RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_DEL, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public Response delProduct(@RequestBody ProductDetailDTO productDetailDTO){
    	 Product product = new Product();
    	Response response = new Response();
         try {
			productService.delProduct(productDetailDTO.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
         return response;
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
    public Response findByChannelId(@RequestBody ProductDTO productDTO) {
        List<ProductDTO> listProductList = new ArrayList<>();
        int offset = productDTO.getOffset();
        int limit = productDTO.getLimit();
        Response response = new Response();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit,Sort.Direction.DESC, "id");
        Integer channelId = productDTO.getChannelId();
        Page<Product> result;
		try {
			result = productService.findByChannelId(channelId, pageRequest);
		} catch (ProductServiceException e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
        PageDTO<ProductDTO> pageDTO = new PageDTO<ProductDTO>();
        if(result != null && result.getContent() != null && result.getContent().size()>0){
            List<Product> listProduct = result.getContent();
         for (Product product:listProduct){
               //ProductDetailDTO productDetailDTO = new ProductDetailDTO();
			 ProductDTO productDetailDTO = new ProductDTO();
                BeanUtils.copyProperties(product, productDetailDTO);
                productDetailDTO.setChannelId(product.getChannel().getId());
                //productDetailDTO.setBrandId(product.getBrand().getId());
                //productDetailDTO.setCreateByAccount(product.getCreateBy().getAccount());
                //productDetailDTO.setUpdateByAccount(product.getUpdateBy().getAccount());
                //productDetailDTO.setTypeId(product.getType().getId());
                listProductList.add(productDetailDTO);
            }
            pageDTO.setContent(listProductList);
            pageDTO.setTotalPages(result.getTotalPages());
            pageDTO.setITotalDisplayRecords(result.getTotalElements());
            pageDTO.setITotalRecords(result.getTotalElements());
            response.setData(pageDTO);
        }
        return response;
    }

	/**
	 * 获取当前渠道下所有的product
	 *
	 * @return Page<ProductDTO>
	 */
	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_CRAWLER_ADD, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json"})
	public Response<ProductDetailDTO> add(@RequestBody FetchProductDTO fetchProductDTO) {
		Response response = new Response();
		response.setData((ProductDetailDTO) MapperUtils.map(productService.processor(fetchProductDTO), ProductDetailDTO.class));
		return response;
	}

	@RequestMapping(value = APPURIConstant.Product.REQUEST_MAPPING_CRAWLER_LIST_ADD, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json"})
	public Response add(@RequestBody List<FetchProductDTO> fetchProductDTOs) {
		Response response = new Response();
		if (fetchProductDTOs != null && fetchProductDTOs.size() > 0)
			for (FetchProductDTO fetchProductDTO : fetchProductDTOs)
				productService.processor(fetchProductDTO);
		response.setData("true");
		return response;
	}

    
}
