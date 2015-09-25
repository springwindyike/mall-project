package com.ishare.mall.biz.restful.channel;

import com.ishare.mall.common.base.constant.CommonConstant;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.channel.ChannelTokenResultDTO;
import com.ishare.mall.common.base.dto.validform.ValidformRespDTO;
import com.ishare.mall.common.base.exception.service.channel.ChannelServiceException;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.information.Channel;
import com.ishare.mall.core.service.information.ChannelService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by YinLin on 2015/9/1.
 * Description : 渠道相关APP
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Channel.REQUEST_MAPPING)
public class ChannelResource {

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
    
}
