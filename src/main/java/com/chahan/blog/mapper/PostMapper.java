package com.chahan.blog.mapper;

import com.chahan.blog.model.dto.PostDto;
import com.chahan.blog.model.entity.Blogger;
import com.chahan.blog.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", uses = CommentMapper.class, unmappedTargetPolicy = IGNORE)
public interface PostMapper {

    List<PostDto> map(List<Post> posts);

    @Mapping(target = "bloggerLikes", expression = "java( getBloggerLikesIds(post) )")
    PostDto map(Post post);

    default List<Long> getBloggerLikesIds(Post post) {
        return post.getBloggerLikes().stream()
                .map(Blogger::getId)
                .collect(toList());
    }
}
