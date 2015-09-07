package com.ishare.mall.common.base.dto.permission;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/7.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class PermissionDTO extends GenericDTO {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionDTO that = (PermissionDTO) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
