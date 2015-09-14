package com.ishare.mall.center.method;

import com.ishare.mall.center.annoation.PageRequest;
import com.ishare.mall.common.base.dto.page.PageRequestDTO;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by YinLin on 2015/9/14.
 * Description :
 * Version 1.0
 */
public class PageRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(PageRequest.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //PageRequest pageRequest = parameter.getParameterAnnotation(PageRequest.class);
        return this.buildPageRequestDTO(webRequest);
    }

    /**
     * 组件当前页码
     * @param webRequest
     * @return
     */
    private PageRequestDTO buildPageRequestDTO(NativeWebRequest webRequest) {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        Integer pageSize = 0;
        try {
            pageSize = Integer.valueOf(webRequest.getParameter("iDisplayLength"));
        } catch (Exception e) {
            pageSize = 15;
        }
       pageRequestDTO.setPageSize(pageSize);

        Integer currentPage = 0;
        if (null != webRequest.getParameter("iDisplayStart")) {
            try {
                currentPage = (Integer.valueOf(webRequest.getParameter("iDisplayStart")) / 10) + 1;
            } catch (Exception e) {
                currentPage = 1;
            }
        }
        pageRequestDTO.setCurrentPage(currentPage);
        return pageRequestDTO;
    }
}
