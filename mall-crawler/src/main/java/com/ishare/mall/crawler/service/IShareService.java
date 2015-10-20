package com.ishare.mall.crawler.service;

import com.google.common.collect.Lists;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.product.FetchProductDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.crawler.model.BasePageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IShareService {

    private static final Logger log = LoggerFactory.getLogger(IShareService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.ishare.mall.rest.url}")
    private String bizAppUrl;

    @Transactional
    public boolean toSave(BasePageData data) throws ApiLogicException {
        ResponseEntity<Response<FetchProductDTO>> responseEntity;
        FetchProductDTO fetchProductDTO = new FetchProductDTO();

        log.debug("attributes={}\nintroImages={}\nphotos={}", data.getAttributes(), data.getIntroImages(), data.getPhotos());

        BeanUtils.copyProperties(data, fetchProductDTO, "attributes", "introImages", "photos");
        fetchProductDTO.setAttributes(data.getAttributes());
        fetchProductDTO.setIntroImages(Lists.newArrayList(data.getIntroImages()));
        fetchProductDTO.setPhotos(Lists.newArrayList(data.getPhotos()));
        log.debug("attributes={}\nintroImages={}\nphotos={}", fetchProductDTO.getAttributes(), fetchProductDTO.getIntroImages(), fetchProductDTO.getPhotos());

        boolean isSuccess = false;
        try {
            String url = buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_CRAWLER_ADD);
            log.debug("{}", url);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<FetchProductDTO>(fetchProductDTO), new ParameterizedTypeReference<Response<FetchProductDTO>>() {
            });

            log.debug("{}", responseEntity);
            isSuccess = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("创建失败", HttpStatus.BAD_REQUEST);
        }

        Response<FetchProductDTO> response = responseEntity.getBody();
        log.debug("{}", response);
        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("创建失败", HttpStatus.BAD_REQUEST);
        }

        return isSuccess;
    }

    @Transactional
    public boolean toSave(List<BasePageData> data) {
        boolean isSuccess = false;
        ResponseEntity<Response> responseEntity;
        List<FetchProductDTO> fetchProductDTOs = Lists.newArrayList();
        for (BasePageData basePageData : data) {
            FetchProductDTO fetchProductDTO = new FetchProductDTO();

            BeanUtils.copyProperties(basePageData, fetchProductDTO, "attributes", "introImages", "photos");
            fetchProductDTO.setAttributes(basePageData.getAttributes());
            fetchProductDTO.setIntroImages(Lists.newArrayList(basePageData.getIntroImages()));
            fetchProductDTO.setPhotos(Lists.newArrayList(basePageData.getPhotos()));
            fetchProductDTOs.add(fetchProductDTO);
        }
        try {
            HttpEntity<List<FetchProductDTO>> requestEntity = new HttpEntity<List<FetchProductDTO>>(fetchProductDTOs);
            String url = buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, "/crawler/list/add");
            log.debug("{}", url);
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Response.class);

            log.debug("{}", responseEntity);
            isSuccess = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("创建失败", HttpStatus.BAD_REQUEST);
        }

        Response<Object> response = responseEntity.getBody();
        log.debug("{}", response);
        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("创建失败", HttpStatus.BAD_REQUEST);
        }

        return isSuccess;
    }

    /**
     * 基础的path和apiPath
     *
     * @param moduleRequestMapping
     * @param apiRequestMapping
     * @return
     */
    protected String buildBizAppURI(String moduleRequestMapping, String apiRequestMapping) {
        return bizAppUrl + moduleRequestMapping + apiRequestMapping;
    }
}
