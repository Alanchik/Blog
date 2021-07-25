package com.chahan.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePostDto {
    @NotBlank
    private String description;
}
