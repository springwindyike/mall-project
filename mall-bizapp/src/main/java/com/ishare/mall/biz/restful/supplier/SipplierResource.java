package com.ishare.mall.biz.restful.supplier;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/11/23.
 */
@RestController
@RequestMapping(APPURIConstant.Supplier.REQUEST_MAPPING)
public class SipplierResource {

//    private static final Logger log = LoggerFactory.getLogger(MemberResource.class);
//
//    public static Logger getLog() {
//        return log;
//    }
//    /**
//     * 查询本周的新增的供应商
//     */
//    @RequestMapping(value = ManageURIConstant.Supplier.FindThisWeek, method = RequestMethod.POST,
//            headers = "Accept=application/xml, application/json",
//            produces = {"application/json"},
//            consumes = {"application/json"})
//    public Response findAllThisWeek(@RequestBody MemberDTO memberDTO) {
//        List<MemberDTO> listMember = new ArrayList<MemberDTO>();
//        Integer offset = memberDTO.getOffset();
//        Integer limit = memberDTO.getLimit();
//        PageDTO<MemberDTO> pageDTO = new PageDTO<MemberDTO>();
//        Response<PageDTO<MemberDTO>> response = new Response();
//        Page<Member> result = null;
//        try {
//            PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
//            // MemberServiceImpl memberServiceimpl = new MemberServiceImpl();
//            result = memberService.findAllThisWeek(pageRequest);
//            if (result != null && result.getContent() != null && result.getContent().size() > 0) {
//                List<MemberDTO> list = (List<MemberDTO>) MapperUtils.mapAsList(result.getContent(), MemberDTO.class);
//                pageDTO.setContent(list);
//                pageDTO.setTotalPages(result.getTotalPages());
//                pageDTO.setITotalDisplayRecords(result.getTotalElements());
//                pageDTO.setITotalRecords(result.getTotalElements());
//                pageDTO.setLimit(limit);
//                pageDTO.setOffset(offset);
//                response.setData(pageDTO);
//            } else {
//                pageDTO.setContent(listMember);
//                pageDTO.setTotalPages(0);
//                pageDTO.setITotalDisplayRecords(0L);
//                pageDTO.setITotalRecords(0L);
//                response.setData(pageDTO);
//            }
//            return response;
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            response.setMessage(e.getMessage());
//            response.setSuccess(false);
//            return response;
//        }
//
//    }
//
//    /**
//     * 查询本周新增供应商的数量
//     * @return
//     */
//    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_THISWEEK_COUNT, method = RequestMethod.GET,
//            headers = "Accept=application/xml, application/json",
//            produces = {"application/json"})
//    public Response findThisWeekCount(){
//        log.debug("findAll start");
//        Long count = memberService.findThisWeekCount();
//        Response response = new Response();
//        response.setData(count);
//        return response;
//    }
//
//    /**
//     * 查询供应商数量
//     * @return
//     */
//    @RequestMapping(value = APPURIConstant.Member.REQUEST_MAPPING_COUNT, method = RequestMethod.GET,
//            headers = "Accept=application/xml, application/json",
//            produces = {"application/json"})
//    public Response findCount(){
//        log.debug("findAll start");
//        Long count = memberService.findCount();
//        Response response = new Response();
//        response.setData(count);
//        return response;
//    }
//
//    /**
//     * 查询所有的会员（分页）
//     * @param memberDTO
//     * @return
//     */
//    @RequestMapping(value = ManageURIConstant.Member.REQUEST_MAPPING_SHOW, method = RequestMethod.POST,
//            headers = "Accept=application/xml, application/json",
//            produces = {"application/json"},
//            consumes = {"application/json"})
//    public Response findAll(@RequestBody MemberDTO memberDTO){
//        List<MemberDTO> listMember = new ArrayList<MemberDTO>();
//        Integer offset = memberDTO.getOffset();
//        Integer limit = memberDTO.getLimit();
//        PageDTO<MemberDTO> pageDTO = new PageDTO<MemberDTO>();
//        Response<PageDTO<MemberDTO>> response = new Response();
//        Page<Member> result = null;
//        try {
//            PageRequest pageRequest = new PageRequest(offset - 1 < 0 ? 0 : offset - 1, limit <= 0 ? 15 : limit, Sort.Direction.DESC, "id");
//            // MemberServiceImpl memberServiceimpl = new MemberServiceImpl();
//            result = memberService.findAll(pageRequest);
//            if (result != null && result.getContent() != null && result.getContent().size() > 0) {
//                List<MemberDTO> list = (List<MemberDTO>)MapperUtils.mapAsList(result.getContent(),MemberDTO.class);
//                pageDTO.setContent(list);
//                pageDTO.setTotalPages(result.getTotalPages());
//                pageDTO.setITotalDisplayRecords(result.getTotalElements());
//                pageDTO.setITotalRecords(result.getTotalElements());
//                pageDTO.setLimit(limit);
//                pageDTO.setOffset(offset);
//                response.setData(pageDTO);
//            } else {
//                pageDTO.setContent(listMember);
//                pageDTO.setTotalPages(0);
//                pageDTO.setITotalDisplayRecords(0L);
//                pageDTO.setITotalRecords(0L);
//                response.setData(pageDTO);
//            }
//            return response;
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            response.setMessage(e.getMessage());
//            response.setSuccess(false);
//            return response;
//        }
//
//    }
}
