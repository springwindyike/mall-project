package com.ishare.mall.manage.controller;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.member.MemberDTO;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2015/11/17.
 */
@Controller
@RequestMapping(ManageURIConstant.Member.REQUEST_MAPPING)
public class MemberController  extends BaseController{

    @Autowired
    private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = ManageURIConstant.Member.FindThisWeek, method = RequestMethod.GET)
    @ResponseBody
    public PageDTO findInThisWeek(HttpServletRequest request, Model model) throws Exception {
        log.debug("hr==");
        MemberDTO memberDTO = new MemberDTO();
        int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
        int displayStart = Integer.parseInt(request.getParameter("start"));
        int currentPage = displayStart/displayLength+1;
        System.out.println(displayLength+" "+displayStart+" "+currentPage);
        memberDTO.setLimit(displayLength);
        memberDTO.setOffset(currentPage);
        ResponseEntity<Response<PageDTO<MemberDTO>>> resultDTO = null;
        HttpEntity<MemberDTO> requestDTO = new HttpEntity<MemberDTO>(memberDTO);
        try {
            resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Member.REQUEST_MAPPING, ManageURIConstant.Member.FindThisWeek),
                    HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<MemberDTO>>>() {
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


    @RequestMapping(value = ManageURIConstant.Member.REQUEST_MAPPING_SHOW, method = RequestMethod.GET)
    public String list() {
        return ManageViewConstant.Member.LIST_THISWEEKMEMBER;
    }
}
