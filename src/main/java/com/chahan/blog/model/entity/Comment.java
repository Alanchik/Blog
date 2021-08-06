package com.chahan.blog.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("Comment")
public class Comment extends AbstractComment {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<CommentReply> replies;
}
