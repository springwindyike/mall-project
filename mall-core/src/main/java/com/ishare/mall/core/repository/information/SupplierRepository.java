package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.model.supplier.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/23.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor {
    @Query("SELECT  s from Supplier s where s.createTime >=?1")
    Page<Channel> findThisWeek(Date date,Pageable pageable);
    @Query("SELECT s FROM Supplier s")
    Page<Supplier> findAll(Pageable pageable);
    @Query("SELECT  count(*) from Supplier")
    Long findCount();
    @Query("SELECT Count(*) from Supplier s where s.createTime >= ?1")
    Long findThisWeekCount(Date date);

}
