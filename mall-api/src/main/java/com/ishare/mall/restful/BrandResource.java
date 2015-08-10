package com.ishare.mall.restful;

import com.ishare.mall.common.base.dto.information.BrandDetailDTO;
import com.ishare.mall.common.base.dto.information.BrandListDTO;
import com.ishare.mall.core.model.information.Brand;
import com.ishare.mall.core.service.information.BrandService;
import com.ishare.mall.core.utils.mapper.MapperUtils;
import com.ishare.mall.utils.Servlets;
import com.ishare.mall.utils.page.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.LIMIT;
import static com.ishare.mall.common.base.constant.ResourceConstant.PAGE.OFFSET;

/**
 * Created by YinLin on 2015/8/10.
 * Description:品牌接口
 * Version 1.0
 */
@RestController
@RequestMapping("/brands")
public class BrandResource {

    private static final Logger log = LoggerFactory.getLogger(BrandResource.class);
    @Autowired
    private BrandService brandService;

    /**
     * 通过类型ID获取单个类型信息  格式 /brands/{id} GET
     * @param id 商品ID
     * @return ProductDetailDTO 返回的数据对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BrandDetailDTO get(@NotEmpty @PathVariable("id") Integer id) {
        //用findOne立即加载实体对象
        Brand brand = brandService.findOne(id);
        //转换为接口输出数据对象DTO 映射规则需要自己添加
        return (BrandDetailDTO) MapperUtils.map(brand, BrandDetailDTO.class);
    }

    /**
     * 通过当前页和每页数量获取商品列表 格式 /products/offset/{offset}/limit/{limit} GET
     * @param offset 分页下标
     * @param limit 分页size
     * @return 返回ProductListDTO
     */
    @RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
    public Page<BrandListDTO> get(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
        Page<Brand> result = brandService.search(null, pageRequest);
        return PageUtils.mapper(result, pageRequest, BrandListDTO.class);
    }

    /**
     * 根据条件查询商品
     * @param request http request
     * @return 返回查询结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<BrandListDTO> search(final HttpServletRequest request) {

        int offset = 1;
        int limit = 15;
        if (StringUtils.isNotEmpty(request.getParameter(OFFSET))) {
            offset = Integer.valueOf(request.getParameter(OFFSET));
            if (offset <= 0) {
                offset = 1;
            }
        }

        if (StringUtils.isNotEmpty(request.getParameter(LIMIT))) {
            limit = Integer.valueOf(request.getParameter(LIMIT));
            if (limit <= 0) {
                limit = 15;
            }
        }

        PageRequest pageRequest = new PageRequest(offset - 1, limit, Sort.Direction.DESC, "id");

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        log.debug("searchParams: {}", searchParams);

        Page<Brand> result = brandService.search(searchParams, pageRequest);

        log.debug("result {}", result.getContent());

        return PageUtils.mapper(result, pageRequest, BrandListDTO.class);
    }

    public static Logger getLog() {
        return log;
    }
}
