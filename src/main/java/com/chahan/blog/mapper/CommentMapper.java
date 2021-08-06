package com.chahan.blog.mapper;

import com.chahan.blog.model.dto.CommentDto;
import com.chahan.blog.model.entity.AbstractComment;
import com.chahan.blog.model.entity.Blogger;
import com.chahan.blog.model.entity.Comment;
import com.chahan.blog.model.entity.CommentReply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "bloggerLikes", expression = "java( getBloggerLikesIds(comment) )")
    CommentDto map(Comment comment);

    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "bloggerLikes", expression = "java( getBloggerLikesIds(commentReply) )")
    CommentDto map(CommentReply commentReply);

    default List<Long> getBloggerLikesIds(AbstractComment comment) {
        return comment.getBloggerLikes().stream()
                .map(Blogger::getId)
                .collect(toList());
    }
}
