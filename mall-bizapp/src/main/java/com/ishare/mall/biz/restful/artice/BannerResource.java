package com.ishare.mall.biz.restful.artice;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.cms.BannerDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.general.Response;
import com.ishare.mall.core.model.cms.Banner;
import com.ishare.mall.core.model.product.Product;
import com.ishare.mall.core.service.artice.BannerService;
import com.ishare.mall.core.utils.mapper.MapperUtils;

@RestController
@RequestMapping(APPURIConstant.Banner.REQUEST_MAPPING)
public class BannerResource {

	private static final Logger log = LoggerFactory.getLogger(BannerResource.class);

	@Autowired
	private BannerService bannerService;

	/**
	 *  查询所有系统栏目信息
	 */
	@RequestMapping(value = APPURIConstant.Banner.REQUEST_MAPPING_ALL_BANNER,method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json",},
			consumes = {"application/json",})
	public Response findByAllBanner(@RequestBody BannerDTO bannerDTO){
		List<BannerDTO> bannerList = new ArrayList<>();
		int offset = bannerDTO.getOffset();
		int limit = bannerDTO.getLimit();
		Response response = new Response<>();
		PageRequest pageRequest = new PageRequest(offset - 1 <0 ? 0:offset - 1, limit <=0 ?15:limit,Sort.Direction.DESC,"id");
		Page<Banner> result;
		try {
			result = bannerService.findAllBanner( pageRequest);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;

		}
		PageDTO<BannerDTO> pageDTO = new PageDTO<BannerDTO>();
		if (result !=null && result.getContent() !=null && result.getContent().size()>0 ) {
			List<Banner> listBanner =result.getContent();
			for (Banner banner:listBanner) {
				BannerDTO bannerDTOTemp = new BannerDTO();
				BeanUtils.copyProperties(banner, bannerDTOTemp);
				bannerList.add(bannerDTOTemp);
			}
			pageDTO.setContent(bannerList);
			pageDTO.setTotalPages(result.getTotalPages());
			pageDTO.setITotalDisplayRecords(result.getTotalElements());
			pageDTO.setITotalRecords(result.getTotalElements());
			response.setData(pageDTO);
		}
		return response;
	}
//	/**
//	 * 获取所有系统栏目LIST列表
//	 */
//	@RequestMapping(value = APPURIConstant.Banner.REQUEST_MAPPING_ALL_BANNER_LIST, method = RequestMethod.GET,
//			headers = "Accept=application/xml, application/json",
//			produces = {"application/json"})
//	public Response findAllBrandList() {
//		List<BannerDTO> bannerList = new ArrayList<>();
//		Page<Banner> result;
//		Response response = new Response();
//		try {
//					result = bannerService.findAllBannerList();
//			for (Banner banner:result) {
//				BannerDTO  bannerDTO = (BannerDTO) MapperUtils.map(banner, BannerDTO.class);
//				bannerList.add(bannerDTO);
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			response.setMessage("系统错误");
//			response.setSuccess(false);
//			return response;
//		}
//		response.setData(bannerList);
//		return response;
//	}
	/**
	 * 删除系统栏目
	 * @param bannerDTO
	 * @return
	 */
	@RequestMapping(value = APPURIConstant.Banner.REQUEST_MAPPING_DELETE_BY_ID, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json"})
	public Response delete(@RequestBody BannerDTO bannerDTO){
		Product product = new Product();
		Response response = new Response();
		try {
			bannerService.delBanner(bannerDTO.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
		return response;

	}
	/**
	 * 通过id获取系统栏目
	 */
	@RequestMapping(value = APPURIConstant.Banner.REQUEST_MAPPING_UPDATE_BY_ID, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json"})
	public Response updateProduct(@RequestBody BannerDTO bannerDTO){
		Response response = new Response();
		try {
			Banner banner = bannerService.findOne(bannerDTO.getId());
			BannerDTO bannerDTO2 = (BannerDTO) MapperUtils.map(banner, BannerDTO.class);
			response.setData(bannerDTO2);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}
		return response;

	}

	/**
	 * 修改系统栏目
	 * @param bannerDTO
	 * @return
	 */
	/* @RequestMapping(value = APPURIConstant.Banner.REQUEST_MAPPING_UPDATE, method = RequestMethod.POST,
	            headers = "Accept=application/xml, application/json",
	            produces = {"application/json"},
	            consumes = {"application/json"})
	 public Response update(@RequestBody BannerDTO bannerDTO){
		 Response response = new Response();
		 try {
			 	Banner banner = bannerService.findOne(bannerDTO.getId());
			 	if (banner !=null) {
			 		banner.


				}



		} catch (Exception e) {
		}


		return null;



	 }*/
	/**
	 * 添加系统栏目
	 * @param bannerDTO
	 * @return
	 */

	@RequestMapping(value = APPURIConstant.Banner.REQUEST_MAPPING_ADD, method = RequestMethod.POST,
			headers = "Accept=application/xml, application/json",
			produces = {"application/json"},
			consumes = {"application/json", "application/xml"})
	public Response saveBanner(@RequestBody BannerDTO bannerDTO){
		Banner banner = new Banner();
		BeanUtils.copyProperties(banner, bannerDTO);
		try {
			bannerService.add(banner);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			Response response = new Response();
			response.setMessage("系统错误");
			response.setSuccess(false);
			return response;
		}
		Response response = new Response();
		return response;

	}



}
