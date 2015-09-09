package com.ishare.mall.core.utils.page;

import com.google.common.collect.Lists;
import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.object.BaseObject;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;


/**
 * Created by YinLin on 2015/8/10.
 * Description: 提供page的转换
 * Version 1.0
 */
public class PageUtils {
	/**
	 * 提供实体和对外开放api字段发放
	 *
	 * @param page     实体page
	 * @param pageable 分页page
	 * @param clazzE   需要转换成的DTO
	 * @param <T>      实体
	 * @param <E>      DTO
	 * @return DTO分页
	 */
	public static <T extends BaseObject, E extends BaseObject> Page<E> mapper(Page<T> page, Pageable pageable, Class<E> clazzE) {
		List<T> element = page.getContent();
		List<E> resultElement = Lists.newArrayList();
		if (element != null && element.size() > 0) {
			resultElement = (List<E>) MapperUtils.mapAsList(element, clazzE);
		}
		Page<E> result = new PageImpl<E>(resultElement, pageable, page.getTotalElements());
		return result;
	}

	/**
	 * 获取page
	 *
	 * @param request
	 * @param direction
	 * @param properties
	 * @return
	 */
	public static PageRequest getPageRequest(HttpServletRequest request, Sort.Direction direction, String... properties) {
		int offset = 1;
		int limit = 15;
		if (StringUtils.isNotEmpty(request.getParameter(OFFSET))) {
			offset = Integer.valueOf(request.getParameter(OFFSET));
			if (offset <= 0) {
				offset = 1;
			}
		}
		if (StringUtils.isNotEmpty(request.getParameter(LIMIT))) {
			limit = Integer.valueOf(request.getParameter(LIMIT));
			if (limit <= 0) {
				limit = 15;
			}
		}
		return new PageRequest(offset - 1, limit, direction, properties);
	}

	/**
	 * 强制转换为DTO形式page
	 * @param page
	 * @param clazzE
	 * @param <T>
	 * @param <E>
	 * @return
	 */
	public static <T extends BaseObject, E extends GenericDTO> PageDTO<E> mapper(Page<T> page, Class<E> clazzE) {
		PageDTO<E> pageDTO = new PageDTO();
		List<T> element = page.getContent();
		List<E> resultElement = Lists.newArrayList();
		if (element != null && element.size() > 0) {
			resultElement = (List<E>) MapperUtils.mapAsList(element, clazzE);
		}
		pageDTO.setContent(resultElement);
		pageDTO.setPageSize(page.getSize());
		pageDTO.setTotalElements(page.getTotalElements());
		pageDTO.setTotalPages(page.getTotalPages());
		return pageDTO;
	}

}
