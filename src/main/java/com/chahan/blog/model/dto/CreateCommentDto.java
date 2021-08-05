package com.chahan.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCommentDto {

    @NotBlank
    private String text;
}
