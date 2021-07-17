package com.chahan.blog.mapper;

import com.chahan.blog.dto.AuthorDto;
import com.chahan.blog.model.Blogger;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BloggerMapper {

    AuthorDto map(Blogger blogger);
}
