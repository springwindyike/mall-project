package com.ishare.mall.biz.interceptor;

import com.ishare.mall.common.base.exception.BaseException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by YinLin on 2015/9/22.
 * Description :
 * Version 1.0
 */
@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Response handleUnexpectedServerError(BaseException ex) {
        log.error(ex.getMessage(), ex);
        Response response = new Response();
        response.setCode(500);
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return response;
    }

    public static Logger getLog() {
        return log;
    }
}
