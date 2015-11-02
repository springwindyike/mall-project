package com.ishare.mall.core.model.cms;

import javax.persistence.*;

import java.util.Date;
/**
 * Created by YinLin on 2015/11/2.
 * Description : 附件
 * Version 1.0
 */
@Entity
@Table(name="t_attachment")
public class Attachment {
	@Id
	@GeneratedValue
	private int id;
	/**
	 * 附件上传之后的名称
	 */
	@Column(name="new_name")
	private String newName;
	/**
	 * 附件的原始名称
	 */
	@Column(name="old_name")
	private String oldName;
	/**
	 * 附件的类型，这个类型和contentType类型一致
	 */
	@Column(name="type")
	private String type;
	/**
	 * 附件的后缀名
	 */
	@Column(name="suffix")
	private String suffix;
	/**
	 * 附件的大小
	 */
	@Column(name="size")
	private long size;
	/**
	 * 该附件是否是主页图片
	 */
	@Column(name="is_index_pic")
	private int isIndexPic;
	/**
	 * 该附件是否是图片类型,0表示不是，1表示是
	 */
	@Column(name="isImg")
	private int isImg;
	/**
	 * 附件所属文章
	 */
	@ManyToOne(cascade= CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topic;
	/**
	 * 是否是附件信息，0表示不是，1表示是，如果是附件信息就在文章的附件栏进行显示
	 */
	@Column(name="is_attach")
	private int isAttach;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

	public int getIsIndexPic() {
		return isIndexPic;
	}
	public void setIsIndexPic(int isIndexPic) {
		this.isIndexPic = isIndexPic;
	}

	public int getIsImg() {
		return isImg;
	}
	public void setIsImg(int isImg) {
		this.isImg = isImg;
	}

	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getIsAttach() {
		return isAttach;
	}
	public void setIsAttach(int isAttach) {
		this.isAttach = isAttach;
	}

	public Attachment() {
	}
	public Attachment(int id, String newName, String oldName, String type,
			String suffix, long size, int isIndexPic, int isImg, int isAttach,int tid,String topicTitle,Date publishDate,String author) {
		super();
		this.id = id;
		this.newName = newName;
		this.oldName = oldName;
		this.type = type;
		this.suffix = suffix;
		this.size = size;
		this.isIndexPic = isIndexPic;
		this.isImg = isImg;
		this.isAttach = isAttach;
		this.topic = new Topic();
		this.topic.setId(tid);
		this.topic.setTitle(topicTitle);
		this.topic.setPublishDate(publishDate);
		this.topic.setAuthor(author);
	}

	public Attachment(int id, String newName, String oldName, String type,
			String suffix, long size,int isIndexPic, int isImg, int isAttach) {
		super();
		this.id = id;
		this.newName = newName;
		this.oldName = oldName;
		this.type = type;
		this.suffix = suffix;
		this.size = size;
		this.isIndexPic = isIndexPic;
		this.isImg = isImg;
		this.isAttach = isAttach;
		this.topic = new Topic();
	}
}
