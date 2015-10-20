package com.ishare.mall.core.service.product.impl;

import com.google.common.collect.Lists;
import com.ishare.mall.common.base.dto.product.FetchProductDTO;
import com.ishare.mall.common.base.enumeration.ValueType;
import com.ishare.mall.core.exception.OrderServiceException;
import com.ishare.mall.core.exception.ProductServiceException;
import com.ishare.mall.core.model.product.*;
import com.ishare.mall.core.repository.information.*;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.repository.product.ProductRepository;
import com.ishare.mall.core.repository.product.ProductTypeRepository;
import com.ishare.mall.core.service.information.AttributeService;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by liaochenglei on 2015/9/22.
 * Description:
 * Version 1.0
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ChannelRepository channelRepository;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private FetchRepository fetchRepository;

	@Autowired
	private ProductAttributeRepository productAttributeRepository;
	@Autowired
	private ProductIntroImageRepository productIntroImageRepository;
	@Autowired
	private ProductPhotoImageRepository productPhotoImageRepository;
	@Override
    public Page<Product> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Product> spec = DynamicSpecifications.bySearchFilter(filters == null ? null : filters.values(), Product.class);
        Page<Product> page = productRepository.findAll(spec, pageRequest);
        log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    @Override
    public Product findOne(Integer id) {
        return productRepository.findOne(id);
    }

    public static Logger getLog() {
        return log;
    }

	@Override
	public Product findByCode(String code) {
    List<Product> products = productRepository.findByCode(code);
    if (products == null || products.size() == 0) return null;
    return products.get(0);
	}

	@Override
	public void saveProduct(Product product) {
		try {
			// TODO Auto-generated method stub
			/*productTypeRepository.save(product.getType());
			memberRepository.save(product.getCreateBy());
			brandRepository.save(product.getBrand());
			channelRepository.save(product.getChannel());*/
			productRepository.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductServiceException("产品保存失败");}
	}

	@Override
	public void updateProduct(Product product) {
		try {
			productTypeRepository.save(product.getType());
			memberRepository.save(product.getCreateBy());
			brandRepository.save(product.getBrand());
			channelRepository.save(product.getChannel());
			productRepository.save(product);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductServiceException("产品更新失败");}
	}

	@Override
	public Product processor(FetchProductDTO fetchProductDTO) throws ProductServiceException {
		Product product = initProduct(fetchProductDTO);
		List<ProductAttribute> productAttributes = Lists.newArrayList();
		List<ProductIntroImage> productIntroImages = Lists.newArrayList();
		List<ProductPhotoImage> productPhotoImages = Lists.newArrayList();

		//属性逻辑处理
		if (fetchProductDTO.getAttributes() != null && fetchProductDTO.getAttributes().size() > 0) {
			for (String name : fetchProductDTO.getAttributes().keySet()) {
				Attribute attribute = attributeService.findByName(name);
				if (attribute == null) {
					attribute = new Attribute();
					attribute.setName(name);
					attribute.setDescription(name);
					attributeService.save(attribute);
				}
				ProductAttribute productAttribute = new ProductAttribute();
				productAttribute.setProduct(product);
				productAttribute.setName(name);
				productAttribute.setAttribute(attribute);
				productAttribute.setType(ValueType.STRING);
				productAttribute.setValue(fetchProductDTO.getAttributes().get(name));
				productAttributes.add(productAttribute);
			}
		}


		//图片描述逻辑处理
		if (fetchProductDTO.getIntroImages() != null && fetchProductDTO.getIntroImages().size() > 0) {
			log.debug("IntroImages.size() : " + fetchProductDTO.getIntroImages().size());
			for (String url : fetchProductDTO.getIntroImages()) {
				ProductIntroImage productIntroImage = new ProductIntroImage();
				productIntroImage.setUrl(url);
				productIntroImage.setProduct(product);
				productIntroImages.add(productIntroImage);
			}
		}

		//商品图片处理
		if (fetchProductDTO.getPhotos() != null && fetchProductDTO.getPhotos().size() > 0) {
			product.setDefaultImageUrl(fetchProductDTO.getPhotos().get(0));
			log.debug("photoImages.size() : " + fetchProductDTO.getPhotos().size());
			for (String url : fetchProductDTO.getPhotos()) {
				ProductPhotoImage productPhotoImage = new ProductPhotoImage();
				productPhotoImage.setUrl(url);
				productPhotoImage.setProduct(product);
				productPhotoImages.add(productPhotoImage);
			}
		}

		//商品第三方类别，标签处理
		List<Fetch> fetches = fetchRepository.findByTag(fetchProductDTO.getTag());
		Fetch fetch = null;
		if (fetches.size() > 0 ) {
			fetch = fetches.get(0);
		} else {
			fetch = new Fetch();
			fetch.setTag(fetchProductDTO.getTag());
			fetchRepository.save(fetch);
		}

		product.setFetch(fetch);

		//保存商品
		productRepository.save(product);
		//保存商品属性
		productAttributeRepository.save(productAttributes);
		//保存商品描述图片
		productIntroImageRepository.save(productIntroImages);
		//保存商品相册图片
		productPhotoImageRepository.save(productPhotoImages);

		return product;
	}

	private Product initProduct(FetchProductDTO fetchProductDTO) {
		Product product = new Product();
		product.setName(fetchProductDTO.getName());
		product.setCode(fetchProductDTO.getCode());
		float basePrice = 0;
		float sellPrice = 0;
		float marketPrice = 0;
		try {
			if (StringUtils.isNotEmpty(fetchProductDTO.getPriceText()))
				basePrice = Float.parseFloat(fetchProductDTO.getPriceText());
		} catch (NumberFormatException e) {}
		try {
			if (StringUtils.isNotEmpty(fetchProductDTO.getPriceText()))
				sellPrice = Float.parseFloat(fetchProductDTO.getPriceText());
		} catch (NumberFormatException e) {}
		try {
			if (StringUtils.isNotEmpty(fetchProductDTO.getPriceOriginText()))
				marketPrice = Float.parseFloat(fetchProductDTO.getPriceOriginText());
		} catch (NumberFormatException e) {}
		product.setBasePrice(basePrice);
		product.setSellPrice(sellPrice);
		product.setMarketPrice(marketPrice);
		product.setStock("Y".equals(fetchProductDTO.getStock()));
		product.setSelf(false);
		product.setLink(fetchProductDTO.getLink());
		product.setTag(fetchProductDTO.getTag());
		product.setOriginCode(fetchProductDTO.getCode());
		//TODO 第三方注入
		return product;
	}

	//删除商品
	@Override
	public void delProduct(Integer id) {
		try {
			productRepository.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductServiceException("产品删除失败");
		}
	}

	@Override
	public Page<Product> findByChannelId(Integer channelId,PageRequest pageRequest) {
		try {
			Page<Product> page = productRepository.findByChannelId(channelId,pageRequest);
			return page;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ProductServiceException("查询产品失败");
		}
	}

	@Override
	public Page<Product> findBycondition(Integer productId, Integer channelId,
			PageRequest pageRequest) throws ProductServiceException {
		try {
			return productRepository.findBycondition(productId, channelId, pageRequest);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new OrderServiceException("搜索产品失败");
		}
	}
}
