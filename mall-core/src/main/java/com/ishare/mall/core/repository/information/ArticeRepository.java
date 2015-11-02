package com.ishare.mall.core.repository.information;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ishare.mall.core.model.cms.Article;

@SuppressWarnings("rawtypes")
public interface ArticeRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor {
//	@Query("select * ");
//	

}
