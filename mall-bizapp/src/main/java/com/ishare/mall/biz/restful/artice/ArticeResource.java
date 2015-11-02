package com.ishare.mall.biz.restful.artice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.cms.ArticleDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.cms.Article;
import com.ishare.mall.core.service.Artice.ArticeService;

/**
 * 
 * @author zhangzhaoxin
 *
 */
@RestController
@RequestMapping(APPURIConstant.Artice.REQUEST_MAPPING)
public class ArticeResource {
	private static final Logger log = LoggerFactory.getLogger(ArticeResource.class);

	@Autowired
	private ArticeService articeService;



	/**
	 * 查询所有日志信息
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Artice.REQUEST_MAPPING_ALL_Artice, method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json", "application/xml"})
	public Response findByAllArtice(@RequestBody ArticleDTO atricleDTO){
		List<ArticleDTO> atricleList = new ArrayList<>();
		int offset = atricleDTO.getOffset();
		int limit = atricleDTO.getLimit();
		Response response = new Response<>();
		PageRequest pageRequest = new PageRequest(offset - 1 <0 ? 0:offset - 1, limit <=0 ?15:limit,Sort.Direction.DESC,"id");
		Page<Article> result;
		try {
			result = articeService.findByAllArtice( pageRequest);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;

		}
		PageDTO<ArticleDTO> pageDTO = new PageDTO<ArticleDTO>();
		if (result !=null && result.getContent() !=null && result.getContent().size()>0 ) {
			List<Article> listAtricle =result.getContent();
			for (Article Artice:listAtricle) {
				ArticleDTO atricleDTOt = new ArticleDTO();
				BeanUtils.copyProperties(Artice, atricleDTOt);
				atricleList.add(atricleDTOt);
			}
			pageDTO.setContent(atricleList);
			pageDTO.setTotalPages(result.getTotalPages());
			pageDTO.setITotalDisplayRecords(result.getTotalElements());
			pageDTO.setITotalRecords(result.getTotalElements());
			response.setData(pageDTO);
		}
		return response;
	}





}
