package com.ishare.mall.restful;

import com.ishare.mall.core.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YinLin on 2015/8/5.
 * Description: 用户接口相关
 * Version 1.0
 */
@Controller
@RequestMapping("/members")
public class MemberResource {
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create() {
        return null;
    }

    public Member get() {
        return null;
    }

    public Member update() {
        return null;
    }


}
