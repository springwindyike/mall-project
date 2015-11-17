package com.ishare.mall.image;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YinLin on 2015/11/16.
 * Description :
 * Version 1.0
 */
@RestController
public class ImageResource {
    /**
     * 上传图片返回地址
     * @return
     */
    @RequestMapping(value="upload", method = RequestMethod.PUT)
    public String index() {
        return "success";
    }
}
