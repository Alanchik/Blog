package com.chahan.blog.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDto {

    private Long id;
    private AuthorDto author;
    private String description;
    private LocalDateTime published;
    private List<Long> bloggerLikes;
    private List<CommentDto> comments;
}
