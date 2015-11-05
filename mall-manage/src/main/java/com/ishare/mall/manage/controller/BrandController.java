package com.ishare.mall.manage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.BrandDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.annoation.CurrentManageUser;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.form.AddBrandForm;
import com.ishare.mall.manage.form.BrandForm;


/**
 * Created by liaochenglei on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING)
public class BrandController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    public static Logger getLog() {
        return log;
    }
    
   
  
  @RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_FORWORD, method = RequestMethod.GET)
 	public String forwardToBrandList() {
	  return ManageViewConstant.Brand.LIST_BRAND;
  }
  
  @RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_ADD_FORWORD, method = RequestMethod.GET)
 	public String forwardToBrandAdd() {
	  return ManageViewConstant.Brand.ADD_BRAND;
  }
  
  @RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_FIND_ALL_BRAND, method = RequestMethod.GET,produces = {"application/json"})
	@ResponseBody
	public PageDTO findAllBrand(@CurrentManageUser CurrentManageUserDTO CurrentManageUserDTO, HttpServletRequest request, Model model) throws Exception{
		BrandDTO brandDTO = new BrandDTO();
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		brandDTO.setLimit(displayLength);
		brandDTO.setOffset(currentPage);
		HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
		ResponseEntity<Response<PageDTO<BrandDTO>>> resultDTO = null;
		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING,APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<BrandDTO>>>() {});
		} catch (Exception e) {
			log.error("call bizp app " + APPURIConstant.Brand.REQUEST_MAPPING + APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND + "error");
			throw new Exception(e.getMessage());
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
  
  @RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_FIND_ALL_BRAND_LIST, method = RequestMethod.GET,produces = {"application/json"})
	@ResponseBody
	public List<BrandDTO> findAllBrandList(@CurrentManageUser CurrentManageUserDTO CurrentManageUserDTO, HttpServletRequest request, Model model) throws Exception{
	  BrandDTO brandDTO = new BrandDTO();
		ResponseEntity<Response> resultDTO = null;
	 	HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
		try {
			resultDTO = restTemplate.exchange(
                            this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING,APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND_LIST),
                            HttpMethod.POST,requestDTO, new ParameterizedTypeReference<Response>() {
                            });
		} catch (Exception e) {
			e.printStackTrace();
			log.error("call bizp app " + APPURIConstant.Brand.REQUEST_MAPPING + APPURIConstant.Brand.REQUEST_MAPPING_ALL_BRAND_LIST + "error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response != null) {
			if(response.isSuccess()){
				List<BrandDTO> returnDTO =(List<BrandDTO>)response.getData();
			
				return returnDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}
 
  /**
   * 品牌的删除
   * @param account
   * @param CurrentManageUserDTO
   * @return
   * @throws Exception
   */
	@ResponseBody
	@RequestMapping(value =  ManageURIConstant.Brand.REQUEST_MAPPING_DELETE_BY_ID)
	public String delete(@NotEmpty @PathVariable("id") Integer id,@CurrentManageUser CurrentManageUserDTO CurrentManageUserDTO) throws Exception{
		BrandDTO brandDTO = new BrandDTO();
	  brandDTO.setId(id);
		ResponseEntity<Response<BrandDTO>> resultDTO = null;
		HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.Brand.REQUEST_MAPPING_DELETE_BY_ID),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<BrandDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Brand.REQUEST_MAPPING+APPURIConstant.Brand.REQUEST_MAPPING_DELETE_BY_ID+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if (response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return ManageViewConstant.Brand.BRAND_UPDATE_SUCCESS;
	}
	

	/**
	 * 跳转到update 页面
	 * @return
	 */
	@RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_UPDATE_BY_ID)
	public String forwordUpdateBrand(@NotEmpty @PathVariable("id") Integer id,Model model) throws  Exception{
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(id);
		ResponseEntity<Response<BrandDTO>> resultDTO = null;
		HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
		try {
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.Brand.REQUEST_MAPPING_UPDATE_BY_ID),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<BrandDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Brand.REQUEST_MAPPING+APPURIConstant.Brand.REQUEST_MAPPING_UPDATE_BY_ID+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultDTO.getBody();
		if(response != null){
			if(response.isSuccess()){
				BrandDTO returnDTO = (BrandDTO)response.getData();
				model.addAttribute("returnDTO",returnDTO);
			}else {
				throw new Exception(response.getMessage());
			}
		}else {
			throw new Exception("get response error");
		}
		return ManageViewConstant.Brand.BRAND_UPDATE;
	}
	
	@ResponseBody
	@RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_UPDATE)
	public String update(BrandForm brandForm) throws Exception{
		BrandDTO brandDTO = new BrandDTO();
		BeanUtils.copyProperties(brandForm, brandDTO);
		ResponseEntity<Response<BrandDTO>> resultEntity = null;
		HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
		try{
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.Brand.REQUEST_MAPPING_UPDATE),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<BrandDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Brand.REQUEST_MAPPING+ APPURIConstant.Brand.REQUEST_MAPPING_UPDATE+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultEntity.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return ManageViewConstant.Brand.BRAND_UPDATE_SUCCESS;
	}
	
@ResponseBody
	@RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_ADD,method = RequestMethod.POST)
	public String add(@ModelAttribute("addBrandForm") AddBrandForm addBrandForm,@CurrentManageUser CurrentManageUserDTO member) throws Exception{
		BrandDTO brandDTO = new BrandDTO();
		BeanUtils.copyProperties(addBrandForm, brandDTO);
	 brandDTO.setLogoUrl(addBrandForm.getLogo());
		ResponseEntity<Response<BrandDTO>> resultEntity = null;
		HttpEntity<BrandDTO> requestDTO = new HttpEntity<BrandDTO>(brandDTO);
		try{
			resultEntity = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Brand.REQUEST_MAPPING, APPURIConstant.Brand.REQUEST_MAPPING_ADD),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<BrandDTO>>() {});
		}catch (Exception e){
			log.error("call bizp app "+APPURIConstant.Brand.REQUEST_MAPPING+ APPURIConstant.Brand.REQUEST_MAPPING_ADD+"error");
			throw new Exception(e.getMessage());
		}
		Response response = resultEntity.getBody();
		if(response == null){
			throw new Exception("get response error");
		}
		if(response != null && !response.isSuccess()){
			throw new Exception(response.getMessage());
		}
		return ManageViewConstant.Brand.BRAND_ADD_SUCCESS;
	}
	
	  @RequestMapping(value = ManageURIConstant.Brand.REQUEST_MAPPING_UPLOAD_PIC)
	 	public String uploadPic(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String realPath = request.getSession().getServletContext().getRealPath("resources/upload");
	        response.setContentType("text/plain; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String originalFilename = null;
            if(file.isEmpty()){
                out.print("1`请选择文件后上传");
                out.flush();
                return null;
            }else{
                originalFilename = file.getOriginalFilename();
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, originalFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                    out.print("1`文件上传失败，请重试！！");
                    out.flush();
                    return null;
                }
            }
	        out.print("0`" + request.getContextPath() + "/upload/" + originalFilename);
	        out.flush();
	        return null;
	  }
}
