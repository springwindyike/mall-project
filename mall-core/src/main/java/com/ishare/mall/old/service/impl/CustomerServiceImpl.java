package com.ishare.mall.old.service.impl;

import com.ishare.mall.old.model.Customer;
import com.ishare.mall.old.repository.CustomerRepository;
import com.ishare.mall.old.service.CustomerService;
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
 * Created by dongqi on 15/7/14.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    public boolean regist(Customer customer) {
    	Boolean flag=true;
    	Customer save = customerRepository.save(customer);
    	if(save==null){
    		flag=false;
    		return flag;
    	}
        return flag;
    }
    
    /**
     * 根据登录模糊查询
     */
    public List<Customer> findByAccount(String account){
    	  List<Customer> customers = customerRepository.findByAccount(account);
    	return customers;
    }
    
    /**
     * 根据登录名查询用户，用户名唯一
     */
    public Customer findOneByAccount(String account){
    	List<Customer> customers = findByAccount(account);
    	for (Customer customer : customers) {
			if(account.equals(customer.getAccount())){
				return customer;
			}
		}
    	return null;
    }
  
    @Override
    public Page<Customer> search(PageRequest pageRequest) {
        return customerRepository.findAll(pageRequest);
    }

    @Override
    public Page<Customer> search(Map<String, Object> searchParams, PageRequest pageRequest) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Customer> spec = DynamicSpecifications.bySearchFilter(filters.values(), Customer.class);
        Page<Customer> page = customerRepository.findAll(spec, pageRequest);
        log.debug("filters: {}, total: {}, content: {}", filters, page.getTotalElements(), page.getContent());
        return page;
    }

    @Override
    public Customer get(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }
}
