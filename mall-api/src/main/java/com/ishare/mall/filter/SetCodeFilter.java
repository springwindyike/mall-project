package com.ishare.mall.filter;

import com.ishare.mall.core.status.Gender;
import com.ishare.mall.core.utils.convert.GenderConverter;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by YinLin on 2015/8/26.
 * Description :
 * Version 1.0
 */
public class SetCodeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ConvertUtils.register(new GenderConverter(), Gender.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
