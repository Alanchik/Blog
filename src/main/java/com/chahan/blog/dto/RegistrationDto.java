package com.chahan.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationDto {
    @Size(min = 8, message = "Username must be min 8 symbols")
    private String username;

    private String password;
}
