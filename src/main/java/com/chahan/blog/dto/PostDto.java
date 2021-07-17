package com.chahan.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private AuthorDto author;
    private String description;
    private LocalDateTime published;

}
