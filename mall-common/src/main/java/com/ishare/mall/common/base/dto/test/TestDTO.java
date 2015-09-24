package com.ishare.mall.common.base.dto.test;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.dto.page.PageDTO;
import com.ishare.mall.common.base.dto.product.ProductDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinLin on 2015/9/21.
 * Description :
 * Version 1.0
 */

@JsonAutoDetect
public class TestDTO<T> extends GenericDTO {

    private static final long serialVersionUID = 3886269150595934286L;

    private Gender gender;

    private PageDTO<ProductDTO> pageDTO;

    List<ProductDTO> list = new ArrayList<>();

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PageDTO<ProductDTO> getPageDTO() {
        return pageDTO;
    }

    public void setPageDTO(PageDTO<ProductDTO> pageDTO) {
        this.pageDTO = pageDTO;
    }

    public List<ProductDTO> getList() {
        return list;
    }

    public void setList(List<ProductDTO> list) {
        this.list = list;
    }
}
