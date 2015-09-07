package com.ishare.mall.member.restful;

import com.ishare.mall.common.base.constant.uri.APPURIConstant;
import com.ishare.mall.common.base.dto.member.MemberPermissionDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@RestController
@RequestMapping(APPURIConstant.Permission.REQUEST_MAPPING)
public class PermissionResource {

    @RequestMapping(value = "/permissions/{account}", method = RequestMethod.GET,
            headers = "Accept=application/xml, application/json",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public MemberPermissionDTO getMemberAllPermissions(@NotEmpty @PathVariable("account") String account) {
        return null;
    }
}
