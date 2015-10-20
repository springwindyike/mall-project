package com.ishare.mall.manage.service.manageuser.impl;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.exception.web.api.ApiLogicException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.service.base.BaseService;

import com.ishare.mall.manage.service.manageuser.ManageUserService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Zhangzhaoxin on 2015/10/19.
 * Description :
 * Version 1.0
 */
@Service
public class ManageUserServiceImpl extends BaseService implements ManageUserService {

    private static final Logger log = LoggerFactory.getLogger(ManageUserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private Cache<String, CurrentManageUserDTO> cache;

    @Autowired
    public ManageUserServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("currentManageUserCache");
    }

    @Override
    public CurrentManageUserDTO getCurrentManageUser(String username) {
        CurrentManageUserDTO currentManageUserDTO = cache.get(username);
        if (currentManageUserDTO == null) {
            currentManageUserDTO = new CurrentManageUserDTO();
            ManageUserDTO manageUserDTO = this.findByUsername(username);
            currentManageUserDTO.setUsername(manageUserDTO.getUsername());
            currentManageUserDTO.setChannelId(manageUserDTO.getChannelId());
            currentManageUserDTO.setName(manageUserDTO.getName());
            currentManageUserDTO.setId(manageUserDTO.getId());
            currentManageUserDTO.setChannelName(manageUserDTO.getChannelName());
            currentManageUserDTO.setGender(manageUserDTO.getGender());
            this.cache.put(username, currentManageUserDTO);
        }
        return currentManageUserDTO;
    }

    @Override
    public ManageUserDTO findByUsername(String username) {
        ResponseEntity<Response<ManageUserDTO>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    this.buildBizAppURI(APPURIConstant.ManageUser.REQUEST_MAPPING, APPURIConstant.ManageUser.REQUEST_MAPPING_GET_BY_USERNAME),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Response<ManageUserDTO>>() {
                    }, username);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ApiLogicException("用户未找到", HttpStatus.NOT_FOUND);
        }
        Response<ManageUserDTO> response = responseEntity.getBody();
        if (!response.isSuccess() || response.getData() == null) {
            throw new ApiLogicException("用户未找到", HttpStatus.NOT_FOUND);
        }
        return response.getData();
    }
}
