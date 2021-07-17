package com.chahan.blog.mapper;

import com.chahan.blog.dto.PostDto;
import com.chahan.blog.model.Post;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", uses = BloggerMapper.class, unmappedTargetPolicy = IGNORE)
public interface PostMapper {
    List<PostDto> map(List<Post> posts);
}
