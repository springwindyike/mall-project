package com.ishare.mall.common.base.dto.cms;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.ishare.mall.common.base.dto.generic.GenericDTO;

/**
 * 系统日志DTO
 * @author zhangzhaoxin
 *
 */
@XmlRootElement
@JsonAutoDetect
public class AtricleDTO extends GenericDTO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//ID
	private Integer id;
	//类型
	private int type;
	//内容
	private String neirong;
	//用户名
	private String name;
	//客户端ip
	private String ip;
	//时间
	private String time;

	private int offset;

	private int limit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
