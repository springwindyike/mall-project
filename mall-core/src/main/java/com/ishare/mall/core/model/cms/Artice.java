package com.ishare.mall.core.model.cms;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_SYS_LOG_NAME;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.ishare.mall.core.model.base.BaseEntity;
/**
 * 系统日志实体类
 * @author zhangzhaoxin
 *
 */
@Entity
@Table(name = TABLE_SYS_LOG_NAME)
public class Artice extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//类型
	@Column(name = "type",length = 10)
	private Integer type;
	//内容
	@Column(name = "content",length = 200)
	private String content;
	//用户名
	@Column(name = "name",length = 20)
	private String name;
	//客户端ip
	@Column(name = "ip",length = 20)
	private String ip;
	//时间
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "time",length = 20)
	private Date time;
	

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

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
