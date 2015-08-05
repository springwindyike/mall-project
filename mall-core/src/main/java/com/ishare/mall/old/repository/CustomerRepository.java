package com.ishare.mall.old.repository;

import com.ishare.mall.old.model.Customer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dongqi on 15/7/13.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor {

	@Query(value = "select c from Customer c where c.account like ?1%")
    Page<Customer> findByAccount(String account, Pageable pageable);
    @Query(value = "select c from Customer c where c.account like ?1%")
    public List<Customer> findByAccount(String account);
}
