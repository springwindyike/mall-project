package com.ishare.mall.api.interceptor;

import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by YinLin on 2015/9/23.
 * Description : 处理逻辑异常
 * Version 1.0
 */
@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ApiLogicException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleUnexpectedLogicError(ApiLogicException ex) {
        Response response = new Response();
        response.setCode(ex.getStatus().value());
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return new ResponseEntity(response, ex.getStatus());
    }

    public ResponseEntity handleUnexpectedAccessTokenError() {
        return null;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleUnexpectedServerError(Exception e) {
        log.error(e.getMessage(), e);
        Response response = new Response();
        response.setCode(500);
        response.setSuccess(false);
        response.setMessage("Server error");
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public static Logger getLog() {
        return log;
    }
}
