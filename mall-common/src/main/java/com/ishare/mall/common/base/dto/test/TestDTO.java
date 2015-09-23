package com.ishare.mall.common.base.dto.test;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/21.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.PROPERTY)
@JsonAutoDetect
public class TestDTO extends GenericDTO {

    private static final long serialVersionUID = 3886269150595934286L;

    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
