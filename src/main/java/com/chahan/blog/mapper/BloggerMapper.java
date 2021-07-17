package com.chahan.blog.mapper;

import com.chahan.blog.dto.AuthorDto;
import com.chahan.blog.model.Blogger;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;


@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface BloggerMapper {

    AuthorDto map(Blogger blogger);
}
