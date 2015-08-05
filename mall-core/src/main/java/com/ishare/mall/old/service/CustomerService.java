package com.ishare.mall.old.service;

import com.ishare.mall.old.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by dongqi on 15/7/14.
 */
public interface CustomerService {

    Page<Customer> search(PageRequest pageRequest);

    Page<Customer> search(Map<String, Object> searchParams, PageRequest pageRequest);

    boolean regist(Customer customer);

    List<Customer> findByAccount(String account);

    Customer findOneByAccount(String account);

    Customer get(Long id);

    Customer update(Customer customer);

}
