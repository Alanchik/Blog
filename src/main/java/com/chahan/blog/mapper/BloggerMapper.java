package com.chahan.blog.mapper;

import com.chahan.blog.dto.AuthorDto;
import com.chahan.blog.model.Blogger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BloggerMapper {

    AuthorDto map(Blogger blogger);
}
