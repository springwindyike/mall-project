package com.ishare.mall.common.base.dto.test;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/21.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class TestDTO extends GenericDTO {
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
