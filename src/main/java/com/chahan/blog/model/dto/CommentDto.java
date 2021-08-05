package com.chahan.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDto {

    private Long id;
    private AuthorDto author;
    private String text;
    private LocalDateTime published;
    private List<Long> bloggerLikes;
    @JsonIgnoreProperties("replies")
    private List<CommentDto> replies;
}
