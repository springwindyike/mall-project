package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.BaseConstant;
import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.manageuser.ManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.common.base.enumeration.UserType;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.annoation.CurrentManageUser;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.form.ManageForm;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by wanghao on 2015/10/27.
 */
@Controller
@RequestMapping(ManageURIConstant.ManageUser.REQUEST_MAPPING)
public class ManageUserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);
    @Autowired
    private RestTemplate resetTemplate;

    /**
     * 分页查询manage user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getManageUserList",method = RequestMethod.GET)
    @ResponseBody
    public PageDTO<ManageUserDTO> getManageUserPage(HttpServletRequest request) throws Exception{
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setLimit(displayLength);
        manageUserDTO.setOffset(currentPage);
        ResponseEntity<Response<PageDTO<ManageUserDTO>>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_GET_MANAGE_USER_List),
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

    /**
     * 跳转到分页查询页面
     * @return
     */
    @RequestMapping(value = ManageURIConstant.ManageUser.REQYEST_MAPPING_FORWARD_TO_MANAGE_USER_LIST)
    public String forward2ManageUsreList(){
        return ManageURIConstant.ManageUser.REQYEST_MAPPING_MANAGE_USER_LIST;
    }

    /**
     * 设置manage user是否可用
     * @param status
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "changeStatus/{status}/{id}")
    public boolean changeStatus(@NotEmpty @PathVariable("status") Integer status,@NotEmpty @PathVariable("id") Integer id,Model model) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        if(status == 0){
            manageUserDTO.setUse(false);
        }else {
            manageUserDTO.setUse(true);
        }
        manageUserDTO.setId(id);
        ResponseEntity<Response<ManageUserDTO>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_CHANGE_STATUS),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ManageUserDTO>>() {});
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception("get response error");
        }
        Response<ManageUserDTO> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            logger.error("get response error");
            throw new Exception("get response error.");
        }
        model.addAttribute("manageUserDTO",response.getData());
        return true;
    }

    /**
     * 跳转到update页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "forward2UpdatePage/{id}",method = RequestMethod.GET)
    public String forward2UpdatePage(@NotEmpty @PathVariable("id") Integer id,Model model) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setId(id);
        ResponseEntity<Response<ManageUserDTO>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_FIND_BY_ID),
                HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ManageUserDTO>>() {});
        Response<ManageUserDTO> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            throw new Exception("get response error.");
        }
        model.addAttribute("manageUserDTO",response.getData());
        return ManageURIConstant.ManageUser.REQUEST_MAPPING_FORWARD_TO_UPDATE_PAGE;
    }

    /**
     * 重置密码
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "resetPassword/{id}",method = RequestMethod.POST)
    public boolean resetPassword(@NotEmpty @PathVariable("id") Integer id) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setId(id);
        ResponseEntity<Response<ManageUserDTO>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_RESET_PASSWORD),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ManageUserDTO>>() {});
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception("get response error");
        }
        Response<ManageUserDTO> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            return false;
        }
        return true;
    }

    /**
     * 添加manage user
     * @param manageForm
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public boolean add(ManageForm manageForm,@CurrentManageUser CurrentManageUserDTO currentManageUserDTO) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        BeanUtils.copyProperties(manageForm, manageUserDTO);
        manageUserDTO.setCreateBy(currentManageUserDTO.getUsername());
        switch (manageForm.getUserType()){
            case BaseConstant.ADMIN:
                manageUserDTO.setUserType(UserType.ADMIN);
                break;
            case BaseConstant.SELF:
                manageUserDTO.setUserType(UserType.SELF);
                break;
            case BaseConstant.CLERK:
                manageUserDTO.setUserType(UserType.CLERK);
                break;
            case BaseConstant.MEMBER:
                manageUserDTO.setUserType(UserType.MEMBER);
                break;
        }
        ResponseEntity<Response> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_SAVE),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {});
        }catch (Exception e){
            logger.error("has error", e.getStackTrace());
            throw new Exception("get response error.");
        }
        Response response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            logger.error("respons is null");
            throw new Exception("get response error.");
        }
        return true;
    }

    /**
     * 跳转到添加用户页面
     * @return
     */
    @RequestMapping(value = "forward2AddPage",method = RequestMethod.GET)
    public String forward2AddPage(){
        return ManageURIConstant.ManageUser.REQUEST_MAPPING_FORWARD_TO_ADD_PAGE;
    }
    /**
     * 修改manage user
     * @param manageForm
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public boolean update(ManageForm manageForm,@CurrentManageUser CurrentManageUserDTO currentManageUserDTO) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        BeanUtils.copyProperties(manageForm, manageUserDTO);
        ResponseEntity<Response> responseEntity = null;
        manageUserDTO.setUpdateBy(currentManageUserDTO.getUsername());
        switch (manageForm.getUserType()){
            case BaseConstant.ADMIN:
                manageUserDTO.setUserType(UserType.ADMIN);
                break;
            case BaseConstant.SELF:
                manageUserDTO.setUserType(UserType.SELF);
                break;
            case BaseConstant.CLERK:
                manageUserDTO.setUserType(UserType.CLERK);
                break;
            case BaseConstant.MEMBER:
                manageUserDTO.setUserType(UserType.MEMBER);
                break;
        }
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_UPDATE),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {});
        }catch (Exception e){
            logger.error("has error", e.getStackTrace());
            throw new Exception("get response error.");
        }
        Response response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            logger.error("respons is null");
            throw new Exception("get response error.");
        }
        return true;
    }

    /**
     * 检查账号是否已经存在
     * @param param
     * @return
     */
    @RequestMapping(value = "checkLoginAccount",method = RequestMethod.POST)
    @ResponseBody
    public ValidformRespDTO checkLoginAccount(@RequestParam("name") String name,@RequestParam("param") String param){
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setUsername(param);
        ResponseEntity<Response<ValidformRespDTO>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_CHECK_NAME),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ValidformRespDTO>>() {});
        }catch (Exception e){
            logger.error("has error", e.getStackTrace());
            ValidformRespDTO validformRespDTO = new ValidformRespDTO();
            validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_FAIL_INFO);
            validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_FAIL_STATUS);
            return validformRespDTO;
        }
        Response<ValidformRespDTO> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            logger.error("respons is null");
            ValidformRespDTO validformRespDTO = new ValidformRespDTO();
            validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_FAIL_INFO);
            validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_FAIL_STATUS);
            return validformRespDTO;
        }
        return response.getData();
    }

    /**
     * 改密码
     * @param currentManageUser
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    public boolean  changePassword(@CurrentManageUser CurrentManageUserDTO currentManageUser,@NotEmpty @RequestParam("password") String password,
                                   @RequestParam("username") String username,@RequestParam("oldPassword") String oldPassword) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setPassword(oldPassword+","+password);
        manageUserDTO.setUsername(username);
        ResponseEntity<Response> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try{
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_CHANGE_PASSWORD),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {});
        }catch (Exception e){

        }
        Response response = responseEntity.getBody();
        if (response == null) throw new Exception("get response error.");
        if(!response.isSuccess()) return false;
        return true;
    }

    /**
     * 跳转到修改密码页面
     * @param model
     * @return
     */
    @RequestMapping(value = "forward2ChangePasswordPage/{id}",method = RequestMethod.GET)
    public String forward2ChangePasswordPage(Model model,@PathVariable("id") Integer id) throws Exception{
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setId(id);
        ResponseEntity<Response<ManageUserDTO>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE, ManageURIConstant.ManageUser.REQUEST_MAPPING_FIND_BY_ID),
                HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<ManageUserDTO>>() {});
        Response<ManageUserDTO> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            throw new Exception("get response error.");
        }
        model.addAttribute("manageUserDTO",response.getData());
        return ManageURIConstant.ManageUser.REQUEST_MAPPING_FORWARD_TO_CHANGE_PASSWORD_PAGE;
    }

    /**
     * 根据条件查询
     * @param searchCondition
     * @return
     */
    @RequestMapping(value = "findBySearchCondition/{searchCondition}")
    @ResponseBody
    public PageDTO<ManageUserDTO> findBySeachCondition(@PathVariable("searchCondition") String searchCondition,HttpServletRequest request) throws Exception {
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        byte[] b = searchCondition.getBytes("ISO-8859-1");
        String s = new String(b,"UTF-8");
        ManageUserDTO manageUserDTO = new ManageUserDTO();
        manageUserDTO.setUsername(s);
        manageUserDTO.setName(s);
        manageUserDTO.setLimit(displayLength);
        manageUserDTO.setOffset(currentPage);
        ResponseEntity<Response<PageDTO<ManageUserDTO>>> responseEntity = null;
        HttpEntity<ManageUserDTO> requestDTO = new HttpEntity<ManageUserDTO>(manageUserDTO);
        try {
            responseEntity = resetTemplate.exchange(this.buildBizAppURI(ManageURIConstant.ManageUser.REQUEST_MAPPING_RESOURCE,ManageURIConstant.ManageUser.REQUEST_MAPPING_FIND_BY_SEARCH_CONDITION),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<ManageUserDTO>>>() {});
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        Response<PageDTO<ManageUserDTO>> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            throw new Exception("get response error");
        }
        return response.getData();
    }
}
