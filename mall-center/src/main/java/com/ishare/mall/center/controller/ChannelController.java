package com.ishare.mall.center.controller;

import com.ishare.mall.center.annoation.CurrentMember;
import com.ishare.mall.center.controller.base.BaseController;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.view.CenterViewConstant;
import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.common.base.dto.member.CurrentMemberDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.exception.service.channel.ChannelServiceException;
import com.ishare.mall.common.base.general.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghao on 2015/10/20.
 */
@Controller
@RequestMapping(value = APPURIConstant.Channel.REQUEST_MAPPING)
public class ChannelController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
    @Autowired
    private RestTemplate restTemplate;

//    @RequestMapping(value = "getChannelPage",method = RequestMethod.GET)
//    @ResponseBody
//    public PageDTO<ChannelDTO> getChannelPage(HttpServletRequest request,@CurrentMember CurrentMemberDTO currentMember) throws Exception {
//        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
//        int displayStart = Integer.parseInt(request.getParameter("start"));
//        int currentPage = displayStart/displayLength+1;
//        ResponseEntity<Response<PageDTO<ChannelDTO>>> responseEntity = null;
//        ChannelDTO channelDTO = new ChannelDTO();
//        channelDTO.setChannelId(currentMember.getChannelId());
//        channelDTO.setLimit(displayLength);
//        channelDTO.setOffset(currentPage);
//        try {
//            responseEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Channel.REQUEST_MAPPING, APPURIConstant.Channel.REQUEST_MAPPING_GET_CHANNEL_PAGE),
//                    HttpMethod.POST, new HttpEntity<ChannelDTO>(channelDTO), new ParameterizedTypeReference<Response<PageDTO<ChannelDTO>>>() {
//                    });
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            throw new Exception(e.getMessage());
//        }
//        Response<PageDTO<ChannelDTO>> response = responseEntity.getBody();
//        if (response == null || !response.isSuccess()){
//            throw new Exception("get response error");
//        }
//        return response.getData();
//    }

    @RequestMapping(value = "view",method = RequestMethod.GET)
    public String  getChannelDetail(Model model,@CurrentMember CurrentMemberDTO currentMemberDTO) throws Exception{
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(currentMemberDTO.getChannelId());
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
        return CenterViewConstant.Channel.CHANNEL_VIEW;
    }
}
