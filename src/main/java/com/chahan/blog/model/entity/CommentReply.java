package com.chahan.blog.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@DiscriminatorValue("Reply")
public class CommentReply extends AbstractComment {

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private AbstractComment comment;
}


