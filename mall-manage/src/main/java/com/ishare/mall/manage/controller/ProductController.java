package com.ishare.mall.manage.controller;
import com.google.common.collect.Maps;
import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.constant.uri.ManageURIConstant;
import com.ishare.mall.common.base.constant.view.ManageViewConstant;
import com.ishare.mall.common.base.dto.manageuser.CurrentManageUserDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.common.base.dto.product.ProductDetailDTO;
import com.ishare.mall.common.base.dto.product.ProductTypeDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.manage.annoation.CurrentManageUser;
import com.ishare.mall.manage.controller.base.BaseController;
import com.ishare.mall.manage.form.ProductSearchForm;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by liaochenglei on 2015/8/13.
 * Description :
 * Version 1.0
 */
@Controller
@RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING)
public class ProductController extends BaseController {
	@Autowired
	private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public static Logger getLog() {
        return log;
    }
    @RequestMapping(value =  ManageURIConstant.Product.REQUEST_MAPPING_FIND_BY_ID, method = RequestMethod.GET,produces = {"application/json"})
    public ProductDetailDTO findById(@NotEmpty @PathVariable("id") Integer id) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(id);
        ResponseEntity<Response> resultEntity = null;
        RestTemplate restTemplate = new RestTemplate();
        resultEntity = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_FIND_ID),productDetailDTO,Response.class);
        ProductDetailDTO returnTO =  (ProductDetailDTO) resultEntity.getBody().getData();
        return returnTO;
    }
    
    @RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING_ALL_TYPE_PRODUCT, produces = {"application/json"})
    @ResponseBody
    public ProductTypeDTO getType() {
    	ResponseEntity<Response> resultDTO = null;
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.getForEntity(this.buildBizAppURI(APPURIConstant.ProductType.REQUEST_MAPPING, APPURIConstant.ProductType.REQUEST_MAPPING_FIND_FIRST_LEVEL), Response.class);
		ProductTypeDTO productTypeDTOResult =  (ProductTypeDTO) resultDTO.getBody().getData();
		return productTypeDTOResult;
    }
    
  @RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING_DEL, method = RequestMethod.GET)
	public String delProduct(@NotEmpty @PathVariable("id") Integer id) {
		ProductDetailDTO productDetailDTO = new ProductDetailDTO();
		productDetailDTO.setId(id);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING, APPURIConstant.Product.REQUEST_MAPPING_DEL), productDetailDTO, Response.class);
		if (resultDTO.getBody().isSuccess()){
				return "success";
  }	 return null;
  }
  
  @RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING_FORWORD, method = RequestMethod.GET)
 	public String forwardTOproductList() {
	  return ManageViewConstant.Product.LIST_PRODUCT;
  }
  
  @RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING_FIND_ALL, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findAll(@CurrentManageUser CurrentManageUserDTO currentMemberDTO, HttpServletRequest request, Model model) {
		ProductDTO productDTO = new ProductDTO();
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));
		int currentPage = displayStart/displayLength+1;
		productDTO.setLimit(displayLength);
		productDTO.setOffset(currentPage);
		ResponseEntity<Response> resultDTO = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			resultDTO = restTemplate.postForEntity(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_ALL), productDTO, Response.class);
		} catch (Exception e) {
			log.debug("error");
				e.printStackTrace();		
				}
		PageDTO pageDTO = (PageDTO) resultDTO.getBody().getData();
		model.addAttribute("pageDTO",pageDTO);
		return pageDTO;
	}
	
/*	@RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION, method = RequestMethod.GET)
	@ResponseBody
	public PageDTO findBySearchCondition(@CurrentManageUser CurrentManageUserDTO currentMemberDTO, @PathVariable("searchCondition") Integer searchCondition,Model model,HttpServletRequest request) throws Exception{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(searchCondition);
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));

		int currentPage = displayStart/displayLength+1;
		productDTO.setLimit(displayLength);
		productDTO.setOffset(currentPage);
		ResponseEntity<Response> resultDTO = null;
		HttpEntity<ProductDTO> requestDTO = new HttpEntity<ProductDTO>(productDTO);
		RestTemplate restTemplate = new RestTemplate();
		try{
			resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response>() {});

		}catch (Exception e){
			log.error("call bizp app " + APPURIConstant.Product.REQUEST_MAPPING + APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION + "error");
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
	}*/
  
	/**
	 * 根据条件查询产品
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = ManageURIConstant.Product.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION,
			method = RequestMethod.GET,
			produces = {"application/json"})
	@ResponseBody
	public PageDTO findBySearchCondition(final HttpServletRequest request,ProductSearchForm searchForm) throws Exception{
		Map<String, Object> searchParams = Maps.newConcurrentMap();
		boolean flag = true;
		if (searchForm.getId() != null&& !searchForm.getId().equals("")){
			searchParams.put("EQ_id",searchForm.getId());
			flag = false;
		}
		if (searchForm.getName() != null && !searchForm.getName().equals("")){
			searchParams.put("LIKE_name",searchForm.getName());
			flag = false;
		}
		int displayLength = Integer.parseInt(request.getParameter("length"))==0?1:Integer.parseInt(request.getParameter("length"));
		int displayStart = Integer.parseInt(request.getParameter("start"));

		int currentPage = displayStart/displayLength+1;
		searchParams.put("limit", displayLength);
		searchParams.put("offset",currentPage);
		ResponseEntity<Response<PageDTO<ProductDTO>>> resultDTO = null;
		HttpEntity<Map> requestDTO = new HttpEntity<Map>(searchParams);
		if(!flag){
			try{
				resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_BY_SEARCHCONDITION),
						HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<ProductDTO>>>() {});
			}catch (Exception e){
				log.error("call bizp app " +APPURIConstant.Product.REQUEST_MAPPING + APPURIConstant.Product.REQUEST_MAPPING_FIND_ALL + "error");
				throw new Exception(e.getMessage());
			}
		}else {
			try{	resultDTO = restTemplate.exchange(this.buildBizAppURI(APPURIConstant.Product.REQUEST_MAPPING,APPURIConstant.Product.REQUEST_MAPPING_FIND_ALL),
					HttpMethod.POST, requestDTO, new ParameterizedTypeReference<Response<PageDTO<ProductDTO>>>() {});
			}catch (Exception e){
				log.error("call bizp app "+ APPURIConstant.Order.REQUEST_MAPPING, APPURIConstant.Order.REQUEST_MAPPING_FIND_ALL + "error");
				throw new Exception(e.getMessage());
			}
		}
		Response response = resultDTO.getBody();
		if(response != null) {
			if(response.isSuccess()){
				PageDTO pageDTO = (PageDTO)response.getData();
				return pageDTO;
			}else {
				throw new Exception(response.getMessage());
			}
		}else{
			throw new Exception("get response error");
		}
	}
}
