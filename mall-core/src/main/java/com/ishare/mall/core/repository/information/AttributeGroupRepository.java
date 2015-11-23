package com.ishare.mall.core.repository.information;

import com.ishare.mall.core.model.product.Attribute;
import com.ishare.mall.core.model.product.AttributeGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by YinLin on 2015/8/10.
 * Description:
 * Version 1.0
 */
public interface AttributeGroupRepository extends JpaRepository<AttributeGroup, Integer>, JpaSpecificationExecutor {

}
