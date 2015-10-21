package com.ishare.mall.biz.restful.channel;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelDTO;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.common.base.exception.service.channel.ChannelServiceException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import com.ishare.mall.core.utils.page.PageUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by YinLin on 2015/9/1.
 * Description : 渠道相关APP
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Channel.REQUEST_MAPPING)
public class ChannelResource {
    private static final Logger logger = LoggerFactory.getLogger(ChannelResource.class);
    @Autowired
    private ChannelService channelService;
    /**
     * 通过appId获取订单
     * @param id
     * @return
     */
    @RequestMapping(value       = APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_ID,
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json"})
    public Response<ChannelTokenResultDTO> getByAppId(@NotEmpty @PathVariable("id")String id) {
        Channel channel = channelService.findByAppId(id);
        if (channel != null) {
            Response response = new Response();
            response.setCode(200);
            response.setData((ChannelTokenResultDTO)MapperUtils.map(channel, ChannelTokenResultDTO.class));
            return response;
        }
        throw new ChannelServiceException("未找到");
    }

    /**
     * 通过appSecret获取订单
     * @param secret
     * @return
     */
    @RequestMapping(value       = APPURIConstant.Channel.REQUEST_MAPPING_GET_BY_APP_SECRET,
                    method      = RequestMethod.GET,
                    headers     = "Accept=application/xml, application/json",
                    produces    = {"application/json"})
    public Response<ChannelTokenResultDTO> getByAppSecret(@NotEmpty @PathVariable("secret") String secret) {
        Channel channel = channelService.findByAppSecret(secret);
        if (channel != null) {
            Response response = new Response();
            response.setCode(200);
            response.setData((ChannelTokenResultDTO)MapperUtils.map(channel, ChannelTokenResultDTO.class));
            return response;
        }
        throw new ChannelServiceException("未找到");
    }
    
    /**
     * 通过account查询出channel是否存在
     * @return Channel 返回的数据对象
     */
    @RequestMapping(value 	= APPURIConstant.Channel.REQUEST_MAPPING_FIND_VALID_BY_NAME, method = RequestMethod.POST,
            				headers 	= "Accept=application/xml, application/json",
        							produces = {"application/json", "application/xml"},
        							consumes = {"application/json", "application/xml"})
    public ValidformRespDTO findValidByAccount(@RequestBody ChannelTokenResultDTO channelRegisterDTO) {
        Channel channel = channelService.findByName(channelRegisterDTO.getName());
        ValidformRespDTO validformRespDTO = new ValidformRespDTO();
        if(null != channel){
		        validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_FAIL_INFO);
		        validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_FAIL_STATUS);
	    			}else{
		        validformRespDTO.setInfo(CommonConstant.ValidForm.VALIDFORM_SUCCESS_INFO);
		        validformRespDTO.setStatus(CommonConstant.ValidForm.VALIDFORM_SUCCESS_STATUS);
        				}
         return validformRespDTO;
    	}

    @RequestMapping(value = APPURIConstant.Channel.REQUEST_MAPPING_GET_CHANNEL_PAGE,method = RequestMethod.POST,
                    headers = "Accept=application/xml, application/json",
                    produces = {"application/json"},
                    consumes = {"application/json"})
    public Response<PageDTO<ChannelDTO>> getChannelPage(ChannelDTO channelDTO){
        Response<PageDTO<ChannelDTO>> response = new Response<PageDTO<ChannelDTO>>();
        int offset = channelDTO.getOffset();
        int limit = channelDTO.getLimit();
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
        try{
            Page<Channel> pageChannel = channelService.getChannelpage(channelDTO.getId(),pageRequest);
            PageDTO<ChannelDTO> pageChnnelDTO = PageUtils.mapper(pageChannel, ChannelDTO.class);
            response.setData(pageChnnelDTO);
        }catch (ChannelServiceException e){
            logger.error(e.getMessage());
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
//        if(pageChannel != null && pageChannel.getContent() != null && pageChannel.getContent().size() > 0){
//            List<Channel> channelList = pageChannel.getContent();
//            PageDTO<ChannelDTO> pageChnnelDTO = PageUtils.mapper(pageChannel, ChannelDTO.class);
//        }
        return response;
    }

    @RequestMapping(value = APPURIConstant.Channel.REQUEST_MAPPING_GET_CHANNEL_PAGE,method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json"},
            consumes = {"application/json"})
    public Response<ChannelDTO> findByChannelId(ChannelDTO channelDTO){
        Channel channel = channelService.findOne(channelDTO.getId());
        BeanUtils.copyProperties(channel,channelDTO);
        Response<ChannelDTO> response = new Response<ChannelDTO>();
        response.setData(channelDTO);
        return response;
    }
}
