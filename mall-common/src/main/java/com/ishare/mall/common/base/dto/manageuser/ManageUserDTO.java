package com.ishare.mall.common.base.dto.manageuser;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import com.ishare.mall.common.base.enumeration.Gender;
import com.ishare.mall.common.base.enumeration.UserType;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by YinLin on 2015/10/19.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ManageUserDTO extends GenericDTO {

    private Integer id;
    private String username;
    private String password;
    private String salt;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy ;
    private boolean use = true;
    private Gender sex;
    private String name;
    private UserType userType;
    private int offset;
    private int limit;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public boolean isUse() {
        return use;
    }

    public Gender getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
