package com.chahan.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.chahan.blog.util.CommonUtils.*;

@Data
public class RegistrationDto {

    @NotBlank
    @Pattern(regexp =USERNAME_PATTERN , message = ERROR_USERNAME_PATTERN)
    private String username;
    @NotBlank
    @Pattern(regexp = PASSWORD_PATTERN, message = ERROR_PASSWORD_PATTERN)
    private String password;
}
