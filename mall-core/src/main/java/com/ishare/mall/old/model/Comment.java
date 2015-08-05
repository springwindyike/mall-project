package com.ishare.mall.old.model;

import com.ishare.mall.old.model.parent.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dongqi on 15/7/12.
 *
 * 评论
 */
@Entity
@Table(name = "es_comment")
public class Comment extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "comment_content")
    private String content;
    private Date commentDate;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
