package com.ishare.mall.common.base.dto.permission;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by YinLin on 2015/9/8.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class RolePermissionDTO extends GenericDTO {

    private Set<String> roleSet = new HashSet<>();
    
    private Set<String> perSet = new HashSet<>();

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPerSet() {
        return perSet;
    }

    public void setPerSet(Set<String> perSet) {
        this.perSet = perSet;
    }
}
