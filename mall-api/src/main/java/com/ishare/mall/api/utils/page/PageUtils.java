package com.ishare.mall.api.utils.page;

import com.google.common.collect.Lists;
import com.ishare.mall.common.base.dto.page.PageRequestDTO;
import com.ishare.mall.common.base.object.BaseObject;

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
     * @param page 实体page
     * @param pageable 分页page
     * @param clazzE 需要转换成的DTO
     * @param <T> 实体
     * @param <E> DTO
     * @return DTO分页
     */
    public static <T extends BaseObject, E extends BaseObject> Page<E> mapper(Page<T> page, Pageable pageable, Class<E> clazzE) {
        List<T> element = page.getContent();
        List<E> resultElement = Lists.newArrayList();
        if (element != null && element.size() > 0) {
         // resultElement = (List<E>) MapperUtils.mapAsList(element, clazzE);
        }
        Page<E> result = new PageImpl<E>(resultElement, pageable, page.getTotalElements());
        return result;
    }

    /**
     * 获取page
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
     * 获取传输DTO
     * @param request
     * @param properties
     * @return
     */
    public static PageRequestDTO getPageRequestDTO(HttpServletRequest request, String properties) {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
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
        pageRequestDTO.setPageSize(limit);
        pageRequestDTO.setCurrentPage(offset - 1);
        pageRequestDTO.setOrder(properties);
        return pageRequestDTO;
    }

    public static PageRequestDTO getPageRequestDTO(int offset, int limit, String properties) {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        if (offset <= 0) {
            offset = 1;
        }


        if (limit <= 0 || limit > 100) {
            limit = 15;
        }

        pageRequestDTO.setPageSize(limit);
        pageRequestDTO.setCurrentPage(offset - 1);
        pageRequestDTO.setOrder(properties);
        return pageRequestDTO;
    }

}
