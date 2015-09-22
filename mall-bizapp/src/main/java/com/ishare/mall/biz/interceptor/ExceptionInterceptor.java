package com.ishare.mall.biz.interceptor;

import com.ishare.mall.common.base.general.Response;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
public class ExceptionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(ExceptionInterceptor.class);
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("hahahha");
        if (ex != null) {
            System.out.println("异常出现啦");
            ex.printStackTrace();
            log.error(ex.getMessage());

        Response responseBody = new Response();
        responseBody.setSuccess(false);
        responseBody.setCode(101);
        responseBody.setMessage("错误");
        JSONObject jsonObject = JSONObject.fromObject(responseBody);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.setContentType("application/xml; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //out.append(jsonObject.toString());

            out.print(responseBody);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        }
    }

    public static Logger getLog() {
        return log;
    }
}
