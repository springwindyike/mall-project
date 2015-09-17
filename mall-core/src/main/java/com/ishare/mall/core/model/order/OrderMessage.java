package com.ishare.mall.core.model.order;

import com.ishare.mall.core.model.base.BaseEntity;
import com.ishare.mall.core.model.member.Member;

import javax.persistence.*;
import java.util.Date;

import static com.ishare.mall.common.base.constant.DataBaseConstant.Table.TABLE_ORDER_MESSAGE_NAME;

/**
 * 客服留言
 * @author keyo
 *
 */
@Entity(name = TABLE_ORDER_MESSAGE_NAME)
public class OrderMessage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**留言内容**/
	@Column(length = 100, nullable = false, name = "order_message_content")
    private String content;
	@Temporal(TemporalType.TIMESTAMP) @Column(nullable=false)
    private Date createTime = new Date();
    /**留言客服**/
	/* 所属用户 */
	@ManyToOne(cascade = CascadeType.REFRESH,optional=false)
	@JoinColumn(name="member_id")
	private Member member;
    /**所属订单，必须要有所以定义@ManyToOne(optional=false)**/
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
