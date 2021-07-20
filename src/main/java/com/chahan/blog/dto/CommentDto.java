package com.chahan.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private AuthorDto author;
    private String text;
    private LocalDateTime published;
}
