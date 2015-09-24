package com.ishare.mall.api.service.rest.impl;

import com.ishare.mall.api.service.rest.RestClient;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Created by YinLin on 2015/9/23.
 * Description :
 * Version 1.0
 */
public class RestClientImpl implements RestClient {

    @Override
    public <T> ResponseEntity<T> post(String url, Object request, Class<T> responseType, Object... uriVariables) {
        return null;
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
        return null;
    }
}
