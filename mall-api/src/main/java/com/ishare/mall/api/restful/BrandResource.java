package com.ishare.mall.api.restful;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by YinLin on 2015/8/10.
 * Description:品牌接口
 * Version 1.0
 */
@RestController
@RequestMapping("/brands")
public class BrandResource {

//    private static final Logger log = LoggerFactory.getLogger(BrandResource.class);
//    @Autowired
//    private BrandService brandService;
//
//    /**
//     * 通过类型ID获取单个类型信息  格式 /brands/{id} GET
//     * @param id 商品ID
//     * @return ProductDetailDTO 返回的数据对象
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public BrandDetailDTO get(@NotEmpty @PathVariable("id") Integer id) {
//        //用findOne立即加载实体对象
//        Brand brand = brandService.findOne(id);
//        //转换为接口输出数据对象DTO 映射规则需要自己添加
//        return (BrandDetailDTO) MapperUtils.map(brand, BrandDetailDTO.class);
//    }
//
//    /**
//     * 通过当前页和每页数量获取商品列表 格式 /products/offset/{offset}/limit/{limit} GET
//     * @param offset 分页下标
//     * @param limit 分页size
//     * @return 返回ProductListDTO
//     */
//    @RequestMapping(value = "/offset/{offset}/limit/{limit}", method = RequestMethod.GET)
//    public Page<BrandListDTO> get(@NotEmpty @PathVariable(OFFSET)Integer offset, @NotEmpty @PathVariable(LIMIT)Integer limit) {
//        PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
//        Page<Brand> result = brandService.search(null, pageRequest);
//        return PageUtils.mapper(result, pageRequest, BrandListDTO.class);
//    }
//
//    /**
//     * 根据条件查询商品
//     * @param request http request
//     * @return 返回查询结果
//     */
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public Page<BrandListDTO> search(final HttpServletRequest request) {
//
//        PageRequest pageRequest = PageUtils.getPageRequest(request, Sort.Direction.DESC, "id");
//
//        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
//
//        log.debug("searchParams: {}", searchParams);
//
//        Page<Brand> result = brandService.search(searchParams, pageRequest);
//
//        log.debug("result {}", result.getContent());
//
//        return PageUtils.mapper(result, pageRequest, BrandListDTO.class);
//    }
//
//    public static Logger getLog() {
//        return log;
//    }
}
