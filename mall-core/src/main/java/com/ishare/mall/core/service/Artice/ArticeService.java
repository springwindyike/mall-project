package com.ishare.mall.core.service.Artice;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ishare.mall.core.exception.ArticeServiceException;
import com.ishare.mall.core.model.cms.Article;

/**
 * 
 * @author zhangzhaoxin
 *系统日志service
 */
public interface ArticeService {
	
	 /**
     * 查看所有系统日志
     * @param channelId
     * @param pageRequest
     * @return
     */
    Page<Article> findByAllArtice(PageRequest pageRequest) throws ArticeServiceException;
	
	
			/**
			 * 根据条件查询系统信息
			 * @param searchParams
			 * @param pageRequest
			 * @return
			 */
    public abstract Page<Article> search(Map<String, Object> searchParams, PageRequest pageRequest) throws ArticeServiceException;
    
    /**
     * 根据系统日志id删除日志信息
     * @param id
     */
    public void delArtice(Integer id) throws ArticeServiceException;
}
