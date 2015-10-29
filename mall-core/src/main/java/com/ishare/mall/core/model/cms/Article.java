package com.ishare.mall.core.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.ishare.mall.core.model.base.BaseEntity;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_SYS_LOG_NAME;
/**
 * 系统日志实体类
 * @author zhangzhaoxin
 *
 */
@Entity
@Table(name = TABLE_SYS_LOG_NAME)
public class Article extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//类型
	@Column(name = "type",length = 10)
	private Integer type;
	//内容
	@Column(name = "neirong",length = 4000)
	private String neirong;
	//用户名
	@Column(name = "name",length = 20)
	private String name;
	//客户端ip
	@Column(name = "ip",length = 20)
	private String ip;
	//时间
	@Column(name = "time",length = 20)
	private DateTime time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}

}
