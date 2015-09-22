package com.ishare.mall.core.service.product.impl;

import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.repository.information.BrandRepository;
import com.ishare.mall.core.repository.information.ChannelRepository;
import com.ishare.mall.core.repository.member.MemberRepository;
import com.ishare.mall.core.repository.product.ProductRepository;
import com.ishare.mall.core.repository.product.ProductTypeRepository;
import com.ishare.mall.core.service.product.ProductService;
import com.ishare.mall.core.utils.filter.DynamicSpecifications;
import com.ishare.mall.core.utils.filter.SearchFilter;
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
 * Created by YinLin on 2015/7/30.
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
    private ProductTypeRepository productTypeResponsitory;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ChannelRepository channelRepository;
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
		// TODO Auto-generated method stub
		productTypeResponsitory.save(product.getType());
		memberRepository.save(product.getCreateBy());
		brandRepository.save(product.getBrand());
		channelRepository.save(product.getChannel());
		productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		productTypeResponsitory.save(product.getType());
		memberRepository.save(product.getCreateBy());
		brandRepository.save(product.getBrand());
		channelRepository.save(product.getChannel());
		productRepository.save(product);
	}
	//删除商品
	@Override
	public void delProduct(Integer id) {
		productRepository.delete(id);
	}

	@Override
	public Page<Product> findByChannelId(Integer channelId,PageRequest pageRequest) {
		try {
			Page<Product> page = productRepository.findByChannelId(channelId,
					pageRequest);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
				return null;
}
	}
}
