package com.ishare.mall.common.base.dto.manageuser;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.enumeration.UserType;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/10/19.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ManageUserDTO extends GenericDTO {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;

    private String salt;

    private Integer roleId;

    private String sex;
    private String name;
    private UserType userType;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
