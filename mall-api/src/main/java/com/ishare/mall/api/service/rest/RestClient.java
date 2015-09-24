package com.ishare.mall.api.service.rest;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Created by YinLin on 2015/9/23.
 * Description :
 * Version 1.0
 */
public interface RestClient {
    // POST
    <T>ResponseEntity<T> post(String url, Object request, Class<T> responseType, Object... uriVariables);

    <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables);



}
