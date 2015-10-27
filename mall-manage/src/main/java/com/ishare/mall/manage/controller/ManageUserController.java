package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghao on 2015/10/27.
 */
@Controller
@RequestMapping(ManageURIConstant.ManageUser.REQUEST_MAPPING)
public class ManageUserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);
    @Autowired
    private RestTemplate resetTemplate;

    @RequestMapping(value = ManageURIConstant.ManageUser.REQUEST_MAPPING_GET_MANAGE_USER_List,method = RequestMethod.GET)
    public PageDTO getManageUserPage(HttpServletRequest request) throws Exception{
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setLimit(displayLength);
        manageUserDTO.setOffset(currentPage);
        ResponseEntity<Response<PageDTO<ManageUserDTO>>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(APPURIConstant.ManageUser.REQUEST_MAPPING, ManageURIConstant.ManageUser.REQUEST_MAPPING_GET_MANAGE_USER_List),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<ManageUserDTO>>>() {
                    });
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        Response<PageDTO<ManageUserDTO>> response = responseEntity.getBody();
        if (response == null || !response.isSuccess()){
            throw new Exception("get response error");
        }
        return response.getData();
    }

    @RequestMapping(value = ManageURIConstant.ManageUser.REQYEST_MAPPING_FORWARD_TO_MANAGE_USER_LIST)
    public String forward2ManageUsreList(){
        return ManageURIConstant.ManageUser.REQYEST_MAPPING_MANAGE_USER_LIST;
    }
}
