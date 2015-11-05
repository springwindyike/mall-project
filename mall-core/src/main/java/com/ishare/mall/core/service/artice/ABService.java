package com.ishare.mall.core.service.artice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.exception.ABServiceException;
import com.ishare.mall.core.model.cms.AB;

public interface ABService {
	
	Page<AB> findByAllAB(PageRequest pageRequest) throws ABServiceException;
	

}
