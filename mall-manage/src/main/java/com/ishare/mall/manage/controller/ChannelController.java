package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.CenterURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.annoation.CurrentManageUser;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.form.ChannelForm;
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
import java.util.Date;

/**
 * Created by wanghao on 2015/10/21.
 *
 */
@Controller
@RequestMapping(ManageURIConstant.Channel.REQUEST_MAPPING)
public class ChannelController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    /**
     * 分页查询
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getChannelPage",method = RequestMethod.GET)
    @ResponseBody
    public PageDTO<ChannelDTO> getChannelPage(HttpServletRequest request) throws Exception{
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setOffset(currentPage);
        channelDTO.setLimit(displayLength);
        ResponseEntity<Response<PageDTO<ChannelDTO>>> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        try {
            responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_GET_CHANNEL_PAGE),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<ChannelDTO>>>() {
                    });
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        Response<PageDTO<ChannelDTO>> response = responseEntity.getBody();
        if(response == null || !response.isSuccess()){
            throw new Exception(response.getMessage());
        }else {
            return response.getData();
        }
    }

    /**
     * 挑战到分页页面
     * @return
     */
    @RequestMapping(value = "forward2ChannelPage")
    public String forward2ChannelPage(){
        return ManageViewConstant.Channel.CHANNEL_PAGE;
    }

    /**
     * 查询channel详细信息
     * @param model
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "view/{id}",method = RequestMethod.GET)
    public String  getChannelDetail(Model model,@NotEmpty @PathVariable("id") Integer id) throws Exception{
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(id);
        ResponseEntity<Response<ChannelDTO>> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        try {
            responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_FIND_BY_ID),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response<ChannelDTO>>() {
                    });
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        Response<ChannelDTO> response = responseEntity.getBody();
        if (response == null || !response.isSuccess()){
            throw new Exception("get response error");
        }
        model.addAttribute("channelDTO",response.getData());
        return ManageViewConstant.Channel.CHANNEL_VIEW;
    }

    /**
     * 更改当前channel状态
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update/{status}/{id}")
    public String updateChannelStatus(@NotEmpty @PathVariable("status") Integer status,@NotEmpty @PathVariable("id") Integer id) throws Exception{
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(id);
        channelDTO.setVisible(status == 1 ? true : false);
        ResponseEntity<Response<ChannelDTO>> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        try {
            responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_UPDATE_CHANNEL_STATUS),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response<ChannelDTO>>() {
                    });
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        Response<ChannelDTO> response = responseEntity.getBody();
        if (response == null || !response.isSuccess()){
            throw new Exception("get response error");
        }
        return ManageViewConstant.Channel.SUCCESS;
    }

    /**
     * 跳转到添加Channel页面
     * @return
     */
    @RequestMapping(value = "forward2AddPage")
    public String addChannle(){
        return ManageViewConstant.Channel.FORWARD_TO_ADD_PAGE;
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "forward2UpdatePage/{id}")
    public String forward2UpdatePage(@NotEmpty @PathVariable("id") Integer id,Model model) throws Exception{
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(id);
        ResponseEntity<Response<ChannelDTO>> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        try {
            responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_FIND_BY_ID),
                    HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response<ChannelDTO>>() {
                    });
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        Response<ChannelDTO> response = responseEntity.getBody();
        if (response == null || !response.isSuccess()){
            throw new Exception("get response error");
        }
        model.addAttribute("channelDTO",response.getData());
        return ManageViewConstant.Channel.FORWARD_TO_UPDATE_PAGE;
    }
    /**
     * 保存Channel
     * @param channelForm
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/saveChannel")
    public boolean saveChannel(ChannelForm channelForm,@CurrentManageUser CurrentManageUserDTO currentManageUserDTO) throws Exception{
        ChannelDTO channelDTO = new ChannelDTO();
        BeanUtils.copyProperties(channelForm, channelDTO);
        channelDTO.setCreateBy(currentManageUserDTO.getUsername());
        channelDTO.setCreateTime(new Date());
        ResponseEntity<Response> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_SAVE_CHANNEL),
                HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response>(){});
        Response response = responseEntity.getBody();
        if(response == null && !response.isSuccess()){
            throw new Exception("get response error");
        }
        return true;
    }

    /**
     * 修改channel
     * @param channelForm
     * @param currentManageUserDTO
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/updateChannel")
    public boolean updateChannel(ChannelForm channelForm,@CurrentManageUser CurrentManageUserDTO currentManageUserDTO) throws Exception{
        ChannelDTO channelDTO = new ChannelDTO();
        BeanUtils.copyProperties(channelForm, channelDTO);
        channelDTO.setCreateBy(currentManageUserDTO.getUsername());
        channelDTO.setCreateTime(new Date());
        ResponseEntity<Response> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_UPDATE_CHANNEL),
                HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response>(){});
        Response response = responseEntity.getBody();
        if(response == null && !response.isSuccess()){
            throw new Exception("get response error");
        }
        return true;
    }
    /**
     * Channel验证（公司名称）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = CenterURIConstant.Index.CHANNEL_VALID, method = RequestMethod.POST)
    public ValidformRespDTO channelValid(@RequestParam("name") String name, @RequestParam("param") String param) {
        ChannelTokenResultDTO channelRegisterDTO = new ChannelTokenResultDTO();
        channelRegisterDTO.setName(param);
        ResponseEntity<ValidformRespDTO> resultDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_FIND_VALID_BY_NAME), channelRegisterDTO, ValidformRespDTO.class);
        ValidformRespDTO validformRespDTO = resultDTO.getBody();
        return validformRespDTO;
    }

    /**
     * 根据条件分页查询
     * @param searchCondition
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/findBySearchCondition/{searchCondition}")
    @ResponseBody
    public  PageDTO<ChannelDTO> findBySearchCondition(@PathVariable("searchCondition") String searchCondition, Model model, HttpServletRequest request) throws Exception{
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        byte[] b = searchCondition.getBytes("ISO-8859-1");
        String s = new String(b,"UTF-8");
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setLimit(displayLength);
        channelDTO.setOffset(currentPage);
        channelDTO.setName(s);
        channelDTO.setPhone(s);
        channelDTO.setIndustry(s);
        ResponseEntity<Response<PageDTO<ChannelDTO>>> responseEntity = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_FIND_BY_PARAM),
                HttpMethod.POST,requestDTO,new ParameterizedTypeReference<Response<PageDTO<ChannelDTO>>>(){});
        Response<PageDTO<ChannelDTO>> response = responseEntity.getBody();
        if(response == null && !response.isSuccess()){
            throw new Exception("get response error");
        }
        return response.getData();
    }



    @RequestMapping(value = ManageURIConstant.Channel.REQUEST_MAPPING_FindThisWeek, method = RequestMethod.GET)
    @ResponseBody
    public PageDTO findInThisWeek(HttpServletRequest request, Model model) throws Exception {
        log.debug("hr==");
        ChannelDTO channelDTO = new ChannelDTO();
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        System.out.println(displayLength+" "+displayStart+" "+currentPage);
        channelDTO.setLimit(displayLength);
        channelDTO.setOffset(currentPage);
        ResponseEntity<Response<PageDTO<ChannelDTO>>> resultDTO = null;
        HttpEntity<ChannelDTO> requestDTO = new HttpEntity<ChannelDTO>(channelDTO);
        try {
            resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, ManageURIConstant.Channel.REQUEST_MAPPING_FindThisWeek),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<ChannelDTO>>>() {
                    });
        } catch (Exception e) {
            log.debug("error");
            e.printStackTrace();
        }
        Response response = resultDTO.getBody();
        if(response != null) {
            if(response.isSuccess()){
                PageDTO pageDTO = (PageDTO)response.getData();
                model.addAttribute("pageDTO",pageDTO);
                return pageDTO;
            }else {
                throw new Exception(response.getMessage());
            }
        }else{
            throw new Exception("get response error");
        }
    }

    /**
     * 返回到本周新增会员界面
     * @return
     */
    @RequestMapping(value = ManageURIConstant.Channel.REQUEST_MAPPING_SHOW, method = RequestMethod.GET)
    public String list() {
        System.out.println("******");
        return ManageViewConstant.Channel.LIST_THISWEEKMEMBER;
    }
}
