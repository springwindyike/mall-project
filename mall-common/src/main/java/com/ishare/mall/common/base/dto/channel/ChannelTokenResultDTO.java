package com.ishare.mall.common.base.dto.channel;

import com.ishare.mall.common.base.dto.generic.GenericDTO;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by YinLin on 2015/9/15.
 * Description :
 * Version 1.0
 */
@XmlRootElement
@JsonAutoDetect
public class ChannelTokenResultDTO extends GenericDTO {

    private Integer id;
    /**供货商名字**/
    private String name;
    /**应用ID**/
    private String appId;
    /**应用秘钥**/
    private String appSecret;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
